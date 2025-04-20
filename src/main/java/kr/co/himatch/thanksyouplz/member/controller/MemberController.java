package kr.co.himatch.thanksyouplz.member.controller;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.validation.Valid;
import kr.co.himatch.thanksyouplz.auth.jwt.JsonWebToken;
import kr.co.himatch.thanksyouplz.auth.util.EmailUtil;
import kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils;
import kr.co.himatch.thanksyouplz.config.AuthConfig;
import kr.co.himatch.thanksyouplz.member.dto.*;
import kr.co.himatch.thanksyouplz.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils.REFRESH_PERIOD;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 휴대폰 인증번호 발신
    public static DefaultMessageService messageService;

    // 관리자가 휴대폰 인증을 보내기 위한 것
    @Autowired
    private AuthConfig authConfig;


    //     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//     여기서부터 휴대폰 인증 Controller
    @Autowired
    public void setDefaultMessageService(AuthConfig authConfig) {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        messageService = NurigoApp.INSTANCE.initialize(authConfig.getCoolsmsapikey(), authConfig.getCoolsmssecretkey(), "https://api.coolsms.co.kr");
    }

    // 회원가입 - 휴대폰 인증 시 6자리 랜덤 숫자 생성
    public static int generateAuthNo1() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

    // 휴대폰 인증
    @PostMapping("/phone")
    public ResponseEntity<?> phone(@RequestBody MemberPhoneRequestDTO memberPhoneRequestDTO) {
        log.info("호출된 API: {}", memberPhoneRequestDTO);

        Message message = new Message();
        int randomPhone = generateAuthNo1();//46번째 줄 만들어둔 변수를 가져온 것임

        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(authConfig.getPhone());
        message.setTo(memberPhoneRequestDTO.getMemberPhone());//memberphone에 있는 memberphone에 보낼거야
        message.setText("[Hi-Match] 본인확인 인증번호는 [" + randomPhone + "] 입니다. 인증번호를 입력해주세요.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        MemberPhoneResponseDTO memberPhoneResponseDTO = new MemberPhoneResponseDTO();
        memberPhoneResponseDTO.setAuthenticationNumber(String.valueOf(randomPhone));

        log.info("호출된 API: {}", memberPhoneResponseDTO);
        return new ResponseEntity<MemberPhoneResponseDTO>(memberPhoneResponseDTO, HttpStatus.OK);
    }

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberSignUpRequestDTO memberSignUpRequestDTO) {
        MemberSignUpResponseDTO memberSignUpResponseDTO = memberService.signUp(memberSignUpRequestDTO);
        return new ResponseEntity<>(memberSignUpResponseDTO, HttpStatus.OK);
    }

    // 회원 가입 시 ID 중복 검사
    @GetMapping("/idcheck")
    public ResponseEntity<?> checkID(@ModelAttribute MemberCheckIDRequestDTO memberCheckIDRequestDTO) {
        Boolean checkMemberID = memberService.checkMemberID(memberCheckIDRequestDTO);
        return new ResponseEntity<>(checkMemberID, HttpStatus.OK);
    }

    // 일반 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {

        Map<String, String> responsebody = new HashMap<>();
        responsebody.put("message", "Success");

        Long login = memberService.login(memberLoginRequestDTO.getMemberID(), memberLoginRequestDTO.getMemberPass());

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
            memberService.memberNormalLoginRefreshToken(login, jsonWebToken.getRefreshToken());
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

    // ID 찾기
    @PostMapping("/idfind")
    public ResponseEntity<?> findID(@RequestBody MemberFindIDRequestDTO memberFindIDRequestDTO) {

        List<MemberFindIDResponseDTO> findID = memberService.findID(memberFindIDRequestDTO);

        if (findID.isEmpty()) {
            return new ResponseEntity<>("일치하는 회원 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(findID, HttpStatus.OK);
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

    // PW 찾기
    @PostMapping("/pwfind")
    public ResponseEntity<?> findPW(@RequestBody MemberFindPassRequestDTO memberFindPassRequestDTO) {

        String temporaryPW = getTempPassword();
        String findPass = memberService.findPass(memberFindPassRequestDTO, temporaryPW);

        if (findPass == null) {
            return new ResponseEntity<>("일치하는 회원 정보가 없습니다", HttpStatus.BAD_REQUEST);
        } else {

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

            MemberFindPassResponseDTO memberPwFindResponseDTO = new MemberFindPassResponseDTO();
            memberPwFindResponseDTO.setMemberMail(findPass);

            return new ResponseEntity<>(memberPwFindResponseDTO, HttpStatus.OK);
        }
    }

    // 프로필 편집 - 휴대폰 번호 변경
    @PutMapping("/modify-phone")
    public ResponseEntity<?> changePhone(@RequestBody MemberChangePhoneRequestDTO memberChangePhoneRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        MemberChangePhoneResponseDTO changePhone = memberService.changePhone(memberChangePhoneRequestDTO, memberNo);
        return new ResponseEntity<>(changePhone, HttpStatus.OK);
    }

    // 프로필 편집 - 메일 변경
    @PutMapping("/modify-mail")
    public ResponseEntity<?> changeMail(@RequestBody MemberChangeMailRequestDTO memberChangeMailRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        MemberChangeMailResponseDTO changeMail = memberService.changeMail(memberChangeMailRequestDTO, memberNo);
        return new ResponseEntity<>(changeMail, HttpStatus.OK);
    }


    // 프로필 편집 - 비밀번호
    @PutMapping("/modify-pass")
    public ResponseEntity<?> changePass(@RequestBody MemberChangePassRequestDTO memberChangePassRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        MemberChangePassResponseDTO changePass = memberService.changePass(memberChangePassRequestDTO);

        if (changePass == null) {
            return new ResponseEntity<>("잘못된 접근입니다. 다시 시도해주세요", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(changePass, HttpStatus.OK);
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        MemberLogOutResponseDTO memberLogOutResponseDTO = new MemberLogOutResponseDTO();
        memberLogOutResponseDTO.setMessage("Success");

        // 리프레시 토큰을 제거한다.
        ResponseCookie cookie = ResponseCookie.from("Refresh", "")
                .sameSite("None")
                .httpOnly(false)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        headers.add("Set-Cookie", cookie.toString());

        log.info("{}", memberLogOutResponseDTO);
        return new ResponseEntity<>(memberLogOutResponseDTO, headers, HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<?> memberDelete(){
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        MemberDeleteResponseDTO memberDelete = memberService.memberDelete(memberNo);

        return new ResponseEntity<>(memberDelete, HttpStatus.OK);
    }


}
