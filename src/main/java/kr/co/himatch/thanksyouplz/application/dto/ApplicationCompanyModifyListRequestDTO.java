package kr.co.himatch.thanksyouplz.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "채용 공고 질문 리스트")
@Data
@ToString
public class ApplicationCompanyModifyListRequestDTO {
    @Schema(description = "질문", example = "지원동기는?")
    private String question;
    @Schema(description = "자소서 글자수", example = "1000")
    private Integer questionLength;
}
