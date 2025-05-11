package kr.co.himatch.thanksyouplz.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Schema(description = "채용 공고 조회")
@Data
@ToString
public class ApplicationCompanySelectResponseDTO {
    private Long companyNo;
    private String companyName;
    private String postingTitle;
    private String postingPart;
    private Integer postingSal;
    private String postingExperience;
    private String postingEducation;
    private String postingLocation;
    private String postingType;
    private String postingWorkType;
    private LocalDateTime postingWorkStartTime;
    private LocalDateTime postingWorkEndTime;
    private Boolean postingIsFinish;
    private LocalDateTime postingDeadLine;
    private String companyImgA;
    private String companyImgB;
    private String companyImgC;
    private String postingContent;
    private List<String> tags;
    private List<ApplicationCompanySelectListResponseDTO> postingQuestion;

}
