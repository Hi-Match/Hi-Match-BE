package kr.co.himatch.thanksyouplz.company.service;

import kr.co.himatch.thanksyouplz.company.dto.CompanySignupRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupResponseDTO;

public interface CompanyService {
    // 기업용 일반 회원 가입
    CompanySignupResponseDTO companySignUp(CompanySignupRequestDTO companySignupRequestDTO);

}
