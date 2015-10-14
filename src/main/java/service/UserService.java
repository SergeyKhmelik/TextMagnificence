package service;

import domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * @author Koloturka
 * @version 22.09.2015 13:00
 */
public interface UserService extends UserDetailsService {

	User registerUser(User user) throws UserPrincipalNotFoundException;

}
