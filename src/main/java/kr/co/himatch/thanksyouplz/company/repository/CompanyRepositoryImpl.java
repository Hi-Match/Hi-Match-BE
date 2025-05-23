package kr.co.himatch.thanksyouplz.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.company.dto.CompanySelectLicenseResponseDTO;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static kr.co.himatch.thanksyouplz.company.entity.QCompany.*;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    // 회원 가입 시, ID 중복 검사
    @Override
    public Optional<String> selectMemberID(String memberID) {
        return Optional.ofNullable(
                queryFactory.select(company.companyID)
                        .from(company)
                        .where(company.companyID.eq(memberID))
                        .fetchFirst()
        );
    }

    // 기업용 일반 로그인
    @Override
    public Company selectID(String memberID) {
        return queryFactory.select(company)
                .from(company)
                .where(company.companyID.eq(memberID))
                .fetchFirst();
    }

    // 기업용 회원 ID 찾기
    @Override
    public List<Company> selectMemberNameAndLicenseNumber(String memberName, String licenseNumber) {
        return queryFactory.select(company)
                .from(company)
                .where(company.companyManagerName.eq(memberName).and(company.companyLicense.eq(licenseNumber)))
                .fetch();
    }

    // 기업용 회원 PW 찾기
    @Override
    public Optional<Company> selectCompanyIdAndNameAndPhone(String memberID, String memberName, String memberPhone) {
        return Optional.ofNullable(
                queryFactory.select(company)
                        .from(company)
                        .where(company.companyID.eq(memberID)
                                .and(company.companyManagerName.eq(memberName).and(company.companyPhone.eq(memberPhone))))
                        .fetchFirst()
        );
    }

    // 기업용 회원 탈퇴
    @Override
    public void deleteCompany(Long memberNo) {
        queryFactory.delete(company)
                .where(company.companyNo.eq(memberNo))
                .execute();
    }
    
    // 기업용 상세 조회
    @Override
    public Optional<Company> selectCompanyDetail(Long memberNo) {
        return Optional.ofNullable(
                queryFactory.select(company)
                        .from(company)
                        .where(company.companyNo.eq(memberNo))
                        .fetchFirst()
        );
    }

    // 사업자 등록번호 조회
    @Override
    public String selectByMemberNo(Long memberNo) {
        return queryFactory.select(company.companyLicense)
                .from(company)
                .where(company.companyNo.eq(memberNo))
                .fetchFirst();
    }
}
