package ru.kirilushkin.personalaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirilushkin.personalaccount.entity.ApplicationClient;

import java.util.Optional;

@Repository
public interface ApplicationClientRepository extends JpaRepository<ApplicationClient, Long> {

    Optional<ApplicationClient> findByClientId(String clientId);
}
