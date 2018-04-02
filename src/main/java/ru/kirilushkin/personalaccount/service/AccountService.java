package ru.kirilushkin.personalaccount.service;

import ru.kirilushkin.personalaccount.entity.Account;

public interface AccountService {
    Account getSettings();

    void updateSettings(Account account);
}
