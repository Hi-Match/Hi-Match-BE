package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationMemberCountResponseDTO {
    private Long total;
    private Long submit;
    private Long progress;
    private Long resumePass;
    private Long finalPass;
    private Long fail;
}
