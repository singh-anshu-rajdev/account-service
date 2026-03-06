package com.smartBankElite.accountService.Annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyAccountOwnership {
    String field() default "accountId";
}
