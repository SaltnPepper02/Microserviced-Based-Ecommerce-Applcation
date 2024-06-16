package com.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.user.models.UserDetails;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
}
