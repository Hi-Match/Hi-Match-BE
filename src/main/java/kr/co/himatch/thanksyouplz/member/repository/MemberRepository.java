package kr.co.himatch.thanksyouplz.member.repository;

import kr.co.himatch.thanksyouplz.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
