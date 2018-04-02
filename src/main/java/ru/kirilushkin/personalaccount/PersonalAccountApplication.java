package ru.kirilushkin.personalaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kirilushkin.personalaccount.entity.Account;
import ru.kirilushkin.personalaccount.entity.ApplicationClient;
import ru.kirilushkin.personalaccount.repository.AccountRepository;
import ru.kirilushkin.personalaccount.repository.ApplicationClientRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PersonalAccountApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PersonalAccountApplication.class, args);
		AccountRepository accountRepository = context.getBean(AccountRepository.class);
		ApplicationClientRepository applicationClientRepository = context.getBean(ApplicationClientRepository.class);
		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		accountRepository.save(new Account(
				"admin@app",
				passwordEncoder.encode("password"),
				Arrays.asList(
						new SimpleGrantedAuthority("VIEW"),
						new SimpleGrantedAuthority("EDIT")
				)
		));
		Set<String> scopes = new HashSet<>();
		scopes.add("read");
		scopes.add("write");

		Set<String> authorizedGrantTypes = new HashSet<>();
		authorizedGrantTypes.add("password");
		authorizedGrantTypes.add("authorization_code");
		authorizedGrantTypes.add("refresh_token");
		applicationClientRepository.save(new ApplicationClient(
				"client",
				passwordEncoder.encode("secret"),
				scopes,
				authorizedGrantTypes,
				3600,
				2592000
		));
	}
}
