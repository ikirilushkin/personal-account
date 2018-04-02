package ru.kirilushkin.personalaccount.service;

import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import ru.kirilushkin.personalaccount.repository.ApplicationClientRepository;

@Service
public class ApplicationClientService implements ClientDetailsService {

    private final ApplicationClientRepository applicationClientRepository;

    public ApplicationClientService(ApplicationClientRepository applicationClientRepository) {
        this.applicationClientRepository = applicationClientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return applicationClientRepository
                .findByClientId(clientId)
                .orElseThrow(() -> new UnapprovedClientAuthenticationException("Client " + clientId + " not found"));
    }
}
