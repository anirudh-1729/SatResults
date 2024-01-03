package com.practise.webservices.RESTfulWebservices.Users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practise.webservices.RESTfulWebservices.ResTfulWebservicesApplication;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;


@RestController
public class UserResource {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(UserResource.class);
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		
		this.service = service;
	}
	
	
	@GetMapping("/users")
	public List<user> retreiveAllUsers(){
		
		return service.FindAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<user> findUser(@PathVariable int id){
		
		
		user User = service.findOne(id);
		
		if(User == null)
			throw new UserNotFoundException("id:" + id);
		
		EntityModel<user> entitymodel = EntityModel.of(User);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retreiveAllUsers());
		entitymodel.add(link.withRel("all-users"));
		
		return entitymodel;
	}
	
	
	@DeleteMapping("/users/{id}")
	public void DeleteUser(@PathVariable int id){
		
		service.DeleteUser(id);
		
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<user> CreateUser(@Valid @RequestBody user User) {
		
		logger.debug("POSTED-Anirudh") ;
		
		user saved_user = service.SaveUser(User);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved_user.getId())
				.toUri();
		
		return ResponseEntity.created(location ).build();
	}
	
	

}
