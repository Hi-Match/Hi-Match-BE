package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.entity.Company;

import java.util.Optional;

public interface CompanyRepositoryCustom {
    // 회원 가입 시, ID 중복 검사
    Optional<String> selectMemberID(String memberID);

    // 기업용 일반 로그인
    Company selectID(String memberID);
}
