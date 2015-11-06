package service;

import domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UserService extends UserDetailsService {

	User registerUser(User user) throws UserPrincipalNotFoundException;

}
