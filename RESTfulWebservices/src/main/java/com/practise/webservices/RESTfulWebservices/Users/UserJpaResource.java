package com.practise.webservices.RESTfulWebservices.Users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.practise.webservices.RESTfulWebservices.jpa.JPARepository;
import com.practise.webservices.RESTfulWebservices.jpa.UserRepository;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;


@RestController
public class UserJpaResource {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(UserJpaResource.class);
	
	private UserRepository repository;
	private JPARepository postrepository;
	
	
	
	
	public UserJpaResource(UserRepository repository, JPARepository postrepository) {
		
		this.repository = repository;
		this.postrepository = postrepository;
	}
	
	
	@GetMapping("/jpa/users")
	public List<user> retreiveAllUsers(){
		
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<user> findUser(@PathVariable int id){
		
		
		Optional<user> User = repository.findById(id);
		
		if(User == null)
			throw new UserNotFoundException("id:" + id);
		
		EntityModel<user> entitymodel = EntityModel.of(User.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retreiveAllUsers());
		entitymodel.add(link.withRel("all-users"));
		
		return entitymodel;
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void DeleteUser(@PathVariable int id){
		
		repository.deleteById(id);
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> RetrievePostForUser(@PathVariable int id){
		
		Optional<user> User = repository.findById(id);
		
		if(User == null)
			throw new UserNotFoundException("id:" + id);
		
		return User.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public List<Post> CreatePostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		
		Optional<user> User = repository.findById(id);
		
		if(User == null)
			throw new UserNotFoundException("id:" + id);
		
		post.setUser(User.get());
		
		Post savedPost = postrepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return User.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<user> CreateUser(@Valid @RequestBody user User) {
		
		logger.debug("POSTED-Anirudh") ;
		
		user saved_user = repository.save(User);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved_user.getId())
				.toUri();
		
		return ResponseEntity.created(location ).build();
	}
	
	

}
