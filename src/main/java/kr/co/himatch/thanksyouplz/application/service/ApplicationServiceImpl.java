package kr.co.himatch.thanksyouplz.application.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberCountResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberPageResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberStatusResponseDTO;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import kr.co.himatch.thanksyouplz.application.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationAwardRepository applicationAwardRepository;
    @Autowired
    private ApplicationCertificateRepository applicationCertificateRepository;
    @Autowired
    private ApplicationEducationRepository applicationEducationRepository;
    @Autowired
    private ApplicationExperienceRepository applicationExperienceRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationSchoolRepository applicationSchoolRepository;
    @Autowired
    private CompanyQuestionsRepository companyQuestionsRepository;
    @Autowired
    private CoverLetterCategoryRepository coverLetterCategoryRepository;
    @Autowired
    private CoverLetterRepository coverLetterRepository;
    @Autowired
    private JobPostingRepository jobPostingRepository;

    // 지원서 상태에 따른 max page
    @Override
    public ApplicationMemberPageResponseDTO selectMaxPage(ApplicationStatus applicationStatus, Long memberNo) {
        long count = applicationRepository.selectMaxPageByApplicationStatus(applicationStatus, memberNo);
        long maxPage = (long) Math.ceil((double) count / 10);
        ApplicationMemberPageResponseDTO pageResponseDTO = new ApplicationMemberPageResponseDTO();
        pageResponseDTO.setMaxPage(maxPage);
        return pageResponseDTO;
    }

    // 지원서 상태에 따른 count
    @Override
    public ApplicationMemberCountResponseDTO selectCountByStatus(Long memberNo) {
        Long total = applicationRepository.selectCountByApplicationAllStatus(memberNo);
        Long submit = applicationRepository.selectCountByApplicationStatus(ApplicationStatus.SUBMIT, memberNo);
        Long progress = applicationRepository.selectCountByApplicationStatus(ApplicationStatus.PROGRESS, memberNo);
        Long resumePass = applicationRepository.selectCountByApplicationStatus(ApplicationStatus.RESUME_PASS, memberNo);
        Long finalPass = applicationRepository.selectCountByApplicationStatus(ApplicationStatus.FINAL_PASS, memberNo);
        Long fail = applicationRepository.selectCountByApplicationStatus(ApplicationStatus.FAIL, memberNo);

        ApplicationMemberCountResponseDTO countResponseDTO = new ApplicationMemberCountResponseDTO();
        countResponseDTO.setTotal(total);
        countResponseDTO.setSubmit(submit);
        countResponseDTO.setProgress(progress);
        countResponseDTO.setResumePass(resumePass);
        countResponseDTO.setFinalPass(finalPass);
        countResponseDTO.setFail(fail);
        return countResponseDTO;
    }

    // 지원서 상태에 따른 지원서 조회
    @Override
    public List<ApplicationMemberStatusResponseDTO> selectPageByStatus(ApplicationStatus applicationStatus, Long memberNo, Long page) {
        if (page >= 1) {
            page--;
        }
        return applicationRepository.selectPageByStatus(applicationStatus, memberNo, page);
    }
}
