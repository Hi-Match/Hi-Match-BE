package kr.co.himatch.thanksyouplz.company.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.company.dto.*;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.repository.CompanyRepository;
import kr.co.himatch.thanksyouplz.member.entity.Member;
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

    // 기업용 일반 회원 가입
    @Override
    public CompanySignupResponseDTO companySignUp(CompanySignupRequestDTO companySignupRequestDTO) {

        Company signup = companyRepository.save(
                Company.builder()
                        .companyID(companySignupRequestDTO.getMemberID())
                        .companyPass(
                                BCrypt.hashpw(
                                        companySignupRequestDTO.getMemberPass(), BCrypt.gensalt()
                                )
                        )
                        .companyManagerName(companySignupRequestDTO.getMemberName())
                        .companyLicense(companySignupRequestDTO.getLicenseNumber())
                        .companyName(companySignupRequestDTO.getCompanyName())
                        .companyEmployee(companySignupRequestDTO.getCompanyCount())
                        .companyIndustry(companySignupRequestDTO.getCompanyPart())
                        .companyMail(companySignupRequestDTO.getMemberMail())
                        .companyPhone(companySignupRequestDTO.getMemberPhone())
                        .companyCreate(LocalDateTime.now())
                        .build());

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

        if (selectMemberID.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    // 기업용 일반 로그인
    @Override
    public Long companyLogin(String memberID, String memberPass) {
        Company selectID = companyRepository.selectID(memberID);

        if (selectID == null){
            return null;
        }else{
            if (BCrypt.checkpw(memberPass, selectID.getCompanyPass())){
                return selectID.getCompanyNo();
            }else{
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
        List<Company> findID = companyRepository.selectMemberNameAndLicenseNumber(
                companyMemberFindIDRequestDTO.getMemberName(), companyMemberFindIDRequestDTO.getLicenseNumber());

        List<CompanyMemberFindIDResponseDTO> companyMemberFindIDResponseDTO = findID.stream().map(this::findListID).toList();

        return companyMemberFindIDResponseDTO;
    }

    // 기업용 회원 ID 찾기 이어서
    public CompanyMemberFindIDResponseDTO findListID(Company company){
        CompanyMemberFindIDResponseDTO companyMemberFindIDResponseDTO = new CompanyMemberFindIDResponseDTO();

        companyMemberFindIDResponseDTO.setMemberID(company.getCompanyID());
        companyMemberFindIDResponseDTO.setMemberJoinDate(company.getCompanyCreate());

        return companyMemberFindIDResponseDTO;
    }

    // 기업용 회원 PW 찾기
    @Override
    public String findPass(CompanyMemberFindPWRequestDTO companyMemberFindPWRequestDTO, String memberPass) {
        Optional<Company> findPass = companyRepository.selectCompanyIdAndNameAndPhone(
                companyMemberFindPWRequestDTO.getMemberID(), companyMemberFindPWRequestDTO.getMemberName(), companyMemberFindPWRequestDTO.getMemberPhone());

        if (findPass.isEmpty()){
            return null;
        }else{

            // 유저의 비밀번호를 임시 비멀번호로 변경한다.
            findPass.get().temporaryChangePass(memberPass);
            return findPass.get().getCompanyMail();
        }
    }
}
