package kr.co.himatch.thanksyouplz.member.controller;

import jakarta.validation.Valid;
import kr.co.himatch.thanksyouplz.config.AuthConfig;
import kr.co.himatch.thanksyouplz.member.dto.MemberPhoneRequestDTO;
import kr.co.himatch.thanksyouplz.member.dto.MemberPhoneResponseDTO;
import kr.co.himatch.thanksyouplz.member.dto.MemberSignUpRequestDTO;
import kr.co.himatch.thanksyouplz.member.dto.MemberSignUpResponseDTO;
import kr.co.himatch.thanksyouplz.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 휴대폰 인증번호 발신
    public static DefaultMessageService messageService;

//     관리자가 휴대폰 인증을 보내기 위한 것
    @Autowired
    private AuthConfig authConfig;

//     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//     여기서부터 휴대폰 인증 Controller
    @Autowired
    public void setDefaultMessageService(AuthConfig authConfig) {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        messageService = NurigoApp.INSTANCE.initialize(authConfig.getCoolsmsapikey(), authConfig.getCoolsmssecretkey(), "https://api.coolsms.co.kr");
    }

    // 회원가입 - 휴대폰 인증 시 6자리 랜덤 숫자 생성
    public static int generateAuthNo1() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

    // 휴대폰 인증
    @PostMapping("/phone")
    public ResponseEntity<?> phone(@RequestBody MemberPhoneRequestDTO memberPhoneRequestDTO) {
        log.info("호출된 API: {}", memberPhoneRequestDTO);

        Message message = new Message();
        int randomPhone = generateAuthNo1();//46번째 줄 만들어둔 변수를 가져온 것임

        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(authConfig.getPhone());
        message.setTo(memberPhoneRequestDTO.getMemberPhone());//memberphone에 있는 memberphone에 보낼거야
        message.setText("[Hi-Match] 본인확인 인증번호는 [" + randomPhone + "] 입니다. 인증번호를 입력해주세요.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        MemberPhoneResponseDTO memberPhoneResponseDTO = new MemberPhoneResponseDTO();
        memberPhoneResponseDTO.setAuthenticationNumber(String.valueOf(randomPhone));

        log.info("호출된 API: {}", memberPhoneResponseDTO);
        return new ResponseEntity<MemberPhoneResponseDTO>(memberPhoneResponseDTO, HttpStatus.OK);
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberSignUpRequestDTO memberSignUpRequestDTO){
        MemberSignUpResponseDTO memberSignUpResponseDTO = memberService.signUp(memberSignUpRequestDTO);
        return new ResponseEntity<>(memberSignUpResponseDTO, HttpStatus.OK);
    }
}
