package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.dto.CompanySelectLicenseResponseDTO;
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

    // 기업용 회원 탈퇴
    void deleteCompany(Long memberNo);

    Optional<Company> selectCompanyDetail(Long memberNo);

    // 사업자 등록번호 조회
    String selectByMemberNo(Long memberNo);

}
