package com.test.config.user;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.test.model.entities.user.User;
import com.test.repository.user.UserRepository;

@Configuration
@EnableTransactionManagement
@EntityScan("com.test.model.entities")
@EnableJpaRepositories 
({
	"com.test.repository"})
public class InstantiationUserConfig implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationUserConfig.class);

	@Autowired
	private ApplicationContext context;

	
	@Autowired
	private UserRepository userRepository;  //// Injection database repository  	
	
	@Override
	public void run(String... args) throws Exception {

		LOGGER.debug("Instantiation config.");
		LOGGER.debug(">>>>run().context.getApplicationName()=", context.getApplicationName());

		
		userRepository.deleteAll();
		User cleber = new User(null, "Cleber Miranda Barbosa", "cleber@gmail.com", "(11)98341-9065", "M");	
		User camila = new User(null, "Camila Miranda Barbosa", "camila@gmail.com", "(11)94642-9065", "F");
		User domingas = new User(null, "Domingas P Roncoleta", "domingas@gmail.com", "(11)98341-9065", "F");
		
		userRepository.saveAll(Arrays.asList(cleber, camila, domingas));
		
	}
	
}