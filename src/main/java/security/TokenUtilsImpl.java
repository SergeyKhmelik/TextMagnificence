package security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import service.mongo.MongoUserServiceImpl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Koloturka
 * @version 15.09.2015 13:41
 */
@Component
public class TokenUtilsImpl implements TokenUtils {

	@Override
	public String getToken(UserDetails userDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String tokenStr = userDetails.getUsername() + ':'
				+ userDetails.getAuthorities();
		return tokenStr;
	}

	@Override
	public String getToken(UserDetails userDetails, Long expiration) {
		return null;
	}

	@Override
	public boolean validate(String token) {
		return true;
	}

	@Override
	public UserDetails getUserFromToken(String token) throws Exception {
		String userName;

		Pattern pattern = Pattern.compile("(.*):()");
		Matcher matcher = pattern.matcher(token);
		if(matcher.find()){
			userName = matcher.group(1);
			return new MongoUserServiceImpl().loadUserByUsername(userName);
		} else {
			throw new Exception();
		}
	}

}
