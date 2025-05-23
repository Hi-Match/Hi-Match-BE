package kr.co.himatch.thanksyouplz.auth;

import kr.co.himatch.thanksyouplz.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    // Token값이 필요없이 접근 가능한 일부러 보안을 풀어둔 링크들 모음
    public static final String[] SECURITY_HTTP_EXCLUDE_URIS = {"/himatch/member/access-token", "/himatch/member/signup",
            "/himatch/member/phone", "/himatch/member/login/callback/**", "/himatch/member/idcheck",
            "/himatch/member/login", "/himatch/member/idfind", "/himatch/member/pwfind",
            "/himatch/member/login/**", "/himatch/resources/**", "/himatch/swagger*/**", "favicon.ico",
            "/himatch/webjars/**", "/himatch/swagger-ui/**",
            "/himatch/v3/api-docs/**", "/himatch/swagger-ui/**", "/himatch/swagger-resources/**",
            "/himatch/company/member/signup", "/himatch/company/member/login",
            "/himatch/company/member/idfind", "/himatch/company/member/pwfind", "/himatch/company/member/idcheck",
            "/himatch/company/member/login", "/himatch/company/member/idfind", "/himatch/company/member/pwfind",
            "/himatch/company/member/license", "/himatch/resume/file", "/himatch/application/company/select",
            "/himatch/application/member/search-page", "/himatch/company/info/detail-select"
    };

    public static final String[] SECURITY_HTTP_NON_MEMBER_ALLOW_URIS = {"/himatch/application/member/job-list"};

    //Authorization == JWT 사용을 위함
    // 해더에서 허용할 부분 설정
    // CORS =  서버가 다른 origin의 브라우저에게 자신의 자원이 로드될 수 있도록 헤더에 표시해주는 방법
    public static final String[] CORS_HEADER_URIS = {"Authorization", "Refresh", "content-type"};


    public static final String FIRST_OAUTH2_URL = "/member/login";
    public static final String SECOND_OAUTH2_AFTER_SPRING_LOGIN_URL = "/member/login/callback/*";
    public static String SERVER_URL;
    public static String FRONT_REDIRECT_URL;


    @Autowired
    public void setDefaultRedirectUrl(AuthConfig authConfig) {
        FRONT_REDIRECT_URL = authConfig.getRedirectUrl();  //frontend에서 받을 곳
    }

    @Autowired
    public void setServerUrl(AuthConfig authConfig) {
        SERVER_URL = authConfig.getServerUrl();
    }
}
