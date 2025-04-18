package kr.co.himatch.thanksyouplz.company.service;

import kr.co.himatch.thanksyouplz.company.dto.CompanyMemberIDCheckRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupResponseDTO;

public interface CompanyService {
    // 기업용 일반 회원 가입
    CompanySignupResponseDTO companySignUp(CompanySignupRequestDTO companySignupRequestDTO);

    // 기업용 일반 회원 가입 시, ID 중복 검사
    Boolean checkCompanyMemberID(CompanyMemberIDCheckRequestDTO companyMemberIDCheckRequestDTO);

    // 기업용 일반 회원 로그인
    Long companyLogin(String memberID, String memberPass);

    // 일반 로그인 시, refresh Token 저장하기
    void memberNormalLoginRefreshToken(Long companyNo, String companyMemberToken);


}
