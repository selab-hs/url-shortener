package com.urlshortener.auth.resolver;

import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.auth.model.AuthToken;
import com.urlshortener.auth.token.TokenProvider;
import com.urlshortener.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        var httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        var token = HeaderUtil.getAccessToken(httpServletRequest);

        if (token == null) {
            if (parameter.isOptional()) {
                return null;
            }

            token = "";
        }

        var authtoken = new AuthToken(token);

        return tokenProvider.getAuthMember(authtoken);
    }
}
