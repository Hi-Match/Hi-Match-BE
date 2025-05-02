package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailCertificateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplicationCertificate.applicationCertificate;

public class ApplicationCertificateRepositoryImpl implements ApplicationCertificateRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationCompanyApplyDetailCertificateResponseDTO> selectCertificate(Long applicationNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyApplyDetailCertificateResponseDTO.class, applicationCertificate.aCerTitle, applicationCertificate.aCerIssuingAuthority, applicationCertificate.aCerDate, applicationCertificate.aCerExpire)
                )
                .from(applicationCertificate)
                .where(applicationCertificate.applicationNo.applicationNo.eq(applicationNo))
                .fetch();
    }
}
