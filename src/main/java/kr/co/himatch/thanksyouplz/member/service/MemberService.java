package kr.co.himatch.thanksyouplz.member.service;

import kr.co.himatch.thanksyouplz.member.dto.*;

import java.util.List;

public interface MemberService {

    // 회원가입
    MemberSignUpResponseDTO signUp(MemberSignUpRequestDTO memberSignUpRequestDTO);

    // 회원 가입 시, ID 중복 검사

    Boolean checkMemberID(MemberCheckIDRequestDTO memberCheckIDRequestDTO);

    // 일반 로그인
    Long login(String memberID, String memberPass);

    // ID 찾기
    List<MemberFindIDResponseDTO> findID(MemberFindIDRequestDTO memberFindIDRequestDTO);

    // PW 찾기
    MemberFindPassResponseDTO findPass(MemberFindPassRequestDTO memberFindPassRequestDTO);

}
