package io.github.gabrielhgcamargo.repository;

import io.github.gabrielhgcamargo.rest.dto.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

Optional<UserModel> findByLogin(String login);

}
