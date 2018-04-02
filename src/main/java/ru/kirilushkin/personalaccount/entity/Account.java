package ru.kirilushkin.personalaccount.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import ru.kirilushkin.personalaccount.deserializer.AccountDeserializer;
import ru.kirilushkin.personalaccount.view.View;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonView(View.AccountView.class)
@JsonDeserialize(using = AccountDeserializer.class)
@ApiModel("User account profile")
public class Account {

    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private long id;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(hidden = true)
    private String username;

    @Column(nullable = false)
    @JsonView(View.FullAccountView.class)
    @ApiModelProperty(hidden = true)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonView(View.FullAccountView.class)
    @ApiModelProperty(hidden = true)
    private Collection<GrantedAuthority> authorities;

    @ApiModelProperty(value = "user first name", allowEmptyValue = true)
    private String firstName;

    @ApiModelProperty(value = "user last name", allowEmptyValue = true)
    private String lastName;

    @ApiModelProperty(value = "user second name", allowEmptyValue = true)
    private String secondName;

    @URL(message = "validation.error.settings.photoUrl")
    @ApiModelProperty(value = "account avatar url", allowEmptyValue = true)
    private String photoUrl;

    public Account(String username, String password, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}
