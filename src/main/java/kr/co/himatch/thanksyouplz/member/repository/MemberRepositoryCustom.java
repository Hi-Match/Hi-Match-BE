package kr.co.himatch.thanksyouplz.member.repository;

import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.entity.SocialType;

import javax.swing.text.html.Option;
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
    Optional<Member> selectMemberIdAndNameAndPhone(String memberID, String memberName, String memberPhone);

    // 소셜 로그인 시 사용함
    Optional<String> selectTokenByMemberNo(Long memberNo);

    // 소셜ID로 MemberNo를 가져오는 것
    Optional<Long> selectMemberBySocialId(SocialType registrationId, String socialId);

    // 회원 탈퇴
    void deleteMember(Long memberNo);

    // 마이페이지 접속 시, 회원 정보
    Optional<Member> selectMemberInfo(Long memberNo);


}
