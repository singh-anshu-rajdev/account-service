package com.smartBankElite.accountService.Service;

import com.smartBankElite.accountService.DTO.CreateAccountRequestDTO;
import com.smartBankElite.accountService.DTO.CreateAccountResponseDTO;

public interface AccountDetailsService {

    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO);
}
