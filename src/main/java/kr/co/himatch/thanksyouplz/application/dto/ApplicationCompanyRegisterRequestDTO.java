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

@Schema(description = "채용 공고 등록")
@Data
@ToString
@NoArgsConstructor
public class ApplicationCompanyRegisterRequestDTO {
    @Schema(description = "공고 제목", example = "백엔드 모여라")
    private String postingTitle;
    @Schema(description = "공고 내용", example = "7명 선착순")
    private String postingContent;
    @Schema(description = "채용 분야", example = "IT직무")
    private String postingPart;
    @Schema(description = "연봉", example = "9000")
    private String postingSal;
    @Schema(description = "경력 조건", example = "3년이하")
    private String postingExperience;
    @Schema(description = "학력 조건", example = "고졸이상")
    private String postingEducation;
    @Schema(description = "근무 지역", example = "서울")
    private String postingLocation;
    @Schema(description = "고용 형태", example = "정규직")
    private String postingType;
    @Schema(description = "근무 형태", example = "자율출퇴근")
    private String postingWorkType;
    @Schema(description = "근무 시작 시간", example = "20240212")
    private LocalDateTime postingWorkStartTime;
    @Schema(description = "근무 종료 시간", example = "20240212")
    private LocalDateTime postingWorkEndTime;
    @Schema(description = "마감 여부", example = "false")
    private Boolean postingIsFinish;
    @Schema(description = "마감일", example = "20240212")
    private LocalDateTime postingDeadLine;
    @Schema(description = "채용 질문 목록")
    private List<ApplicationCompanyRegisterListRequestDTO> postingQuestion;

    @JsonProperty("postingWorkStartTime")
    public void setPostingWorkStartTime(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.postingWorkStartTime = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.postingWorkStartTime = null;
        }
    }
    @JsonProperty("postingWorkEndTime")
    public void setPostingWorkEndTime(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.postingWorkEndTime = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.postingWorkEndTime = null;
        }
    }
    @JsonProperty("postingDeadLine")
    public void setPostingDeadLine(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.postingDeadLine = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.postingDeadLine = null;
        }
    }
}
