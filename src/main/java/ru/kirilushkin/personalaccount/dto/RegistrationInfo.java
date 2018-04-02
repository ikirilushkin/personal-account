package ru.kirilushkin.personalaccount.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Registration info")
public class RegistrationInfo {

    @NotNull(message = "validation.error.signup.login.empty")
    @Email(message = "validation.error.signup.login.email")
    @Size(min = 1, max = 254, message = "validation.error.signup.login.size")
    @ApiModelProperty(name = "login email", example = "example@app", required = true)
    private String login;

    @Size(min = 1, max = 100, message = "validation.error.signup.password")
    @ApiModelProperty(name = "password", required = true)
    private String password;
}
