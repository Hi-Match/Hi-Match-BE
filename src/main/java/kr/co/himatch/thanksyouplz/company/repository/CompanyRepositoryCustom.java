package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryCustom {
    // 회원 가입 시, ID 중복 검사
    Optional<String> selectMemberID(String memberID);

    // 기업용 일반 로그인
    Company selectID(String memberID);

    // 기업용 회원 ID 찾기
    List<Company> selectMemberNameAndLicenseNumber(String memberName, String licenseNumber);

    // 기업용 회원 PW 찾기
    Optional<Company> selectCompanyIdAndNameAndPhone(String memberID, String memberName, String memberPhone);

    // 기업용 회원 프로필 편집 - 비밀번호 편집 시 받는 정보로 CompanyMember 조회
    Optional<Company> selectCompanyPass(String memberID, String memberName, String memberPhone);

    // 기업용 회원 탈퇴
    void deleteCompany(Long memberNo);

}
