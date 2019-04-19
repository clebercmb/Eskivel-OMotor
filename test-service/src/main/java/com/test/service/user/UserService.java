package com.test.service.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import org.springframework.stereotype.Service;


import com.test.model.dto.user.UserDTO;
import com.test.model.entities.user.User;
import com.test.repository.user.UserRepository;
import com.test.service.exception.ObjectNotFoundException;
	
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
		
	
	public List<User> findAll() {
		
		ConfigurableEnvironment env = context.getEnvironment();
		
		String host = env.getProperty("SPRING_RABBITMQ_HOST", env.getProperty("spring.rabbitmq.host"));
		LOGGER.debug(">>findAll().host=", host);

		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public User userFromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), objDto.getSexo());
	}
	
	public void delete(Long id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(User obj) {
		LOGGER.debug("User.id=" + obj.getId());
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		
		return userRepository.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}

}