package com.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.user.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    public User findByEmail(String email);
}
