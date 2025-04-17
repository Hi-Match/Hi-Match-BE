package kr.co.himatch.thanksyouplz.member.repository;

import kr.co.himatch.thanksyouplz.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    // 회원 가입 시, ID 중복 검사
    Optional<String> selectMemberID(String memberID);

    // 일반 로그인
    Member selectId(String memberID);

    // ID 찾기
    List<Member> selectMemberNameAndMemberPhone(String memberName, String memberPhone);

    // PW 찾기
    String selectMemberPass(String memberID, String memberName, String memberPhone);
}
