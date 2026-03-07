package com.smartBankElite.accountService.ServiceImpl;

import com.smartBankElite.accountService.DTO.CreateAccountRequestDTO;
import com.smartBankElite.accountService.DTO.CreateAccountResponseDTO;
import com.smartBankElite.accountService.DTO.RegisterUserDto;
import com.smartBankElite.accountService.IFeignServices.IAuthSeviceCalls;
import com.smartBankElite.accountService.Model.AccountDetails;
import com.smartBankElite.accountService.Model.Address;
import com.smartBankElite.accountService.Repository.AccountDetailsRepository;
import com.smartBankElite.accountService.Service.AccountDetailsService;
import com.smartBankElite.accountService.Utlis.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.smartBankElite.accountService.Utlis.CredentialGenerator.*;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private IAuthSeviceCalls iAuthSeviceCalls;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    @Override
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO) {

        checkValidity(createAccountRequestDTO.getEmail(), createAccountRequestDTO.getMobileNumber(), createAccountRequestDTO.getUniqueId());
        try {

            AccountDetails account = GenericMapper.mapToTarget(createAccountRequestDTO, AccountDetails.class);

            Address address = GenericMapper.mapToTarget(createAccountRequestDTO.getAddressDTO(), Address.class);
            account.setAddress(address);

            // set account number
            account.setAccountNumber(generateAccountNumber());

            // Convert Entity back to Response DTO
            RegisterUserDto registerUser = new RegisterUserDto();
            registerUser.setEmail(createAccountRequestDTO.getEmail());
            registerUser.setPassword(generatePassword());
            registerUser.setUserName(generateUsername(createAccountRequestDTO.getFirstName()));
            registerUser.setFullName(createAccountRequestDTO.getFirstName() + " " + createAccountRequestDTO.getLastName());

            registerUser = iAuthSeviceCalls.register(registerUser);
            account.setUserId(registerUser.getId());
            account = accountDetailsRepository.save(account);
            return GenericMapper.mapToTarget(account, CreateAccountResponseDTO.class);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating account: " + ex.getMessage(), ex);
        }
    }

    private void checkValidity(String emailId, String mobileNumber, String uniqueId) {
        if (null!=emailId && accountDetailsRepository.existsByEmailAndDeletedFlagFalse(emailId)) {
            throw new RuntimeException("Email ID already exists");
        }
        if (null!=mobileNumber && accountDetailsRepository.existsByMobileNumberAndDeletedFlagFalse(mobileNumber)) {
            throw new RuntimeException("Mobile number already exists");
        }
        if (null!=uniqueId && accountDetailsRepository.existsByUniqueIdAndDeletedFlagFalse(uniqueId)) {
            throw new RuntimeException("Unique ID already exists");
        }
    }
}
