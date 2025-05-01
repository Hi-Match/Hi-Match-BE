package kr.co.himatch.thanksyouplz.application.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.application.dto.*;
import kr.co.himatch.thanksyouplz.application.entity.Application;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import kr.co.himatch.thanksyouplz.application.entity.CompanyQuestions;
import kr.co.himatch.thanksyouplz.application.entity.JobPosting;
import kr.co.himatch.thanksyouplz.application.repository.*;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private CompanyRepository companyRepository;

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

    // 지원서 상세 보기
    @Override
    public ApplicationMemberDetailResponseDTO selectApplicationDetail(Long applicationNo) {
        Application application = applicationRepository.getReferenceById(applicationNo);
        List<ApplicationMemberDetailListResponseDTO> coverList = coverLetterRepository.selectQuestionListByApplicationNo(applicationNo);

        ApplicationMemberDetailResponseDTO detailResponseDTO = new ApplicationMemberDetailResponseDTO();
        detailResponseDTO.setApplicationDate(application.getApplicationDate());
        detailResponseDTO.setCoverList(coverList);
        return detailResponseDTO;
    }

    // 기업이 등록한 채용 공고 목록 조회
    @Override
    public List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo) {
        return jobPostingRepository.selectPostingList(memberNo);
    }

    @Override
    public ApplicationCompanySelectResponseDTO selectJobPosting(Long postingNo) {
        JobPosting posting = jobPostingRepository.getReferenceById(postingNo);
        List<ApplicationCompanySelectListResponseDTO> questions = companyQuestionsRepository.selectQuestionByPostingNo(postingNo);

        ApplicationCompanySelectResponseDTO responseDTO = new ApplicationCompanySelectResponseDTO();
        responseDTO.setPostingTitle(posting.getPostingTitle());
        responseDTO.setPostingPart(posting.getPostingPart());
        responseDTO.setPostingSal(posting.getPostingSal());
        responseDTO.setPostingExperience(posting.getPostingExperience());
        responseDTO.setPostingEducation(posting.getPostingEducation());
        responseDTO.setPostingLocation(posting.getPostingLocation());
        responseDTO.setPostingType(posting.getPostingType());
        responseDTO.setPostingWorkType(posting.getPostingWorkType());
        responseDTO.setPostingWorkStartTime(posting.getPostingWorkStartTime());
        responseDTO.setPostingWorkEndTime(posting.getPostingWorkEndTime());
        responseDTO.setPostingIsFinish(posting.getPostingIsFinish());
        responseDTO.setPostingDeadLine(posting.getPostingDeadline());
        responseDTO.setPostingQuestion(questions);

        return responseDTO;
    }

    // 채용 공고 등록
    @Override
    public ApplicationCompanyRegisterResponseDTO postingRegister(ApplicationCompanyRegisterRequestDTO registerRequestDTO, Long memberNo) {
        Company company = companyRepository.getReferenceById(memberNo);
        JobPosting jobPosting = jobPostingRepository.save(JobPosting
                .builder()
                .companyNo(company)
                .postingTitle(registerRequestDTO.getPostingTitle())
                .postingContent(registerRequestDTO.getPostingContent())
                .postingPart(registerRequestDTO.getPostingPart())
                .postingSal(registerRequestDTO.getPostingSal())
                .postingExperience(registerRequestDTO.getPostingExperience())
                .postingEducation(registerRequestDTO.getPostingEducation())
                .postingLocation(registerRequestDTO.getPostingLocation())
                .postingType(registerRequestDTO.getPostingType())
                .postingWorkType(registerRequestDTO.getPostingWorkType())
                .postingWorkStartTime(registerRequestDTO.getPostingWorkStartTime())
                .postingWorkEndTime(registerRequestDTO.getPostingWorkEndTime())
                .postingIsFinish(false)
                .postingDeadline(registerRequestDTO.getPostingDeadLine())
                .postingCreate(LocalDateTime.now())
                .build());

        for (ApplicationCompanyRegisterListRequestDTO question : registerRequestDTO.getPostingQuestion()) {
            companyQuestionsRepository.save(
                    CompanyQuestions
                            .builder()
                            .postingNo(jobPosting)
                            .questionTitle(question.getQuestion())
                            .questionLength(question.getQuestionLength())
                            .build()
            );
        }


        ApplicationCompanyRegisterResponseDTO registerResponseDTO = new ApplicationCompanyRegisterResponseDTO();
        registerResponseDTO.setMessage("Success");
        return registerResponseDTO;
    }

    // 채용 공고 수정
    @Override
    public ApplicationCompanyModifyResponseDTO postingModify(ApplicationCompanyModifyRequestDTO modifyRequestDTO, Long memberNo) {
        JobPosting jobPosting = jobPostingRepository.getReferenceById(modifyRequestDTO.getPostingNo());
        if (!jobPosting.getCompanyNo().getCompanyNo().equals(memberNo)) {
            return null;
        }

        jobPosting.changePostingInfo(modifyRequestDTO.getPostingTitle(), modifyRequestDTO.getPostingContent(),
                modifyRequestDTO.getPostingPart(), modifyRequestDTO.getPostingSal(),
                modifyRequestDTO.getPostingExperience(), modifyRequestDTO.getPostingEducation(),
                modifyRequestDTO.getPostingLocation(), modifyRequestDTO.getPostingType(),
                modifyRequestDTO.getPostingWorkType(), modifyRequestDTO.getPostingWorkStartTime(),
                modifyRequestDTO.getPostingWorkEndTime(), modifyRequestDTO.getPostingIsFinish(),
                modifyRequestDTO.getPostingDeadLine());

        companyQuestionsRepository.deleteQuestionByPostingNo(modifyRequestDTO.getPostingNo());

        for (ApplicationCompanyModifyListRequestDTO question : modifyRequestDTO.getPostingQuestion()) {
            companyQuestionsRepository.save(
                    CompanyQuestions
                            .builder()
                            .postingNo(jobPosting)
                            .questionTitle(question.getQuestion())
                            .questionLength(question.getQuestionLength())
                            .build()
            );
        }

        ApplicationCompanyModifyResponseDTO modifyResponseDTO = new ApplicationCompanyModifyResponseDTO();
        modifyResponseDTO.setMessage("Success");
        return modifyResponseDTO;
    }

    @Override
    public ApplicationCompanyDeleteResponseDTO posingDelete(Long memberNo, Long postingNo) {
        JobPosting jobPosting = jobPostingRepository.getReferenceById(postingNo);
        if (!jobPosting.getCompanyNo().getCompanyNo().equals(memberNo)) {
            return null;
        }

        companyQuestionsRepository.deleteQuestionByPostingNo(postingNo);
        jobPostingRepository.delete(jobPosting);

        ApplicationCompanyDeleteResponseDTO deleteResponseDTO = new ApplicationCompanyDeleteResponseDTO();
        deleteResponseDTO.setMessage("Success");
        return deleteResponseDTO;
    }
}
