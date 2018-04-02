package ru.kirilushkin.personalaccount.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kirilushkin.personalaccount.dto.RegistrationInfo;
import ru.kirilushkin.personalaccount.service.SignupService;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping
    @ApiOperation("Sign up new user")
    public void signup(@RequestBody @Valid RegistrationInfo registrationInfo) {
        signupService.signup(registrationInfo);
    }
}
