package kr.co.himatch.thanksyouplz.code.dto;

import kr.co.himatch.thanksyouplz.code.util.PersonalTypeVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class CodeMemberResultResponseDTO {
    private String code;
    private String description;
    private CodeMemberResultRateDTO rate;
    private String slogan;
    private List<PersonalTypeVO> detail;
}