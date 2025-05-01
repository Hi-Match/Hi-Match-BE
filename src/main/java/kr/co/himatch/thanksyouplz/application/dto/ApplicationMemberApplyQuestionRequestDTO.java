package kr.co.himatch.thanksyouplz.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(description = "채용 공고 지원 질문 요청 DTO")
public class ApplicationMemberApplyQuestionRequestDTO {

    @Schema(description = "질문 내용", example = "지원 동기는 무엇인가요?")
    private String question;

    @Schema(description = "질문 길이 제한", example = "500")
    private Integer questionLength;

    @Schema(description = "질문 답변 내용", example = "저는 귀사의 ... 에 깊은 감명을 받아 ...")
    private String questionContent;
}