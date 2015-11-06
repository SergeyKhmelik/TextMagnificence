package dao.mongo;

import com.mongodb.*;
import dao.ConverterUtil;
import dao.UserDao;
import dao.mongo.enum_attributes.UserAttr;
import domain.User;
import exceptions.NoSuchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Koloturka
 * @version 04.09.2015 18:25
 */

@Component
public class MongoUserDaoImpl implements UserDao{

	public static final String DB_NAME = "TextMagnificence";
	public static final String USERS_COLLECTION = "users";

	private ConverterUtil converterUtil;
	private DB textGameDB;
	private DBCollection users;

	@Autowired
	public void setConverterUtil(ConverterUtil converterUtil) {
		this.converterUtil = converterUtil;
	}

	@Autowired
	public void setMongoClient(Mongo mongoClient) {
		this.textGameDB = mongoClient.getDB(DB_NAME);
		this.users = textGameDB.getCollection(USERS_COLLECTION);
	}

	@Override
	public User getUserByName(String username) {
		User result = null;
		DBObject userObject = users.findOne(new BasicDBObject(UserAttr.NAME.getName(), username));
		if(userObject != null) {
			result = converterUtil.convertDBObjectToUser(userObject);
		}
		return result;
	}

	@Override
	public String createUser(User user) {
		users.insert(converterUtil.convertUserToDBObject(user));
		return user.getName();
	}

	@Override
	public String updateUser(User user) {
		return null;
	}

	@Override
	public String upsertUser(String username, User user) {
		DBObject findQuery = new BasicDBObject(UserAttr.NAME.getName(), username);
		if (!users.update(findQuery, converterUtil.convertUserToDBObject(user), true, false).isUpdateOfExisting()){
			throw new NoSuchDataException(User.class, username);
		}
		return user.getName();
	}

	@Override
	public String deleteUser(String username) {
		if(users.remove(new BasicDBObject(UserAttr.NAME.getName(), username)).isUpdateOfExisting()){
			throw new NoSuchDataException(User.class, username);
		}
		return "User '" + username + "' has been successfully deleted.";
	}

	@Override
	public String blockUser(String username, boolean block) {
		DBObject findQuery = new BasicDBObject(UserAttr.NAME.getName(), username);
		DBObject updateQuery = new BasicDBObject("$set", new BasicDBObject(UserAttr.LOCKED.getName(), block));
		users.update(findQuery, updateQuery);
		if(!block){
			return "User '" + username + "' has been successfully unblocked.";
		}
		return "User '" + username + "' has been successfully blocked.";
	}

}
