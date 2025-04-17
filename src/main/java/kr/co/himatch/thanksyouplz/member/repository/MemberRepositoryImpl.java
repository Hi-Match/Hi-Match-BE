package kr.co.himatch.thanksyouplz.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.member.entity.Member;
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
    public String selectMemberPass(String memberID, String memberName, String memberPhone) {
        return queryFactory.select(member.memberPass)
                .from(member)
                .where(member.memberID.eq(memberID).and(member.memberName.eq(memberName).and(member.memberPhone.eq(memberPhone))))
                .fetchFirst();
    }
}
