package com.test.api.resources.user;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.test.model.dto.user.UserDTO;
import com.test.model.entities.user.User;
import com.test.service.user.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserResource {
	@Autowired
	private UserService service;  // Injection service

	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		
		LOGGER.debug("RequestMethod.GET User >>>> ");

		List<User> userList = service.findAll(); 
		
		List<UserDTO> listDto = userList.stream().map( p  -> new UserDTO(p) ).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //End point path to get user by id. Path = /users/id
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		
		User user = service.findById(id);
				
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid  UserDTO objDto) {
		User user = service.userFromDTO(objDto);
		user = service.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		//System.out.println(">>>" + uri);
		
		return ResponseEntity.created(uri).build(); //ResponseEntity.created(uri) will return an empty answer with the code 201
													//and a header having the location of the new resource created
														
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) //End point path to get user by id. Path = /users/id
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		service.delete(id);
				
		return ResponseEntity.noContent().build(); //This operation returns nothing. When it happens, the return code is 204
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid UserDTO objDto, @PathVariable Long id) {
		User user = service.userFromDTO(objDto);
		user.setId(id);
		
		user = service.update(user);
		
		return ResponseEntity.noContent().build(); 	
	}	
	
	
	
}
