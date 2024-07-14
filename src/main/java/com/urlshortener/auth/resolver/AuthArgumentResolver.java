package com.urlshortener.auth.resolver;

import com.urlshortener.auth.annotation.AuthMember;
import com.urlshortener.auth.domain.Authentication;
import com.urlshortener.auth.domain.MemberDetail;
import com.urlshortener.auth.token.TokenProvider;
import com.urlshortener.member.domain.vo.MemberType;
import com.urlshortener.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(AuthMember.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        var httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        var token = HeaderUtil.getAccessToken(httpServletRequest);
        MemberDetail memberData = null;

        if (token != null && tokenProvider.validateDateToken(token)) {
            memberData = tokenProvider.getAuthentication(token).getUserDetail();
        }

        if (token == null) {
            memberData = new Authentication(new MemberDetail(), MemberType.GUEST).getUserDetail();
        }

        return Optional.of(memberData).orElseThrow(NullPointerException::new);
    }
}