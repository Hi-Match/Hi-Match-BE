package kr.co.himatch.thanksyouplz.bookmark.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BookMarkListRequestDTO {
    private Long page;

    public BookMarkListRequestDTO(Long page) {
        this.page = page;
    }
}
