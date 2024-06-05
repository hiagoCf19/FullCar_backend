package com.fullCar.FullCar.repository;

import com.fullCar.FullCar.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long > {
}
