package kr.co.himatch.thanksyouplz.application.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.application.dto.*;
import kr.co.himatch.thanksyouplz.application.entity.*;
import kr.co.himatch.thanksyouplz.application.repository.*;
import kr.co.himatch.thanksyouplz.bookmark.entity.BookMark;
import kr.co.himatch.thanksyouplz.bookmark.repository.BookMarkRepository;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.repository.CompanyRepository;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.repository.MemberRepository;
import kr.co.himatch.thanksyouplz.resume.entity.*;
import kr.co.himatch.thanksyouplz.resume.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private ResumeAwardRepository resumeAwardRepository;
    @Autowired
    private ResumeCertificateRepository resumeCertificateRepository;
    @Autowired
    private ResumeEducationRepository resumeEducationRepository;
    @Autowired
    private ResumeExperienceRepository resumeExperienceRepository;
    @Autowired
    private ResumeSchoolRepository resumeSchoolRepository;
    @Autowired
    private BookMarkRepository bookMarkRepository;


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

    // 지원자 지원 목록 검색 조회 API
    @Override
    public ApplicationMemberSearchResponseDTO selectSearchPageByStatus(ApplicationMemberSearchRequestDTO requestDTO, Long memberNo) {
        Long page = requestDTO.getPage();
        if (page >= 1) {
            page--;
        }

        Long maxPage = applicationRepository.selectPageSearchCountByStatus(requestDTO.getKeyword(), requestDTO.getCategory(), memberNo);
        List<ApplicationMemberStatusResponseDTO> list = applicationRepository.selectPageSearchByStatus(requestDTO.getKeyword(), requestDTO.getCategory(), page, memberNo);

        ApplicationMemberSearchResponseDTO responseDTO = new ApplicationMemberSearchResponseDTO();
        responseDTO.setMaxPage(maxPage);
        responseDTO.setList(list);
        return responseDTO;
    }

    // 지원서 상태에 따른 지원서 조회
    @Override
    public List<ApplicationMemberStatusResponseDTO> selectPageByStatus(ApplicationStatus applicationStatus, Long memberNo, Long page) {
        if (page >= 1) {
            page--;
        }
        return applicationRepository.selectPageByStatus(applicationStatus, memberNo, page);
    }

    //  기업 공고 등록 전, 기업이 요구할 자기소개서 질문지 조회
    @Override
    public List<ApplicationCompanyQuestionResponseDTO> selectQuestionList() {
        return coverLetterCategoryRepository.selectQuestionList();
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

    // 개인 사용자가 1개의 기업에 채용 지원
    @Override
    public ApplicationMemberApplyResponseDTO applyPosting(ApplicationMemberApplyRequestDTO requestDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        Resume resume = resumeRepository.getReferenceById(requestDTO.getResumeNo());
        JobPosting posting = jobPostingRepository.getReferenceById(requestDTO.getPostingNo());

        if (!resume.getMemberNo().getMemberNo().equals(memberNo)) {
            return null;
        }

        Application application = applicationRepository.save(Application
                .builder()
                .postingNo(posting)
                .memberNo(member)
                .applicationTitle(resume.getResumeTitle())
                .applicationName(resume.getResumeName())
                .applicationEngname(resume.getResumeEngname())
                .applicationMail(resume.getResumeMail())
                .applicationTel(resume.getResumeTel())
                .applicationAddress(resume.getResumeAddress())
                .applicationBirthday(resume.getResumeBirthday())
                .applicationGender(resume.getResumeGender())
                .applicationImg(resume.getResumeImg())
                .applicationPortfolio(resume.getResumePortfolio())
                .applicationAmbition(resume.getResumeAmbition())
                .applicationArmyType(resume.getResumeArmyType())
                .applicationArmyDate(resume.getResumeArmyDate())
                .applicationArmyEnd(resume.getResumeArmyEnd())
                .applicationArmyPart(resume.getResumeArmyPart())
                .applicationDisabilityType(resume.getResumeDisabilityType())
                .applicationDisability(resume.getResumeDisability())
                .applicationRewardingPatriotism(resume.getResumeRewardingPatriotism())
                .applicationDate(LocalDateTime.now())
                .applicationEndDate(posting.getPostingDeadline())
                .applicationStatus(ApplicationStatus.SUBMIT)
                .applicationAlarm(false)
                .applicationGrade(null)
                .applicationResult(false)
                .applicationPf(null)
                .applicationCreate(LocalDateTime.now())
                .build());

        for (ResumeAward resumeAward : resumeAwardRepository.findByResumeNo(resume)) {
            applicationAwardRepository.save(ApplicationAward
                    .builder()
                    .applicationNo(application)
                    .aAwaTitle(resumeAward.getAwaTitle())
                    .aAwaCompetitionName(resumeAward.getAwaCompetitionName())
                    .aAwaOrgan(resumeAward.getAwaOrgan())
                    .aAwaDate(resumeAward.getAwaDate())
                    .aAwaContent(resumeAward.getAwaContent())
                    .build());
        }
        for (ResumeCertificate resumeCertificate : resumeCertificateRepository.findByResumeNo(resume)) {
            applicationCertificateRepository.save(ApplicationCertificate
                    .builder()
                    .applicationNo(application)
                    .aCerTitle(resumeCertificate.getCerTitle())
                    .aCerIssuingAuthority(resumeCertificate.getCerIssuingAuthority())
                    .aCerDate(resumeCertificate.getCerDate())
                    .aCerExpire(resumeCertificate.getCerExpire())
                    .build());
        }
        for (ResumeEducation resumeEducation : resumeEducationRepository.findByResumeNo(resume)) {
            applicationEducationRepository.save(ApplicationEducation
                    .builder()
                    .applicationNo(application)
                    .aEduTitle(resumeEducation.getEduTitle())
                    .aEduOrgan(resumeEducation.getEduOrgan())
                    .aEuContent(resumeEducation.getEduContent())
                    .aEduTime(resumeEducation.getEduTime())
                    .aEduStartDate(resumeEducation.getEduStartDate())
                    .aEduEndDate(resumeEducation.getEduEndDate())
                    .build());
        }
        for (ResumeExperience resumeExperience : resumeExperienceRepository.findByResumeNo(resume)) {
            applicationExperienceRepository.save(ApplicationExperience
                    .builder()
                    .applicationNo(application)
                    .aExpCompanyName(resumeExperience.getExpCompanyName())
                    .aExpPosition(resumeExperience.getExpPosition())
                    .aExpStartDate(resumeExperience.getExpStartDate())
                    .aExpEndDate(resumeExperience.getExpEndDate())
                    .aExpPart(resumeExperience.getExpPart())
                    .aExpAchievement(resumeExperience.getExpAchievement())
                    .aExpIsCurrent(resumeExperience.getExpIsCurrent())
                    .build());
        }
        for (ResumeSchool resumeSchool : resumeSchoolRepository.findByResumeNo(resume)) {
            applicationSchoolRepository.save(ApplicationSchool
                    .builder()
                    .applicationNo(application)
                    .aSchName(resumeSchool.getSchName())
                    .aSchMajor(resumeSchool.getSchMajor())
                    .aSchMinor(resumeSchool.getSchMinor())
                    .aSchMultiple(resumeSchool.getSchMultiple())
                    .aSchDegree(resumeSchool.getSchDegree())
                    .aSchGraduationDate(resumeSchool.getSchGraduationDate())
                    .aSchAdmissionDate(resumeSchool.getSchAdmissionDate())
                    .aSchGpa(resumeSchool.getSchGpa())
                    .aSchStandardGpa(resumeSchool.getSchStandardGpa())
                    .aSchPart(resumeSchool.getSchPart())
                    .aSchLev(resumeSchool.getSchLev())
                    .build());
        }
        for (ApplicationMemberApplyQuestionRequestDTO questionRequestDTO : requestDTO.getQuestion()) {
            coverLetterRepository.save(CoverLetter
                    .builder()
                    .applicationNo(application)
                    .coverQuestion(questionRequestDTO.getQuestion())
                    .coverContent(questionRequestDTO.getQuestionContent())
                    .coverLength(questionRequestDTO.getQuestionLength())
                    .build());
        }


        ApplicationMemberApplyResponseDTO applyResponseDTO = new ApplicationMemberApplyResponseDTO();
        applyResponseDTO.setMessage("Success");
        return applyResponseDTO;
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

    // 채용 공고의 지원자 목록 조회
    @Override
    public List<ApplicationCompanyListResponseDTO> selectCompanyList(Long postingNo) {
        List<ApplicationCompanyListResponseDTO> responseDTOList = applicationRepository.selectCompanyListByPostingNo(postingNo);
        for (ApplicationCompanyListResponseDTO responseDTO : responseDTOList) {
            SchPart schPart = applicationSchoolRepository.selectLastEducationPartByApplicationNo(responseDTO.getApplicationNo());
            responseDTO.setApplicationPart(schPart);
        }
        return responseDTOList;
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

    // 추천 채용 직무
    @Override
    public List<ApplicationMemberJobListResponseDTO> selectPostingByMember(Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        List<String> address = null;
        List<String> part = null;
        List<String> type = null;
        if (member.getMemberCompanyAddress() != null) {
            address = Arrays.stream(member.getMemberCompanyAddress().split(",")).toList();
        }
        if (member.getMemberCompanyPart() != null) {
            part = Arrays.stream(member.getMemberCompanyPart().split(",")).toList();
        }
        if (member.getMemberCompanyContract() != null) {
            type = Arrays.stream(member.getMemberCompanyContract().split(",")).toList();
        }

        List<ApplicationMemberJobListResponseDTO> list =  jobPostingRepository.selectPostingByMember(address, part, type, member.getMemberCode());

        for (ApplicationMemberJobListResponseDTO dto : list) {
            JobPosting posting = jobPostingRepository.getReferenceById(dto.getPostingNo());
            List<BookMark> bookMarkList = bookMarkRepository.findByMemberNoAndPostingNo(member, posting);
            if (!ObjectUtils.isEmpty(bookMarkList) && !bookMarkList.isEmpty()) {
                dto.setBookMarkNo(bookMarkList.get(0).getBookMarkNo());
            }
        }
        return list;
    }

    // 기업 - 이력서 상세 조회
    @Override
    public ApplicationCompanyApplyDetailResponseDTO selectApplication(Long applicationNo) {
        Application application = applicationRepository.getReferenceById(applicationNo);

        ApplicationCompanyApplyDetailResponseDTO detailResponseDTO = new ApplicationCompanyApplyDetailResponseDTO();
        detailResponseDTO.setApplicationNo(application.getApplicationNo());
        detailResponseDTO.setApplicationTitle(application.getApplicationTitle());
        detailResponseDTO.setApplicationName(application.getApplicationName());
        detailResponseDTO.setApplicationEngName(application.getApplicationEngname());
        detailResponseDTO.setApplicationMail(application.getApplicationEngname());
        detailResponseDTO.setApplicationTel(application.getApplicationTel());
        detailResponseDTO.setApplicationAddress(application.getApplicationAddress());
        detailResponseDTO.setApplicationBirthDay(application.getApplicationBirthday());
        detailResponseDTO.setApplicationGender(application.getApplicationGender());
        detailResponseDTO.setApplicationIMG(application.getApplicationImg());
        detailResponseDTO.setApplicationDate(application.getApplicationDate());
        detailResponseDTO.setApplicationPortFolio(application.getApplicationPortfolio());
        detailResponseDTO.setApplicationAmbition(application.getApplicationAmbition());
        detailResponseDTO.setApplicationArmyType(application.getApplicationArmyType());
        detailResponseDTO.setApplicationArmyDate(application.getApplicationArmyDate());
        detailResponseDTO.setApplicationArmyEnd(application.getApplicationArmyEnd());
        detailResponseDTO.setApplicationArmyPart(application.getApplicationArmyPart());
        detailResponseDTO.setApplicationDisability(application.getApplicationDisability());
        detailResponseDTO.setApplicationDisabilityType(application.getApplicationDisabilityType());
        detailResponseDTO.setApplicationRewardingPatriotism(application.getApplicationRewardingPatriotism());
        detailResponseDTO.setApplicationCover(coverLetterRepository.selectQuestionListByApplicationNo(applicationNo));
        detailResponseDTO.setApplicationSchool(applicationSchoolRepository.selectSchool(applicationNo));
        detailResponseDTO.setApplicationExperience(applicationExperienceRepository.selectExperience(applicationNo));
        detailResponseDTO.setApplicationCertificate(applicationCertificateRepository.selectCertificate(applicationNo));
        detailResponseDTO.setApplicationEducation(applicationEducationRepository.selectEducation(applicationNo));
        detailResponseDTO.setApplicationAward(applicationAwardRepository.selectAward(applicationNo));
        return detailResponseDTO;
    }

    @Override
    public ApplicationCompanyResumeStatusResponseDTO applicationStatusModify(Long applicationNo, ApplicationStatus status) {
        Application application = applicationRepository.getReferenceById(applicationNo);
        application.changeApplicationStatus(status);

        ApplicationCompanyResumeStatusResponseDTO resumeStatusResponseDTO = new ApplicationCompanyResumeStatusResponseDTO();
        resumeStatusResponseDTO.setMessage("Success");
        return resumeStatusResponseDTO;
    }

    // 기업 - 지원서 열람 후 점수 입력 API
    @Override
    public ApplicationCompanyScoreResponseDTO applicationScoreInput(ApplicationCompanyScoreRequestDTO requestDTO) {
        Application application = applicationRepository.getReferenceById(requestDTO.getApplicationNo());
        application.changeApplicationGrade(requestDTO.getApplicationGrade());

        ApplicationCompanyScoreResponseDTO responseDTO = new ApplicationCompanyScoreResponseDTO();
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    // 기업 - 조기 마감 API
    @Override
    public ApplicationCompanyEarlyFinishResponseDTO applicationEarlyFinish(Long postingNo) {
        JobPosting posting = jobPostingRepository.getReferenceById(postingNo);
        List<Application> applicationList = applicationRepository.findByPostingNo(posting);
        for (Application application : applicationList) {
            if (!application.getApplicationStatus().equals(ApplicationStatus.FINAL_PASS)) {
                application.changeApplicationStatus(ApplicationStatus.FAIL);
            }
        }

        ApplicationCompanyEarlyFinishResponseDTO responseDTO = new ApplicationCompanyEarlyFinishResponseDTO();
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    // 기업 - 1개의 지원 타입에 대한 전체 불합격
    @Override
    public ApplicationCompanyCategoryFailResponseDTO applicationCategoryFail(Long postingNo, ApplicationStatus status) {
        JobPosting posting = jobPostingRepository.getReferenceById(postingNo);
        List<Application> applicationList = applicationRepository.findByPostingNo(posting);
        for (Application application : applicationList) {
            if (application.getApplicationStatus().equals(status) || ApplicationStatus.TOTAL.equals(status)) {
                application.changeApplicationStatus(ApplicationStatus.FAIL);
            }
        }

        ApplicationCompanyCategoryFailResponseDTO responseDTO = new ApplicationCompanyCategoryFailResponseDTO();
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    // 개인 - 채용 목록 page 조회 시 나오는 모든 공고에 대한 목록 및 검색 API (비회원)
    @Override
    public List<ApplicationMemberJobListResponseDTO> selectJobList(ApplicationMemberJobListRequestDTO requestDTO) {
        Long page = requestDTO.getPage();
        if (page >= 1) {
            page--;
        }
        log.info("here2");
        return jobPostingRepository.selectPostingBySearch(
                requestDTO.getCompanyAddress(),
                requestDTO.getCompanyPart(),
                requestDTO.getCompanyType(),
                requestDTO.getPostingEducation(),
                requestDTO.getKeyword(),
                page);
    }

    // 개인 - 채용 목록 page 조회 시 나오는 모든 공고에 대한 목록 및 검색 API (회원)
    @Override
    public List<ApplicationMemberJobListResponseDTO> selectJobList(ApplicationMemberJobListRequestDTO requestDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        Long page = requestDTO.getPage();
        if (page >= 1) {
            page--;
        }

        List<ApplicationMemberJobListResponseDTO> list = jobPostingRepository.selectPostingBySearch(
                requestDTO.getCompanyAddress(),
                requestDTO.getCompanyPart(),
                requestDTO.getCompanyType(),
                requestDTO.getPostingEducation(),
                requestDTO.getKeyword(),
                page);
        for (ApplicationMemberJobListResponseDTO dto : list) {
            JobPosting posting = jobPostingRepository.getReferenceById(dto.getPostingNo());
            List<BookMark> bookMarkList = bookMarkRepository.findByMemberNoAndPostingNo(member, posting);
            if (!ObjectUtils.isEmpty(bookMarkList) && !bookMarkList.isEmpty()) {
                dto.setBookMarkNo(bookMarkList.get(0).getBookMarkNo());
            }
        }

        return list;
    }

    // 개인 - 체용 목록 page 검색 시, 몇 페이지까지 있는지 조회하는 API
    @Override
    public ApplicationMemberSearchPageResponseDTO selectSearchPageCount(ApplicationMemberSearchPageRequestDTO requestDTO) {
        Long count = jobPostingRepository.selectPostingCountBySearch(requestDTO.getCompanyAddress(), requestDTO.getCompanyPart(), requestDTO.getCompanyType(), requestDTO.getPostingEducation(), requestDTO.getKeyword());
        count = (long) Math.ceil((double) count / 10);
        ApplicationMemberSearchPageResponseDTO responseDTO = new ApplicationMemberSearchPageResponseDTO();
        responseDTO.setPage(count);
        return responseDTO;
    }
}
