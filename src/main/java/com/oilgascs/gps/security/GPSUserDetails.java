package com.oilgascs.gps.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;

import com.oilgascs.gps.domain.BusinessUnit;


public class GPSUserDetails implements  org.springframework.security.core.userdetails.UserDetails {
	
	private BusinessUnit bu;
	private String password;
	private final String username;
	private final List<GrantedAuthority> authorities;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	public BusinessUnit getBu() {
		return bu;
	}

	public void setBu(BusinessUnit bu) {
		this.bu = bu;
	}

	public GPSUserDetails( String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			List<GrantedAuthority> authorities) {
		this.username= username;
		this.password = password;
		this.enabled=enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired=credentialsNonExpired;
		this.accountNonLocked=accountNonLocked;
		this.authorities = authorities;
		
		
		// TODO Auto-generated constructor stub
	}

	public GPSUserDetails(String username, String password, List<GrantedAuthority> authorities) {
		this.username= username;
		this.password = password;
		this.authorities = authorities;
		// TODO Auto-generated constructor stub
		this.enabled=true;
		this.accountNonExpired = true;
		this.credentialsNonExpired=true;
		this.accountNonLocked=true;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
}