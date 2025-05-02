package kr.co.himatch.thanksyouplz.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Schema(description = "채용 공고 지원 요청 DTO")
public class ApplicationMemberApplyRequestDTO {

    @Schema(description = "공고 번호", example = "123")
    private Long postingNo;

    @Schema(description = "이력서 번호", example = "456")
    private Long resumeNo;

    @Schema(description = "질문 목록")
    private List<ApplicationMemberApplyQuestionRequestDTO> question;
}