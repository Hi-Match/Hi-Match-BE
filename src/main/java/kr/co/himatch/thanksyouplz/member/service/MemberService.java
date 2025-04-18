package kr.co.himatch.thanksyouplz.member.service;

import kr.co.himatch.thanksyouplz.member.dto.*;

import java.util.List;

public interface MemberService {

    // 회원가입
    MemberSignUpResponseDTO signUp(MemberSignUpRequestDTO memberSignUpRequestDTO);

    // 회원 가입 시, ID 중복 검사

    Boolean checkMemberID(MemberCheckIDRequestDTO memberCheckIDRequestDTO);

    // 일반 로그인
    // memberNo == Long == Token을 받아오므로 그냥 Long 으로 받는다.
    // 더불어, ResponseDTO가 따로 없기 때문이기도 하다.
    Long login(String memberID, String memberPass);

    // 일반 로그인 시, refresh Token 저장하기
    void memberNormalLoginRefreshToken(Long memberNo, String memberToken);

    // ID 찾기
    List<MemberFindIDResponseDTO> findID(MemberFindIDRequestDTO memberFindIDRequestDTO);

    // PW 찾기
    String findPass(MemberFindPassRequestDTO memberFindPassRequestDTO, String memberPass);

    // 프로필 편집 - 휴대폰 번호 변경
    MemberChangePhoneResponseDTO changePhone(MemberChangePhoneRequestDTO memberChangePhoneRequestDTO, Long memberNo);

    // 프로필 편집 - 메일 변경
    MemberChangeMailResponseDTO changeMail(MemberChangeMailRequestDTO memberChangeMailRequestDTO, Long memberNo);

    // 프로필 편집 - 주소 변경
//    MemberChangeAddressResponseDTO changeAddress(MemberChangeMailRequestDTO memberChangeMailRequestDTO, Long memberNo);


}
