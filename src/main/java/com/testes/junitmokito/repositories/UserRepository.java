package com.testes.junitmokito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testes.junitmokito.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

}
