package kr.co.himatch.thanksyouplz.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import kr.co.himatch.thanksyouplz.auth.jwt.JsonWebToken;
import kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils;
import kr.co.himatch.thanksyouplz.company.dto.*;
import kr.co.himatch.thanksyouplz.company.service.CompanyService;
import kr.co.himatch.thanksyouplz.config.AuthConfig;
import kr.co.himatch.thanksyouplz.auth.util.OkHttpService;
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

import static kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils.REFRESH_PERIOD;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/company")
public class CompanyConrtoller {
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
    @PostMapping("/mebmer/license")
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


}
