package com.suhas.EmployeeMngmtSys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User1Repository extends JpaRepository<User1, Long> {
    Optional<User1> findByUsername(String username);

}
