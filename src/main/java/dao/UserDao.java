package dao;

import domain.User;

/**
 * Created by koloturka on 16.07.15.
 */
public interface UserDao {

	User getUserByName(String username);

	String createUser(User user);

	String updateUser(User user);

	String upsertUser(String username, User user);

	String deleteUser(String username);

	String blockUser(String username, boolean block);

}
