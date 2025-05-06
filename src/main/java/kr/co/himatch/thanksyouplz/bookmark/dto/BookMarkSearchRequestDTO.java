package kr.co.himatch.thanksyouplz.bookmark.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookMarkSearchRequestDTO {
    private String keyword;
    private Long page;
}
