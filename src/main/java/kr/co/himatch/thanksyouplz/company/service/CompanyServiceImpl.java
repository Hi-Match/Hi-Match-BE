package kr.co.himatch.thanksyouplz.company.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupRequestDTO;
import kr.co.himatch.thanksyouplz.company.dto.CompanySignupResponseDTO;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.repository.CompanyRepository;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                        .companyPass(companySignupRequestDTO.getMemberPass())
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
}
