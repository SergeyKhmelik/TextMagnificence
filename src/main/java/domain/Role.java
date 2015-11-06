package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@JsonSerialize(using = RoleSerializer.class)
public enum Role {

	ADMIN("admin"), USER("user"), GUEST("guest");

	private final String value;

	private Role(String value) {
		this.value = value;
	}

	public static Role fromValue(String value) {
		if (value != null) {
			for (Role role : values()) {
				if (role.value.equals(value)) {
					return role;
				}
			}
		}
		return getDefault();
	}

	public String toValue() {
		return value;
	}

	public static Role getDefault() {
		return GUEST;
	}

}



