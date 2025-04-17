package kr.co.himatch.thanksyouplz.company.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyLicenseResponseDTO {
    @JsonProperty("match_cnt")
    private Integer matchCnt;
    public boolean hasMatchCnt() {
        return this.matchCnt != null;
    }
}
