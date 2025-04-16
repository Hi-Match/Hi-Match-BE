package kr.co.himatch.thanksyouplz.member.service;

import kr.co.himatch.thanksyouplz.member.dto.MemberSignUpRequestDTO;
import kr.co.himatch.thanksyouplz.member.dto.MemberSignUpResponseDTO;

public interface MemberService {

    // 회원가입
    MemberSignUpResponseDTO signUp(MemberSignUpRequestDTO memberSignUpRequestDTO);
}
