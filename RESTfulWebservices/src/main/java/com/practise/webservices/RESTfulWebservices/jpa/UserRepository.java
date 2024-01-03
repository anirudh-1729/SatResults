package com.practise.webservices.RESTfulWebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.webservices.RESTfulWebservices.Users.user;

public interface UserRepository extends JpaRepository<user,Integer>{

}
