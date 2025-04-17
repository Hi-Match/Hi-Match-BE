package kr.co.himatch.thanksyouplz.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import kr.co.himatch.thanksyouplz.company.dto.CompanyLicenseRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanyLicenseResponseDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupResponseDTO;
import kr.co.himatch.thanksyouplz.company.service.CompanyService;
import kr.co.himatch.thanksyouplz.config.AuthConfig;
import kr.co.himatch.thanksyouplz.util.OkHttpService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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


    @PostMapping("/mebmer/signup")
    public ResponseEntity<?> signup(@RequestBody CompanySignupRequestDTO companySignupRequestDTO) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

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
