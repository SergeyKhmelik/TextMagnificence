package security;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Koloturka
 * @version 15.09.2015 13:41
 */

public interface TokenUtils {

	String getToken(UserDetails userDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	String getToken(UserDetails userDetails, Long expiration);

	boolean validate(String token);

	UserDetails getUserFromToken(String token) throws Exception;

}
