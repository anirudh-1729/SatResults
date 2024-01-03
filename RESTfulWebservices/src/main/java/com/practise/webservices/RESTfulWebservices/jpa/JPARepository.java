package com.practise.webservices.RESTfulWebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.webservices.RESTfulWebservices.Users.Post;
import com.practise.webservices.RESTfulWebservices.Users.user;

import jakarta.validation.Valid;

public interface JPARepository extends JpaRepository<Post,Integer>{

	Post save(@Valid Post post);

}
