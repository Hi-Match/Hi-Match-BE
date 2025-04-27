package kr.co.himatch.thanksyouplz.company.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.company.dto.*;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.entity.CompanyLog;
import kr.co.himatch.thanksyouplz.company.entity.CompanyTag;
import kr.co.himatch.thanksyouplz.company.repository.CompanyLogRepository;
import kr.co.himatch.thanksyouplz.company.repository.CompanyRepository;
import kr.co.himatch.thanksyouplz.company.repository.CompanyTagRepository;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeAward;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static kr.co.himatch.thanksyouplz.company.entity.QCompany.*;

@Slf4j
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyLogRepository companyLogRepository;

    @Autowired
    private CompanyTagRepository companyTagRepository;

    // 기업용 일반 회원 가입
    @Override
    public CompanySignupResponseDTO companySignUp(CompanySignupRequestDTO companySignupRequestDTO) {

        Company signup = companyRepository.save(Company.builder().companyID(companySignupRequestDTO.getMemberID()).companyPass(BCrypt.hashpw(companySignupRequestDTO.getMemberPass(), BCrypt.gensalt())).companyManagerName(companySignupRequestDTO.getMemberName()).companyLicense(companySignupRequestDTO.getLicenseNumber()).companyName(companySignupRequestDTO.getCompanyName()).companyEmployee(companySignupRequestDTO.getCompanyCount()).companyIndustry(companySignupRequestDTO.getCompanyPart()).companyMail(companySignupRequestDTO.getMemberMail()).companyPhone(companySignupRequestDTO.getMemberPhone()).companyCreate(LocalDateTime.now()).build());

        CompanySignupResponseDTO companySignupResponseDTO = new CompanySignupResponseDTO();
        companySignupResponseDTO.setMemberID(signup.getCompanyID());
        companySignupResponseDTO.setCompanyName(signup.getCompanyName());
        companySignupResponseDTO.setMemberName(signup.getCompanyManagerName());
        companySignupResponseDTO.setMemberJoinDate(signup.getCompanyCreate());

        return companySignupResponseDTO;
    }

    // 회원 가입 시, ID 중복 검사
    @Override
    public Boolean checkCompanyMemberID(CompanyMemberIDCheckRequestDTO companyMemberIDCheckRequestDTO) {
        Optional<String> selectMemberID = companyRepository.selectMemberID(companyMemberIDCheckRequestDTO.getMemberID());

        if (selectMemberID.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // 기업용 일반 로그인
    @Override
    public Long companyLogin(String memberID, String memberPass) {
        Company selectID = companyRepository.selectID(memberID);

        if (selectID == null) {
            return null;
        } else {
            if (BCrypt.checkpw(memberPass, selectID.getCompanyPass())) {
                return selectID.getCompanyNo();
            } else {
                return null;
            }
        }
    }

    // 일반 로그인 시 refreshToken 저장
    @Override
    public void memberNormalLoginRefreshToken(Long companyNo, String companyMemberToken) {

        Company company = companyRepository.getReferenceById(companyNo);
        company.changeToken(companyMemberToken);
    }

    // 기업용 회원 ID 찾기
    @Override
    public List<CompanyMemberFindIDResponseDTO> companyFindID(CompanyMemberFindIDRequestDTO companyMemberFindIDRequestDTO) {
        List<Company> findID = companyRepository.selectMemberNameAndLicenseNumber(companyMemberFindIDRequestDTO.getMemberName(), companyMemberFindIDRequestDTO.getLicenseNumber());

        List<CompanyMemberFindIDResponseDTO> companyMemberFindIDResponseDTO = findID.stream().map(this::findListID).toList();

        return companyMemberFindIDResponseDTO;
    }

    // 기업용 회원 ID 찾기 이어서
    public CompanyMemberFindIDResponseDTO findListID(Company company) {
        CompanyMemberFindIDResponseDTO companyMemberFindIDResponseDTO = new CompanyMemberFindIDResponseDTO();

        companyMemberFindIDResponseDTO.setMemberID(company.getCompanyID());
        companyMemberFindIDResponseDTO.setMemberJoinDate(company.getCompanyCreate());

        return companyMemberFindIDResponseDTO;
    }

    // 기업용 회원 PW 찾기
    @Override
    public String findPass(CompanyMemberFindPWRequestDTO companyMemberFindPWRequestDTO, String memberPass) {
        Optional<Company> findPass = companyRepository.selectCompanyIdAndNameAndPhone(companyMemberFindPWRequestDTO.getMemberID(), companyMemberFindPWRequestDTO.getMemberName(), companyMemberFindPWRequestDTO.getMemberPhone());

        if (findPass.isEmpty()) {
            return null;
        } else {

            // 유저의 비밀번호를 임시 비멀번호로 변경한다.
            findPass.get().temporaryChangePass(memberPass);
            return findPass.get().getCompanyMail();
        }
    }

    // 기업용 회원 프로필 편집 - 휴대폰 번호 변경
    @Override
    public CompanyChangePhoneResponseDTO companyChangePhone(CompanyChangePhoneRequestDTO companyChangePhoneRequestDTO, Long memberNo) {
        Company company = companyRepository.getReferenceById(memberNo);
        company.companyChangePhone(companyChangePhoneRequestDTO.getMemberPhone());

        CompanyChangePhoneResponseDTO companyChangePhoneResponseDTO = new CompanyChangePhoneResponseDTO();
        companyChangePhoneResponseDTO.setMessage("Success!");

        return companyChangePhoneResponseDTO;
    }

    // 기업용 회원 프로필 편집 - 메일 변경
    @Override
    public CompanyChangeMailResponseDTO companyChangeMail(CompanyChangeMailRequestDTO companyChangeMailRequestDTO, Long memberNo) {
        Company company = companyRepository.getReferenceById(memberNo);
        company.companyChangeMail(companyChangeMailRequestDTO.getMemberMail());

        CompanyChangeMailResponseDTO companyChangeMailResponseDTO = new CompanyChangeMailResponseDTO();
        companyChangeMailResponseDTO.setMessage("Success!");

        return companyChangeMailResponseDTO;
    }

    // 기업용 회원 프로필 편집 - 비밀번호 변경
    @Override
    public CompanyChangePassResponseDTO companyChangePass(CompanyChangePassRequestDTO companyChangePassRequestDTO) {
        Optional<Company> companyChangePass = companyRepository.selectCompanyPass(companyChangePassRequestDTO.getMemberID(), companyChangePassRequestDTO.getMemberName(), companyChangePassRequestDTO.getMemberPhone());

        if (companyChangePass.isEmpty()) {
            return null;
        } else {
            companyChangePass.get().companyChangePass(BCrypt.hashpw(companyChangePassRequestDTO.getMemberPass(), BCrypt.gensalt()));

            CompanyChangePassResponseDTO companyChangePassResponseDTO = new CompanyChangePassResponseDTO();
            companyChangePassResponseDTO.setMessage("Success!");

            return companyChangePassResponseDTO;
        }

    }

    // 기업용 회원 탈퇴
    // 비밀번호의 경우, Bcrypt로 암호화 되어있으나 Log의 기능만을 하고 있기 때문에 그대로 뽑아서 바로 넣는다.
    // 기업이 탈퇴할 경우, 관련된 data를 삭제해야한다.
    // TODO : 채용 공고, 기업 정보 등을 삭제해야하므로 추후에 API 보완 예정
    @Override
    public CompanyMemberDeleteResponseDTO companyDelete(Long memberNo) {
        Company company = companyRepository.getReferenceById(memberNo);

        // 기업 탈퇴 전, Log Table로 data를 옮긴다.
        CompanyLog companyLogInsert = companyLogRepository.save(CompanyLog.builder().companyID(company.getCompanyID()).companyPass(company.getCompanyPass()).companyName(company.getCompanyName()).companyManagerName(company.getCompanyManagerName()).companyAddress(company.getCompanyAddress()).companyLicense(company.getCompanyLicense()).companyPhone(company.getCompanyPhone()).companyMail(company.getCompanyMail()).companyIndustry(company.getCompanyIndustry()).companyEmployee(company.getCompanyEmployee()).companyDescription(company.getCompanyDescription()).companyCode(company.getCompanyCode()).companyLogo(company.getCompanyLogo()).companyCreate(company.getCompanyCreate()).companyDelete(LocalDateTime.now()).build());

        // Company Table에서 data를 일괄 삭제한다.
        companyRepository.deleteCompany(memberNo);

        CompanyMemberDeleteResponseDTO companyMemberDeleteResponseDTO = new CompanyMemberDeleteResponseDTO();
        companyMemberDeleteResponseDTO.setMessage("Success!");

        return companyMemberDeleteResponseDTO;
    }

    // 기업용 상세 조회 API
    @Override
    public CompanyInfoDetailResponseDTO companyDetail(Long memberNo) {
        Company company = companyRepository.selectCompanyDetail(memberNo).orElseThrow();
        CompanyInfoDetailResponseDTO companyInfoDetailResponseDTO = new CompanyInfoDetailResponseDTO();
        companyInfoDetailResponseDTO.setCompanyName(company.getCompanyName());
        companyInfoDetailResponseDTO.setCompanyManagerName(company.getCompanyManagerName());
        companyInfoDetailResponseDTO.setCompanyAddress(company.getCompanyAddress());
        companyInfoDetailResponseDTO.setCompanyPhone(company.getCompanyPhone());
        companyInfoDetailResponseDTO.setCompanyMail(company.getCompanyMail());
        companyInfoDetailResponseDTO.setCompanyIndustry(company.getCompanyIndustry());
        companyInfoDetailResponseDTO.setCompanyEmployee(company.getCompanyEmployee());
        companyInfoDetailResponseDTO.setCompanyDescription(company.getCompanyDescription());
        companyInfoDetailResponseDTO.setCompanyLogo(company.getCompanyLogo());
        companyInfoDetailResponseDTO.setTag(companyTagRepository.selectCompanyTags(memberNo));
        return companyInfoDetailResponseDTO;
    }

    @Override
    public CompanyInfoRegisterResponseDTO companyUpdate(CompanyInfoRegisterRequestDTO registerRequestDTO, Long memberNo) {
        Company company = companyRepository.getReferenceById(memberNo);
        company.companyInfoModify(registerRequestDTO.getCompanyName(), registerRequestDTO.getCompanyManagerName(), registerRequestDTO.getCompanyAddress(), registerRequestDTO.getCompanyPhone(), registerRequestDTO.getCompanyMail(), registerRequestDTO.getCompanyIndustry(), registerRequestDTO.getCompanyEmployee(), registerRequestDTO.getCompanyDescription(), registerRequestDTO.getCompanyLogo());
        companyTagRepository.deleteCompanyTags(memberNo);
        Optional.ofNullable(registerRequestDTO.getTag()).orElseGet(List::of).forEach(companyInfoTagRegisterDTO -> companyTagRepository.save(CompanyTag.builder().companyNo(company).cTagName(companyInfoTagRegisterDTO.getTagName()).build()));
        CompanyInfoRegisterResponseDTO companyInfoRegisterResponseDTO = new CompanyInfoRegisterResponseDTO();
        companyInfoRegisterResponseDTO.setMessage("Success");
        return companyInfoRegisterResponseDTO;
    }

    @Override
    public CompanyMemberMyhomeResponseDTO companyMemberMyhome(Long memberNo) {
        Company company = companyRepository.getReferenceById(memberNo);
        CompanyMemberMyhomeResponseDTO companyMyhome = new CompanyMemberMyhomeResponseDTO();
        companyMyhome.setMemberID(company.getCompanyID());
        companyMyhome.setMemberMail(company.getCompanyMail());
        companyMyhome.setMemberName(company.getCompanyManagerName());
        companyMyhome.setMemberPhone(company.getCompanyPhone());
        companyMyhome.setMemberJoinDate(company.getCompanyCreate());
        return companyMyhome;
    }
}
