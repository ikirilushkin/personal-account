package ru.kirilushkin.personalaccount.service;

import ru.kirilushkin.personalaccount.dto.RegistrationInfo;

public interface SignupService {
    void signup(RegistrationInfo registrationInfo);
}
