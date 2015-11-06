package domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

	@Pattern(regexp = "[a-zA-Z0-9]+[\\s]*", message = "Name should consist of english letters, numbers and space.")
	private String name;

	@Pattern(regexp = "[a-zA-Z0-9]{32}", message = "Password field should be 32symbol length (hash).")
	private String password;

	private Role role;

	@NotNull
	@Min(value = 12, message = "This game is dedicated for people of 12 years old and higher.")
	private int age;

	@NotNull
	private String email;

	@NotNull
	private boolean enabled;

	@NotNull
	private boolean locked;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				", age=" + age +
				", email='" + email + '\'' +
				", enabled=" + enabled +
				", locked=" + locked +
				'}';
	}
}
