package jarkz.institutescheduler.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class User implements UserDetails {
  
  public String username;
  public String password;

  public boolean expired = false;
  public boolean locked = false;
  public boolean credentialsNonExpired = true;
  public boolean enabled = true;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    throw new UnsupportedOperationException("Need to reimplement the 'getAuthorities' method in inherted classes");
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return expired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

}
