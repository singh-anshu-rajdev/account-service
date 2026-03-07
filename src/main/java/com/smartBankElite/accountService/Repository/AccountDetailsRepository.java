package com.smartBankElite.accountService.Repository;

import com.smartBankElite.accountService.Model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface AccountDetailsRepository extends JpaRepository<AccountDetails,Long> {

    boolean existsByMobileNumberAndDeletedFlagFalse(String mobileNumber);

    boolean existsByUniqueIdAndDeletedFlagFalse(String uniqueId);

    boolean existsByEmailAndDeletedFlagFalse(String emailId);

    boolean existsByAccountNumberAndUserIdAndDeletedFlagFalse(String accountId, Integer userId);
}
