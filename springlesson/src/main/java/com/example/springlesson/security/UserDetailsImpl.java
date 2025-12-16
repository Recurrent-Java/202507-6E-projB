package com.example.springlesson.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springlesson.entity.User;

public class UserDetailsImpl implements UserDetails {
  private final User customer;
  private final Collection<GrantedAuthority> authorities;
  
  // コンストラクターインジェクション
  public UserDetailsImpl(User customer, Collection<GrantedAuthority> authorities) {
    this.customer = customer;
    this.authorities = authorities;
  }
  
  public User getCustomer() {
    return this.customer;
  }
  
  @Override
  public String getUsername() {
    return this.customer.getEmail();
  }
  
  @Override
  public String getPassword() {
    return this.customer.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

}
