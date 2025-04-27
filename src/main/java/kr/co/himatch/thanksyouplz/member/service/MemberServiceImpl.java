package kr.co.himatch.thanksyouplz.member.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.member.dto.*;
import kr.co.himatch.thanksyouplz.member.entity.MemberLog;
import kr.co.himatch.thanksyouplz.member.repository.MemberLogRepository;
import lombok.extern.slf4j.Slf4j;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.entity.SocialType;
import kr.co.himatch.thanksyouplz.member.repository.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberLogRepository memberLogRepository;

    //닉네임 배열을 위한
    String[] nick1 = new String[]{"성실한", "재밌는", "유쾌한", "밝은", "목마른", "배아픈", "청량한", "심심한", "차가운", "지루한", "불안한", "행복한",
            "추위를 많이 타는", "용감한", "서글픈", "웃고있는", "감성적인", "이성적인", "말이 많은", "무뚝뚝한", "쾌활한", "신나는", "뒤집어진", "걸어가는",
            "생각하는", "배고픈", "질서를 중요시하는", "개구장이", "귀여운", "단호한", "소심한", "잠만보", "감기에 걸린", "맛있는 걸 좋아하는", "특이한",
            "재치있는", "배고픔을 못참는", "독특한", "과자러버", "디저트를 사랑하는", "빵순이", "빵돌이", "한식이 좋은", "양식이 좋은", "나는", "준비된",
            "여름이 좋은", "겨울이 좋은", "졸린", "동그란", "예민한", "마음이 고운", "사람이 좋은", "게임중독", "긴장한", "더위를 많이타는", "운동하는",
            "긴장한", "울랄라", "즐거울까말까", "해피니스", "혼자가 아닌", "우울하지 않은", "질투쟁이", "새침한", "까탈레나", "까다로운", "오늘은", "내일은",
            "일주일 뒤에", "물을 좋아하는", "수영하는", "배영하는", "접영하는", "평형하는", "태권도를 사랑하는", "한국의", "여행가고싶은", "커피를 좋아하는",
            "사랑의", "행복의", "빠른", "여유로운", "조용한", "시끄러운", "공연예정인", "틱토커", "인플루언서", "요즘 핫플"
    };
    String[] nick2 = new String[]{"지원자", "면접자", "학생", "취준생", "교육생", "천재", "마케터", "개발자", "선생님", "회계사", "변리사", "노무사",
            "교사", "인턴", "정직원", "기획자", "사원", "대리", "책임", "주임", "과장", "차장", "부장", "팀장", "연구원", "선임연구원", "사업가",
            "디자이너", "퍼블리셔", "세무사", "판사", "검사", "외교관", "서기", "군인", "요리사", "PM", "공무원", "의사", "판매원", "매니저",
            "모델", "감독관", "가수", "연습생", "제빵사", "CTO", "CEO", "파티쉐", "소믈리에", "상담사", "간호사", "무역가", "관세사", "기자",
            "리포터", "네일아티스트", "도선사", "뮤지컬배우", "물리치료사", "방사선사", "보조교사", "화가", "바리스타", "미술가", "PD",
            "강사", "교수", "대학원생", "트레이너", "프로게이머", "프로그래머", "컨설턴트", "애널리스트", "호텔지배인", "집사", "한의사", "파일럿",
            "UX디자이너", "감정평가사", "경비원", "경마기수", "경비원", "웨딩플래너", "해설가", "통역가", "사서", "빅데이터분석가", "데이터분석가",
            "사이버범죄수사관", "형사", "경찰", "프로파일러", "사진가", "포토그래퍼", "큐레이터", "환경운동가", "자선사", "작가", "웹툰작가",
            "은행가", "음악가", "작곡가", "작사가", "기타리스트", "시나리오작가", "식물원관리사", "퍼스널쇼퍼", "관리자", "농부",
    };


    // 회원 가입
    @Override
    public MemberSignUpResponseDTO signUp(MemberSignUpRequestDTO memberSignUpRequestDTO) {

        //랜덤숫자태그
        int randomTag = (int) (Math.random() * (99999 - 10000 + 1)) + 10000;

        Member memberSignUp = memberRepository.save(
                Member.builder()
                        .memberID(memberSignUpRequestDTO.getMemberID())
                        .memberPass(
                                BCrypt.hashpw(
                                        memberSignUpRequestDTO.getMemberPass(), BCrypt.gensalt()
                                )
                        )
                        .memberName(memberSignUpRequestDTO.getMemberName())
                        .memberMail(memberSignUpRequestDTO.getMemberMail())
                        .memberPhone(memberSignUpRequestDTO.getMemberPhone())
                        .socialType(SocialType.NORMAL)
                        .memberJoinDate(LocalDateTime.now())
                        .memberNickName(nick1[(int) (Math.random() * nick1.length)] + nick2[(int) (Math.random() * nick2.length)])
                        .memberRandom("#" + randomTag)
                        .build()
        );

        MemberSignUpResponseDTO memberSignUpResponseDTO = new MemberSignUpResponseDTO();
        memberSignUpResponseDTO.setMemberID(memberSignUp.getMemberID());
        memberSignUpResponseDTO.setMemberName(memberSignUp.getMemberName());
        memberSignUpResponseDTO.setMemberNickName(memberSignUp.getMemberNickName());
        memberSignUpResponseDTO.setMemberRandom(memberSignUp.getMemberRandom());
        memberSignUpResponseDTO.setMemberJoinDate(memberSignUp.getMemberJoinDate());

        return memberSignUpResponseDTO;
    }

    // 회원 가입 시, ID 중복 검사
    @Override
    public Boolean checkMemberID(MemberCheckIDRequestDTO memberCheckIDRequestDTO) {
        Optional<String> selectMemberID = memberRepository.selectMemberID(memberCheckIDRequestDTO.getMemberID());

        if (selectMemberID.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // 일반 로그인
    @Override
    public Long login(String memberID, String memberPass) {
        Member selectId = memberRepository.selectId(memberID);

        if (selectId == null) {
            return null;
        } else {
            if (BCrypt.checkpw(memberPass, selectId.getMemberPass())) {
                return selectId.getMemberNo();
            } else {
                return null;
            }
        }
    }

    // 일반 로그인 시 refreshToken 저장
    @Override
    public void memberNormalLoginRefreshToken(Long memberNo, String memberToken) {
        Member member = memberRepository.getReferenceById(memberNo);
        member.changeToken(memberToken);
    }

    // ID 찾기
    @Override
    public List<MemberFindIDResponseDTO> findID(MemberFindIDRequestDTO memberFindIDRequestDTO) {
        List<Member> findID = memberRepository.selectMemberNameAndMemberPhone(
                memberFindIDRequestDTO.getMemberName(), memberFindIDRequestDTO.getMemberPhone());

        List<MemberFindIDResponseDTO> memberFindIDResponseDTO = findID.stream().map(this::findListID).toList();
        return memberFindIDResponseDTO;
    }

    // ID 찾기 이어서
    public MemberFindIDResponseDTO findListID(Member member) {
        MemberFindIDResponseDTO memberFindIDResponseDTO = new MemberFindIDResponseDTO();

        memberFindIDResponseDTO.setMemberID(member.getMemberID());
        memberFindIDResponseDTO.setMemberJoinDate(member.getMemberJoinDate());
        return memberFindIDResponseDTO;
    }

    // PW 찾기
    @Override
    public String findPass(MemberFindPassRequestDTO memberFindPassRequestDTO, String memberPass) {
        Optional<Member> findPass = memberRepository.selectMemberIdAndNameAndPhone(memberFindPassRequestDTO.getMemberID(),
                memberFindPassRequestDTO.getMemberName(), memberFindPassRequestDTO.getMemberPhone());

        if (findPass.isEmpty()){
            return null;
        }else{

            // 유저의 비밀번호를 임시 비멀번호로 변경한다.
            findPass.get().temporaryChangePass(memberPass);
            return findPass.get().getMemberMail();
        }
    }

    // 프로필 편집 - 휴대폰 번호 변경
    @Override
    public MemberChangePhoneResponseDTO changePhone(MemberChangePhoneRequestDTO memberChangePhoneRequestDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        member.changePhone(memberChangePhoneRequestDTO.getMemberPhone());

        MemberChangePhoneResponseDTO memberChangePhoneResponseDTO = new MemberChangePhoneResponseDTO();
        memberChangePhoneResponseDTO.setMessage("Success!");

        return memberChangePhoneResponseDTO;
    }

    // 프로필 편집 - 메일 변경
    @Override
    public MemberChangeMailResponseDTO changeMail(MemberChangeMailRequestDTO memberChangeMailRequestDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        member.changeMail(memberChangeMailRequestDTO.getMemberMail());

        MemberChangeMailResponseDTO memberChangeMailResponseDTO = new MemberChangeMailResponseDTO();
        memberChangeMailResponseDTO.setMessage("Success!");

        return memberChangeMailResponseDTO;
    }

    // 프로필 편집 - 비밀번호 변경
    @Override
    public MemberChangePassResponseDTO changePass(MemberChangePassRequestDTO memberChangePassRequestDTO) {
        Optional<Member> selectPass = memberRepository.selectPass(memberChangePassRequestDTO.getMemberID(),
                memberChangePassRequestDTO.getMemberName(), memberChangePassRequestDTO.getMemberPhone());

        if (selectPass.isEmpty()){
            return null;
        }else{
            selectPass.get().changePass(BCrypt.hashpw(memberChangePassRequestDTO.getMemberPass(), BCrypt.gensalt()));

            MemberChangePassResponseDTO memberChangePassResponseDTO = new MemberChangePassResponseDTO();
            memberChangePassResponseDTO.setMessage("Success!");

            return memberChangePassResponseDTO;
        }
    }

    // 회원 탈퇴
    @Override
    public MemberDeleteResponseDTO memberDelete(Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);

        // 회원 탈퇴 전, Member Log Table로 data를 옮긴다.
        MemberLog memberLog = memberLogRepository.save(
                MemberLog.builder()
                        .memberID(member.getMemberID())
                        .memberPass(member.getMemberPass())
                        .memberName(member.getMemberName())
                        .memberMail(member.getMemberMail())
                        .memberPhone(member.getMemberPhone())
                        .socialType(member.getSocialType())
                        .memberSocialID(member.getMemberSocialID())
                        .memberRefreshToken(member.getMemberRefreshToken())
                        .memberNickName(member.getMemberNickName())
                        .memberRandom(member.getMemberRandom())
                        .memberFile(member.getMemberFile())
                        .memberJoinDate(member.getMemberJoinDate())
                        .memberCompanyAddress(member.getMemberCompanyAddress())
                        .memberCompanyPart(member.getMemberCompanyPart())
                        .memberCompanyContract(member.getMemberCompanyContract())
                        .memberCode(member.getMemberCode())
                        .memberTestDate(member.getMemberTestDate())
                        .memberDeleteDate(LocalDateTime.now())
                        .build()
        );

        // Member Table > Member_Log Table로 data 이관 후, 삭제를 진행한다.
        memberRepository.deleteMember(memberNo);

        MemberDeleteResponseDTO memberDeleteResponseDTO = new MemberDeleteResponseDTO();
        memberDeleteResponseDTO.setMessage("Success!");

        return memberDeleteResponseDTO;

    }

    // 마이 페이지 접속 시 제공하는 회원 정보
    @Override
    public MemberInfoResponseDTO memberInfo(Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        MemberInfoResponseDTO memberInfoResponseDTO = new MemberInfoResponseDTO();
        memberInfoResponseDTO.setMemberID(member.getMemberID());
        memberInfoResponseDTO.setMemberName(member.getMemberName());
        memberInfoResponseDTO.setMemberMail(member.getMemberMail());
        memberInfoResponseDTO.setMemberJoinDate(member.getMemberJoinDate());
        memberInfoResponseDTO.setMemberPhone(member.getMemberPhone());

        // spilt을 바로 하면 생기는 오류
        // member.getCompanyAddress가 null이면 null.spilt()이 되기 때문에 >> 오류가 남

        // 사용자가 원하는 기업의 주소지들을 배열로 입력받는다.
        // 사용자가 원하는 기업의 주소(근무지)는 여러 군데 일 수 있기 때문이다.
        if (member.getMemberCompanyAddress() == null){
            memberInfoResponseDTO.setCompanyAddress(null);
        }else{
            memberInfoResponseDTO.setCompanyAddress(Arrays.stream(member.getMemberCompanyAddress().split(",")).toList());
        }

        // 사용자가 원하는 기업의 직무들을 배열로 입력받는다.
        // 사용자가 원하는 기업의 직무 여러개일 수 있기 때문이다.
        if (member.getMemberCompanyPart() == null){
            memberInfoResponseDTO.setCompanyPart(null);
        }else{
            memberInfoResponseDTO.setCompanyPart(Arrays.stream(member.getMemberCompanyPart().split(",")).toList());
        }

        // 사용자가 원하는 기업의 고용형태들을 배열로 입력받는다.
        // 사용자가 원하는 기업의 고용형태가 다양할 수 있기 때문이다.
        if (member.getMemberCompanyContract() == null){
            memberInfoResponseDTO.setCompanyType(null);
        }else{
            memberInfoResponseDTO.setCompanyType(Arrays.stream(member.getMemberCompanyContract().split(",")).toList());
        }

        return memberInfoResponseDTO;
    }

    // 지원자가 마이페이지에서 저장하는 원하는 기업의 정보들(주소, 고용형태, 직무)
    @Override
    public MemberCompanyInfoResponseDTO memberCompanyInfo(MemberCompanyInfoRequestDTO memberCompanyInfoRequestDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);

        // List로 받아져있는 주소 정보들을 ","를 기준으로 한 String으로 합쳐준다.
        List<String> memberCompanyInfoAddress = memberCompanyInfoRequestDTO.getCompanyAddress();
        String memberWantedCompanyAddressInfo = String.valueOf("");

        if (memberCompanyInfoAddress != null && !memberCompanyInfoAddress.isEmpty()){
            memberWantedCompanyAddressInfo = String.join(",", memberCompanyInfoAddress);
        }

        // List로 받아져있는 직무 정보들을 ","를 기준으로 한 String으로 합쳐준다.
        List<String> memberCompanyInfoPart = memberCompanyInfoRequestDTO.getCompanyPart();
        String memberWantedCompanyPartInfo = String.valueOf("");

        if (memberCompanyInfoPart != null && !memberCompanyInfoPart.isEmpty()){
            memberWantedCompanyPartInfo = String.join(",", memberCompanyInfoPart);
        }

        // List로 받아져있는 고용형태 정보들을 ","를 기준으로 한 String으로 합쳐준다.
        List<String> memberCompanyInfoType = memberCompanyInfoRequestDTO.getCompanyType();
        String memberWantedCompanyTypeInfo = String.valueOf("");

        if (memberCompanyInfoType != null && !memberCompanyInfoType.isEmpty()){
            memberWantedCompanyTypeInfo = String.join(",", memberCompanyInfoType);
        }

        member.changeMemberCompanyInfo(memberWantedCompanyAddressInfo, memberWantedCompanyPartInfo, memberWantedCompanyTypeInfo);

        MemberCompanyInfoResponseDTO memberCompanyInfoResponseDTO = new MemberCompanyInfoResponseDTO();
        memberCompanyInfoResponseDTO.setMessage("Success!");

        return memberCompanyInfoResponseDTO;
    }

}
