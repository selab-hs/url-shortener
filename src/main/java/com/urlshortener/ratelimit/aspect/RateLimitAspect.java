package com.urlshortener.ratelimit.aspect;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.url.RateLimitExceededException;
import com.urlshortener.ratelimit.annotation.RateLimit;
import com.urlshortener.ratelimit.facade.LettuceLockStockFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.urlshortener.util.HttpUtil.getClientIdFromCookie;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {
    private final LettuceLockStockFacade lettuceLockStockFacade;
    private final HttpServletRequest request;

    /**
     * UUID 사용한 요청 횟수 제한
     *
     * @param rateLimit -> 일정 시간 동안 (rateLimit.value()) 특정 횟수까지 요청 제한 (rateLimit.durationMinutes())
     * @throws RateLimitExceededException 일정 시간 요청 횟수 초과시 예외 발생
     */
    @Around("@annotation(rateLimit)")
    public Object checkRateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String clientId = getClientIdFromCookie(request);

        if (!lettuceLockStockFacade.limitIncrease(clientId, rateLimit)) {
            throw new RateLimitExceededException(
                    ErrorMessage.RATE_LIMIT_EXCEEDED,
                    rateLimit.type().getMessage()
            );
        }

        return joinPoint.proceed();
    }

//    web Cookie uuid (요청 제한)
//    uuid -> User class (uuid를 컨트롤러로 보내서 유저 클래스를 생성하고 id를 Short 넣어라)
//    User class id -> controller shortUrl -> use ()
}