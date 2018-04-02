package ru.kirilushkin.personalaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "oauth_client_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationClient implements ClientDetails {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scope = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorizedGrantTypes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return true;
    }


    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }


    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    public ApplicationClient(String clientId, String clientSecret, Set<String> scope, Set<String> authorizedGrantTypes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
}
