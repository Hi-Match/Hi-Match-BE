package kr.co.himatch.thanksyouplz.code.util;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PersonalTypeVO {
    private String title;
    private List<String> detailContent;

    public PersonalTypeVO(String title, List<String> detailContent) {
        this.title = title;
        this.detailContent = detailContent;
    }
}
