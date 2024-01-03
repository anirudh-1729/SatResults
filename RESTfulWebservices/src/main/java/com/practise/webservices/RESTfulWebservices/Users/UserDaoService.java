package com.practise.webservices.RESTfulWebservices.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<user> users  = new ArrayList<>();
	private static int UserCount = 0;
	
	static {
		users.add(new user(++UserCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new user(++UserCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new user(++UserCount,"Jim",LocalDate.now().minusYears(20)));
		users.add(new user(++UserCount,"Aditya",LocalDate.now().minusYears(20)));
		
	}
	
	public List<user> FindAll(){
		
		return users;
	}
	
	public user findOne(int id) {
		Predicate<? super user> predicate = users -> users.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void DeleteUser(int id) {
		// TODO Auto-generated method stub
		Predicate<? super user> predicate = users -> users.getId().equals(id); 
		users.removeIf(predicate);
	}
	

	public user SaveUser(user User) {
		
		User.setId(++UserCount);
		users.add(User);
		return User;
		
	}

	

	

}
