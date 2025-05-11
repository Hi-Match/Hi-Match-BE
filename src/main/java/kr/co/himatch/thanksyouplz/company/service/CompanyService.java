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

    // 기업용 회원 프로필 편집 - 휴대폰 번호 변경
    CompanyChangePhoneResponseDTO companyChangePhone(CompanyChangePhoneRequestDTO companyChangePhoneRequestDTO, Long memberNo);

    // 기업용 회원 프로필 편집 - 메일 변경
    CompanyChangeMailResponseDTO companyChangeMail(CompanyChangeMailRequestDTO companyChangeMailRequestDTO, Long memberNo);

    // 기업용 회원 프로필 편집 - 비밀번호 변경
    CompanyChangePassResponseDTO companyChangePass(CompanyChangePassRequestDTO companyChangePassRequestDTO, Long memberNo);

    // 기업용 회원 탈퇴
    CompanyMemberDeleteResponseDTO companyDelete(Long memberNo);

    // 기업용 상세 조회
    CompanyInfoDetailResponseDTO companyDetail(Long memberNo);

    // 기업용 상세 등록
    CompanyInfoRegisterResponseDTO companyUpdate(CompanyInfoRegisterRequestDTO registerRequestDTO, Long memberNo);

    // 기업 회원 프로필 정보
    CompanyMemberMyhomeResponseDTO companyMemberMyhome(Long memberNo);

    // 기업용 사업자등록번호 조회
    CompanySelectLicenseResponseDTO companySelectLicense(Long memberNo);
}
