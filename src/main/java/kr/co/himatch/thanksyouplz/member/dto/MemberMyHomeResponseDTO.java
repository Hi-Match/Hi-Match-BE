package kr.co.himatch.thanksyouplz.member.dto;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberCountResponseDTO;
import kr.co.himatch.thanksyouplz.bookmark.dto.BookMarkListResponseDTO;
import kr.co.himatch.thanksyouplz.resume.dto.ResumeListResponseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MemberMyHomeResponseDTO {
    private List<BookMarkListResponseDTO> bookmark;
    private MemberMyHomeCodeResponseDTO code;
    private ApplicationMemberCountResponseDTO application;
    private List<ResumeListResponseDTO> resume;
}
