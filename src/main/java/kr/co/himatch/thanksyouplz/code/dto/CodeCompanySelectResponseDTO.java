package kr.co.himatch.thanksyouplz.code.dto;

import kr.co.himatch.thanksyouplz.code.util.PersonalTypeVO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CodeCompanySelectResponseDTO {

    private List<PersonalTypeVO> detail;
}
