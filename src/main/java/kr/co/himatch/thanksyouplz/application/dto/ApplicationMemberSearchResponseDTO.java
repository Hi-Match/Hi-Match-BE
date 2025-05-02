package kr.co.himatch.thanksyouplz.application.dto;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ApplicationMemberSearchResponseDTO {
    private Long maxPage;
    private List<ApplicationMemberStatusResponseDTO> list;
}
