package ru.kirilushkin.personalaccount.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kirilushkin.personalaccount.entity.Account;

import java.util.Collection;

public class AccountDetails implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private String password;

    private String username;

    private long accountId;

    public AccountDetails(Account account) {
        this.authorities = account.getAuthorities();
        this.password = account.getPassword();
        this.username = account.getUsername();
        this.accountId = account.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getAccountId() {
        return accountId;
    }
}
