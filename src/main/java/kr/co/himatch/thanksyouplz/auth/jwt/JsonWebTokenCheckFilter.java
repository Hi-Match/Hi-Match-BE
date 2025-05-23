package kr.co.himatch.thanksyouplz.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.himatch.thanksyouplz.auth.exception.ErrorResponse;
import kr.co.himatch.thanksyouplz.auth.util.JwtTokenUtils;
import kr.co.himatch.thanksyouplz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static kr.co.himatch.thanksyouplz.auth.Constants.SECURITY_HTTP_EXCLUDE_URIS;
import static kr.co.himatch.thanksyouplz.auth.Constants.SECURITY_HTTP_NON_MEMBER_ALLOW_URIS;


//@Transactional
@RequiredArgsConstructor
public class JsonWebTokenCheckFilter extends OncePerRequestFilter {

    private final AntPathMatcher antPathMatcher;

    private final JsonWebTokenProvider jsonWebTokenProvider;
    private final MemberRepository memberRepository;

//    private String excludeUrl = Constants.BASE_URI + "/**";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //login 요청의 경우 다음 필터로
//        if (antPathMatcher.match("/**", request.getRequestURI())) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        for (String str : SECURITY_HTTP_EXCLUDE_URIS) {
            if (antPathMatcher.match(str, request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        //비회원, 회원 동시 허용 URI에 대한 로직
        for (String str : SECURITY_HTTP_NON_MEMBER_ALLOW_URIS) {
            if (antPathMatcher.match(str, request.getRequestURI())) {
                String access = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("Refresh")) {
                            access = cookie.getValue();
                        }
                    }
                }

                if (access != null && JwtTokenUtils.isValidToken(access)) {
                    Authentication authentication = jsonWebTokenProvider.getAuthentication(access); // 정상 토큰이면 SecurityContext 저장
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);
                return;
            }

        }

        // request에 모든 Cookie를 읽는다. > refresh라고 적혀있는 쿠키가 있다면 access라는 String에 넣는다.
        // 근데 이 과정을 거쳐서도 null 이라면 BAD_REQUEST를 보낸다.
        try {
            String access = null;
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                throw new ErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 Refresh 토큰입니다. ");
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Refresh")) {
                    access = cookie.getValue();
                }
            }

            if (access == null) {
                throw new ErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 Refresh 토큰입니다. ");
            }

            if (JwtTokenUtils.isValidToken(access)) { // ACCESS 토큰 유효기간 안지남.
                Authentication authentication = jsonWebTokenProvider.getAuthentication(access); // 정상 토큰이면 SecurityContext 저장
                // 제대로 된 토큰이라면 SecurityContextHolder에 담는다.
                // 담은 토큰을 Controller에서 활용할 수 있게 전송하는 코드
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else { // ACCESS 토큰 유효기간 지남.+ REFRESH 있음

                throw new ErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 Refresh 토큰입니다. ");

            }

        } catch (ErrorResponse e) {
//            JsonUtils.writeJsonExceptionResponse(response, e.getHttpStatus(), e.getMessage());
            response.sendError(e.getStatusCode(), e.getMessage());
            return;
        }
        doFilter(request, response, filterChain);
    }
}
