package com.fullCar.FullCar.repository;

import com.fullCar.FullCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long > {
}
