package com.smartBankElite.accountService.Configuration;

import com.smartBankElite.accountService.Annotation.VerifyAccountOwnership;
import com.smartBankElite.accountService.DTO.CacheDTO;
import com.smartBankElite.accountService.Repository.AccountDetailsRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static com.smartBankElite.accountService.Configuration.JwtAuthenticationFilter.getToken;

@Aspect
@Component
public class AccountOwnershipAspect {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Before("@annotation(verifyAccountOwnership)")
    public void verifyAccountOwnership(JoinPoint joinPoint,
                                       VerifyAccountOwnership verifyAccountOwnership) throws Exception {

        String accountId = extractAccountId(joinPoint, verifyAccountOwnership.field());
        CacheDTO cacheDTO = jwtUtils.getCacheDTOFromToken(getToken());
        if (accountId == null || cacheDTO == null) {
            throw new RuntimeException("Account ID not found in request");
        }
        boolean exists = accountDetailsRepository
                .existsByAccountNumberAndUserIdAndDeletedFlagFalse(accountId, cacheDTO.getUserId());
        if (!exists) {
            throw new RuntimeException("Unauthorized access to account");
        }
    }

    private String extractAccountId(JoinPoint joinPoint, String fieldName) throws Exception {

        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];

            if (arg == null) continue;

            // path variable
            if (parameter.isAnnotationPresent(PathVariable.class) && arg instanceof String) {
                return  (String) arg;
            }

            // request param
            if (parameter.isAnnotationPresent(RequestParam.class) && arg instanceof String) {
                return (String) arg;
            }

            // request body
            try {
                Field field = arg.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(arg);
                if (value instanceof String) {
                    return (String) value;
                }
            } catch (NoSuchFieldException ignored) {
                // If the field is not found in this parameter, continue to the next one
            }
        }

        return null;
    }
}
