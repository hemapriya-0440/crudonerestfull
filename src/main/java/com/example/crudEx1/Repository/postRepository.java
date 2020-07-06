package com.example.crudEx1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudEx1.bean.Posts;

@Repository
public interface postRepository extends JpaRepository<Posts, Integer> {

}
