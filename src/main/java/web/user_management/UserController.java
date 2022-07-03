package web.user_management;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/registration")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid @RequestBody User user) throws UserPrincipalNotFoundException {
		String userName = userService.registerUser(user).getName();
		UserDetails userDetails = userService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
		return new ModelAndView("redirect:/stories");
	}


}
