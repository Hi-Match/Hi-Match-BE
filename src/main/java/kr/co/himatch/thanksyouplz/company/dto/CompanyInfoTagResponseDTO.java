package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CompanyInfoTagResponseDTO {
    private String tagName;

    public CompanyInfoTagResponseDTO(String tagName) {
        this.tagName = tagName;
    }
}
