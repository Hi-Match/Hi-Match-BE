package kr.co.himatch.thanksyouplz.company.service;

import kr.co.himatch.thanksyouplz.company.dto.*;

import java.util.List;

public interface CompanyService {
    // 기업용 일반 회원 가입
    CompanySignupResponseDTO companySignUp(CompanySignupRequestDTO companySignupRequestDTO);

    // 기업용 일반 회원 가입 시, ID 중복 검사
    Boolean checkCompanyMemberID(CompanyMemberIDCheckRequestDTO companyMemberIDCheckRequestDTO);

    // 기업용 일반 회원 로그인
    Long companyLogin(String memberID, String memberPass);

    // 일반 로그인 시, refresh Token 저장하기
    void memberNormalLoginRefreshToken(Long companyNo, String companyMemberToken);

    // 기업용 회원 ID 찾기
    List<CompanyMemberFindIDResponseDTO> companyFindID(CompanyMemberFindIDRequestDTO companyMemberFindIDRequestDTO);

    // 기업용 회원 PW 찾기
    String findPass(CompanyMemberFindPWRequestDTO companyMemberFindPWRequestDTO, String memberPass);
}
