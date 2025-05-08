package kr.co.himatch.thanksyouplz.code.dto;

import kr.co.himatch.thanksyouplz.code.util.PersonalTypeEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CodeCompanyRegisterRequestDTO {
    private PersonalTypeEnum code;
}
