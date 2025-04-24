package kr.co.himatch.thanksyouplz.company.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.company.dto.CompanyInfoTagResponseDTO;
import kr.co.himatch.thanksyouplz.company.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<CompanyInfoTagResponseDTO> tagList() {
        return tagRepository.selectTags();
    }
}
