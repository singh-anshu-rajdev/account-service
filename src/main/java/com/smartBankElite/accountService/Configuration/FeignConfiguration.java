package com.smartBankElite.accountService.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import static com.smartBankElite.accountService.Configuration.JwtAuthenticationFilter.getToken;

@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = getToken();
        if (token != null && !token.isEmpty()) {
            requestTemplate.header("Authorization", "Bearer " + token);
        }
    }
}
