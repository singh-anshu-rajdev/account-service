package com.smartBankElite.accountService.IFeignServices;

import com.smartBankElite.accountService.DTO.RegisterUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "AUTH-SERVER")
public interface IAuthSeviceCalls {

    @PostMapping("/api/unsecure/signup")
    RegisterUserDto register(@RequestBody RegisterUserDto registerUserDto);
}
