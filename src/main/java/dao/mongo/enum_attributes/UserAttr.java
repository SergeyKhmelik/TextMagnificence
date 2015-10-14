package dao.mongo.enum_attributes;

/**
 * @author Koloturka
 * @version 04.09.2015 18:42
 */
public enum UserAttr implements Attribute {
	NAME("name"), PASSWORD("password"), EMAIL("email"), ROLE("role"),
	AGE("age"), ENABLED("enabled"), LOCKED("locked");

	private String name;

	private UserAttr(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
