package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class ApplicationMemberDetailResponseDTO {
    private List<ApplicationMemberDetailListResponseDTO> coverList;
    private LocalDateTime applicationDate;
}
