package kr.co.himatch.thanksyouplz.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class CompanyInfoRegisterRequestDTO {
    @Schema(description = "회사명", example = "CJ대한통운")
    private String companyName;

    @Schema(description = "담당자 이름", example = "김민주")
    private String companyManagerName;

    @Schema(description = "회사 주소", example = "서울특별시")
    private String companyAddress;

    @Schema(description = "회사 전화번호", example = "01012345678")
    private String companyPhone;

    @Schema(description = "회사 이메일", example = "mail@naver.com")
    private String companyMail;

    @Schema(description = "회사 업종", example = "제조업")
    private String companyIndustry;

    @Schema(description = "회사 규모 (사원수)", example = "300이상")
    private String companyEmployee;

    @Schema(description = "회사 소개", example = "오시면 좋아요")
    private String companyDescription;

    @Schema(description = "회사 로고 이미지 파일명", example = "profile.png")
    private String companyLogo;

    @Schema(description = "회사 대표 이미지A", example = "profile.png")
    private String companyImgA;

    @Schema(description = "회사 대표 이미지B", example = "profile.png")
    private String companyImgB;

    @Schema(description = "회사 대표 이미지C", example = "profile.png")
    private String companyImgC;

    private List<CompanyInfoTagRegisterDTO> tag;
}