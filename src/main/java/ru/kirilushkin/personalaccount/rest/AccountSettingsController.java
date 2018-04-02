package ru.kirilushkin.personalaccount.rest;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.kirilushkin.personalaccount.entity.Account;
import ru.kirilushkin.personalaccount.service.AccountService;
import ru.kirilushkin.personalaccount.view.View;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings")
public class AccountSettingsController {

    private final AccountService accountService;

    public AccountSettingsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @JsonView(View.AccountView.class)
    @ApiOperation("Getting user settings")
    public Account getSettings() {
        return accountService.getSettings();
    }

    @PostMapping
    @ApiOperation("Updating user settings")
    public void update(@RequestBody @Valid Account account) {
        accountService.updateSettings(account);
    }
}
