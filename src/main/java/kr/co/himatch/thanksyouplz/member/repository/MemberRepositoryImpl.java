package kr.co.himatch.thanksyouplz.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.entity.SocialType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import static kr.co.himatch.thanksyouplz.member.entity.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    // 회원 가입 시, ID 중복 검사
    @Override
    public Optional<String> selectMemberID(String memberID) {
        return Optional.ofNullable(

                queryFactory.select(member.memberID)
                        .from(member)
                        .where(member.memberID.eq(memberID))
                        .fetchFirst()
        );
    }

    // 일반 로그인
    @Override
    public Member selectId(String memberID) {
        return queryFactory.select(member)
                .from(member)
                .where(member.memberID.eq(memberID))
                .fetchFirst();
    }

    // ID 찾기
    @Override
    public List<Member> selectMemberNameAndMemberPhone(String memberName, String memberPhone) {
        return queryFactory.select(member)
                .from(member)
                .where(member.memberName.eq(memberName).and(member.memberPhone.eq(memberPhone)))
                .fetch();
    }

    // PW 찾기
    @Override
    public Optional<Member> selectMemberIdAndNameAndPhone(String memberID, String memberName, String memberPhone) {
        return Optional.ofNullable(
                queryFactory.select(member)
                        .from(member)
                        .where(member.memberID.eq(memberID).and(member.memberName.eq(memberName).and(member.memberPhone.eq(memberPhone))))
                        .fetchFirst()
        );
    }

    // 소셜 로그인 시 사용 - Token 받아오기
    @Override
    public Optional<String> selectTokenByMemberNo(Long memberNo) {
        return Optional.ofNullable(
                queryFactory.select(member.memberRefreshToken)
                        .from(member)
                        .where(member.memberNo.eq(memberNo))
                        .where().fetchFirst());
    }

    // 소셜ID로 MemberNo를 가져오는 것
    // 소셜로그인시 kakao. google. naver. github중 무엇인지 파악한 뒤, 첫 로그인인지 판별
    @Override
    public Optional<Long> selectMemberBySocialId(SocialType registrationId, String socialId) {
        return Optional.ofNullable(
                queryFactory.select(member.memberNo)
                        .from(member)
                        .where(member.memberSocialID.eq(socialId).and(member.socialType.eq(registrationId)))
                        .where().fetchFirst());
    }

    // 비밀번호 변경, 혹은 찾을 때 받는 정보로 Member 조회
    @Override
    public Optional<Member> selectPass(String memberID, String memberName, String memberPhone) {
        return Optional.ofNullable(
                queryFactory.select(member)
                        .from(member)
                        .where(member.memberID.eq(memberID).and(member.memberName.eq(memberName).and(member.memberPhone.eq(memberPhone))))
                        .fetchFirst()
        );
    }

    // 회원 탈퇴
    @Override
    public void deleteMember(Long memberNo) {
        queryFactory.delete(member)
                .where(member.memberNo.eq(memberNo))
                .execute();
    }

    // 마이페이지 접속 시, 회원 정보
    @Override
    public Optional<Member> selectMemberInfo(Long memberNo) {
        return Optional.ofNullable(
                queryFactory.select(member)
                        .from(member)
                        .where(member.memberNo.eq(memberNo))
                        .fetchFirst()
        );
    }
}
