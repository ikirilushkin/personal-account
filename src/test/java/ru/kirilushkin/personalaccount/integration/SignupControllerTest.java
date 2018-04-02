package ru.kirilushkin.personalaccount.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kirilushkin.personalaccount.dto.RegistrationInfo;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SignupControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldNotSignUpOnNullLogin() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationInfo> entity = new HttpEntity<>(registrationInfo, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/signup", HttpMethod.POST, entity, Object.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        LinkedHashMap<String, String> response = (LinkedHashMap<String, String>) responseEntity.getBody();
        assertEquals("login", response.get("field"));
        assertEquals("validation.error.signup.login.empty", response.get("code"));
    }

    @Test
    public void shouldNotSignUpOnEmptyLogin() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setLogin("");
        registrationInfo.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationInfo> entity = new HttpEntity<>(registrationInfo, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/signup", HttpMethod.POST, entity, Object.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        LinkedHashMap<String, String> response = (LinkedHashMap<String, String>) responseEntity.getBody();
        assertEquals("login", response.get("field"));
        assertEquals("validation.error.signup.login.size", response.get("code"));
    }

    @Test
    public void shouldNotSignUpOnEmailLogin() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setLogin("login");
        registrationInfo.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationInfo> entity = new HttpEntity<>(registrationInfo, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/signup", HttpMethod.POST, entity, Object.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        LinkedHashMap<String, String> response = (LinkedHashMap<String, String>) responseEntity.getBody();
        assertEquals("login", response.get("field"));
        assertEquals("validation.error.signup.login.email", response.get("code"));
    }

    @Test
    public void shouldNotSignUpOnEmptyPassword() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setLogin("login@app");
        registrationInfo.setPassword("");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationInfo> entity = new HttpEntity<>(registrationInfo, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/signup", HttpMethod.POST, entity, Object.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        LinkedHashMap<String, String> response = (LinkedHashMap<String, String>) responseEntity.getBody();
        assertEquals("password", response.get("field"));
        assertEquals("validation.error.signup.password", response.get("code"));
    }

    @Test
    public void shouldSignup() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setLogin("login@app");
        registrationInfo.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationInfo> entity = new HttpEntity<>(registrationInfo, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/signup", HttpMethod.POST, entity, Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
