package del.ac.id.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import del.ac.id.demo.model.User;
import del.ac.id.demo.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto, int id);
}
