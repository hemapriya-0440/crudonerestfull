package com.example.crudEx1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudEx1.bean.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer> {

}
