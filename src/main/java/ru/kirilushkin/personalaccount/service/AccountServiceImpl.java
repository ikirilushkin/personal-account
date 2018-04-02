package ru.kirilushkin.personalaccount.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kirilushkin.personalaccount.config.AccountDetails;
import ru.kirilushkin.personalaccount.entity.Account;
import ru.kirilushkin.personalaccount.exception.AccountNotFounException;
import ru.kirilushkin.personalaccount.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountServiceImpl implements UserDetailsService, AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findAccountByUsername(username);
        if (account.isPresent()) {
            return new AccountDetails(account.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    public Account getSettings() {
        long accountId = getAccountId();
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFounException("Account with id " + accountId + "not found"));
    }

    @Override
    public void updateSettings(Account account) {
        long accountId = getAccountId();
        Account currentAccount = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFounException("Account with id " + accountId + "not found"));
        currentAccount.setFirstName(account.getFirstName());
        currentAccount.setLastName(account.getLastName());
        currentAccount.setSecondName(account.getSecondName());
        currentAccount.setPhotoUrl(account.getPhotoUrl());
        accountRepository.save(currentAccount);
    }

    private long getAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        return accountDetails.getAccountId();
    }
}
