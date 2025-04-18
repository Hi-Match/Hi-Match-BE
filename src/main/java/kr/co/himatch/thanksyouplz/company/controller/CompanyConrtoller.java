package kr.co.himatch.thanksyouplz.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import kr.co.himatch.thanksyouplz.company.dto.CompanyLicenseRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanyLicenseResponseDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupResponseDTO;
import kr.co.himatch.thanksyouplz.company.service.CompanyService;
import kr.co.himatch.thanksyouplz.config.AuthConfig;
import kr.co.himatch.thanksyouplz.auth.util.OkHttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

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
}
