package com.smartBankElite.accountService.Controller;

import com.smartBankElite.accountService.Annotation.VerifyAccountOwnership;
import com.smartBankElite.accountService.DTO.CreateAccountRequestDTO;
import com.smartBankElite.accountService.DTO.CreateAccountResponseDTO;
import com.smartBankElite.accountService.Service.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @PostMapping("/unsecure/create")
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@RequestBody CreateAccountRequestDTO createAccountRequestDTO) {
        return new ResponseEntity<>(accountDetailsService.createAccount(createAccountRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    @VerifyAccountOwnership
    public String test(@RequestParam(value = "accountId",required = false) String accountId) {
        return "Hello";
    }


}

