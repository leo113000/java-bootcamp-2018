package com.globant.bootcamp.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.bootcamp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * This class works like a wrapper of the User's model
 * to be understanded by Spring Security
 */
@Getter @Setter @AllArgsConstructor public class UserCredentials implements UserDetails {
	private Long id;

	private String username;

	@JsonIgnore private String email;

	@JsonIgnore private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public static UserCredentials create(User user) {
		return new UserCredentials(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), createAuthority());
	}

	/**
	 * Hardcoded authority
	 *
	 * @return
	 */
	public static Collection<? extends GrantedAuthority> createAuthority() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return true;
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override public boolean isEnabled() {
		return true;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserCredentials that = (UserCredentials) o;
		return Objects.equals(id, that.id);
	}

	@Override public int hashCode() {

		return Objects.hash(id);
	}

	public User getUser() {
		User user = new User(this.email, this.username, this.password);
		user.setId(this.id);
		return user;
	}

}
