package service.mongo;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koloturka on 04.08.15.
 */
@Service
public class MongoUserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username != null && !username.isEmpty()) {
			System.out.println("Finding user bai name " + username);
			domain.User user = userDao.getUserByName(username);
			System.out.println("User found is dis one " + user);
			if (user != null) {
				List<GrantedAuthority> authorities =
						new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toValue().toUpperCase()));
				UserDetails userDetails = new User(
						user.getName(),
						user.getPassword(),
						user.isEnabled(),
						true,
						true,
						!user.isLocked(),
						authorities);
				System.out.println(userDetails.toString());
				return userDetails;
			}
			throw new UsernameNotFoundException(
					"User '" + username + "' not found.");
		} else {
			throw new UsernameNotFoundException(
					"User '" + username + "' not found.");

		}
	}

	@Override
	public domain.User registerUser(domain.User user) throws UserPrincipalNotFoundException {
		String username = userDao.createUser(user);
		domain.User result = userDao.getUserByName(username);
		if(result == null){
			throw new UserPrincipalNotFoundException("No such user with name " + username);
		}
		return result;
	}
}