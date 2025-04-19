package kr.co.himatch.thanksyouplz.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.validation.Valid;
import kr.co.himatch.thanksyouplz.auth.jwt.JsonWebToken;
import kr.co.himatch.thanksyouplz.auth.util.EmailUtil;
import kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils;
import kr.co.himatch.thanksyouplz.company.dto.*;
import kr.co.himatch.thanksyouplz.company.service.CompanyService;
import kr.co.himatch.thanksyouplz.config.AuthConfig;
import kr.co.himatch.thanksyouplz.auth.util.OkHttpService;
import kr.co.himatch.thanksyouplz.member.dto.MemberFindPassResponseDTO;
import kr.co.himatch.thanksyouplz.member.dto.MemberLoginResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils.REFRESH_PERIOD;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private OkHttpService okHttpService;
    @Autowired
    private AuthConfig authConfig;
    @Autowired
    private CompanyService companyService;


    // 기업용 회원 가입
    // 기업은 소셜 로그인 진행하지 않고, 일반 회원 가입만 가능하다
    @PostMapping("/member/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody CompanySignupRequestDTO companySignupRequestDTO) {

        CompanySignupResponseDTO companySignUp = companyService.companySignUp(companySignupRequestDTO);

        return new ResponseEntity<>(companySignUp, HttpStatus.OK);
    }


    // 사업자 등록번호 조회
    @PostMapping("/member/license")
    public ResponseEntity<?> license(@RequestBody CompanyLicenseRequestDTO companyLicenseRequestDTO) throws IOException {
        String url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=" + authConfig.getLicenseKey();
        String jsonData = String.format("{ \"b_no\": [ \"%s\" ] }", companyLicenseRequestDTO.getLicenseNumber());
        String result = okHttpService.callPostApi(url, jsonData);
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyLicenseResponseDTO companyLicenseResponseDTO = objectMapper.readValue(result, CompanyLicenseResponseDTO.class);
        return new ResponseEntity<>(companyLicenseResponseDTO.hasMatchCnt(), HttpStatus.OK);
    }

    // 회원 가입 시 ID 중복 검사
    @GetMapping("member/idcheck")
    public ResponseEntity<?> checkID(@ModelAttribute CompanyMemberIDCheckRequestDTO memberIDCheckRequestDTO){
        Boolean checkCompanyMemberID = companyService.checkCompanyMemberID(memberIDCheckRequestDTO);
        return new ResponseEntity<>(checkCompanyMemberID, HttpStatus.OK);
    }

    // 일반 로그인
    @PostMapping("/member/login")
    public ResponseEntity<?> companyLogin(@RequestBody CompanyMemberLoginRequestDTO companyMemberLoginRequestDTO){

        Map<String, String> responsebody = new HashMap<>();
        responsebody.put("message", "Success");

        Long login = companyService.companyLogin(
                companyMemberLoginRequestDTO.getMemberID(), companyMemberLoginRequestDTO.getMemberPass());

        if (login != null) {

            JsonWebToken jsonWebToken = JwtTokenUtils.allocateToken(login, "ROLE_USER");
            MultiValueMap<String, String> headers = new HttpHeaders();

            // 엑세스 토큰을 넣어준다. 헤더에 들어간다. DB에는 저장되지 않는다.
            // 백엔드에서만 토큰을 관리하기 때문에 header에 AccessToken을 보내지 않는다.
//            headers.add("Access", jsonWebToken.getAccessToken());

            // AccessToken도 쿠키로 준다.
            // 원래 AccssToken을 Authorization이라는 이름으로 줬으나, Access로 변경
//            ResponseCookie accessToken = ResponseCookie.from("Access", jsonWebToken.getAccessToken())
//                    .sameSite("None")
//                    .httpOnly(false)
//                    .secure(true)
//                    .path("/")
//                    .maxAge(0)
//                    .build();
//            headers.add("Set-Cookie", accessToken.toString());

            // 리프레시 토큰을 넣어준다. 해당 member_Token에 들어간다.
            companyService.memberNormalLoginRefreshToken(login, jsonWebToken.getRefreshToken());
            ResponseCookie cookie = ResponseCookie.from("Refresh", jsonWebToken.getRefreshToken())
                    //sameSite == None 으로 하는 순간, 다른 서버(?) 곳 에서도 접속이 가능하다.
                    .sameSite("None")
                    .httpOnly(false)
                    .secure(true)
                    .path("/")
                    .maxAge(REFRESH_PERIOD / 1000)
                    .build();
            headers.add("Set-Cookie", cookie.toString());

            MemberLoginResponseDTO memberLoginResponseDTO = new MemberLoginResponseDTO();
            memberLoginResponseDTO.setMessage("Success");

            return new ResponseEntity<>(memberLoginResponseDTO, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ID와 PW를 확인해주세요", HttpStatus.BAD_REQUEST);
        }
    }

    // 기업용 회원 로그아웃
    @GetMapping("/member/logout")
    public ResponseEntity<?> companyLogOut(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        CompanyMemberLogOutResponseDTO companyMemberLogOutResponseDTO = new CompanyMemberLogOutResponseDTO();
        companyMemberLogOutResponseDTO.setMessage("Success!");

        // 리프레시 토큰을 제거한다.
        ResponseCookie cookie = ResponseCookie.from("Refresh", "")
                .sameSite("None")
                .httpOnly(false)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        headers.add("Set-Cookie", cookie.toString());

        return new ResponseEntity<>(companyMemberLogOutResponseDTO, headers, HttpStatus.OK);

    }

    // 기업용 회원 ID 찾기
    @PostMapping("/member/idfind")
    public ResponseEntity<?> findID(@RequestBody CompanyMemberFindIDRequestDTO companyMemberFindIDRequestDTO){

        List<CompanyMemberFindIDResponseDTO> companyFindID = companyService.companyFindID(companyMemberFindIDRequestDTO);

        if (companyFindID.isEmpty()){
            return new ResponseEntity<>("일치하는 회원 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(companyFindID, HttpStatus.OK);
        }
    }

    // 랜덤함수로 임시비밀번호 구문 만들기
    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    // 기업용 회원 PW 찾기
    @PostMapping("/member/pwfind")
    public ResponseEntity<?> findPW(@RequestBody CompanyMemberFindPWRequestDTO companyMemberFindPWRequestDTO){

        String temporaryPW = getTempPassword();
        String findPass = companyService.findPass(companyMemberFindPWRequestDTO, temporaryPW);

        if (findPass == null) {
            return new ResponseEntity<>("일치하는 회원 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }else{

            // 임시비밀번호 발급 메일 전송
            final String fromEmail = authConfig.getEmailId(); // requires valid gmail id
            final String password = authConfig.getEmailPw(); // correct password for gmail id

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
            props.put("mail.smtp.port", "587"); // TLS Port
            props.put("mail.smtp.auth", "true"); // enable authentication
            props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            // create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                // override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            EmailUtil.sendEmail(session, findPass,
                    "[Hi-Match] 하이매치 임시 비밀번호 안내 입니다.",
                    "Hi! Higher한 당신을 Hire합니다.\n\n\n" +
                            "안녕하세요. 하이매치입니다.\n\n" +
                            "지원자와 기업을 1:1로 이어주는 [Hi-Match] 임시 비밀번호 발급 안내입니다.\n\n" +
                            "회원님의 [Hi-Match] 임시 비밀번호가 발급되었습니다.\n" +
                            "아래의 임시 비밀번호로 로그인 하신 후 비밀번호를 재설정하시기 바랍니다.\n" +
                            "비밀번호 재설정은 설정 > 비밀번호 변경에서 가능합니다.\n" +
                            "\n" +
                            "임시 비밀번호는 복사 + 붙여넣기 대신 직접 입력하여 주시기 바랍니다.\n\n\n" +
                            temporaryPW);

            CompanyMemberFindPWResponseDTO companyMemberFindPWResponseDTO = new CompanyMemberFindPWResponseDTO();
            companyMemberFindPWResponseDTO.setMemberMail(findPass);

            return new ResponseEntity<>(companyMemberFindPWResponseDTO, HttpStatus.OK);
        }
    }

    //


}
