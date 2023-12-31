package com.laborbus.DemoLabourbusapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laborbus.DemoLabourbusapi.Model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByMobileNumber(String mobileNumber);

}
