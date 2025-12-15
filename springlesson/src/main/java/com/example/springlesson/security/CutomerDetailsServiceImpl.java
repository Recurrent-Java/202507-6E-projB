package com.example.springlesson.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springlesson.entity.User;
import com.example.springlesson.repository.AuthRepository;

@Service
public class CutomerDetailsServiceImpl implements UserDetailsService {
  private final AuthRepository customerRepository;
  
  // コンストラクターインジェクション
  public CutomerDetailsServiceImpl(AuthRepository customerRepository) {
    this.customerRepository = customerRepository;
  }



  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    try {
      User customer = customerRepository.findByLogin(login);
      if(customer == null) {
        throw new UsernameNotFoundException("ユーザーが見つかりません：" + login);
      }
      // 権限(とりあえずここに固定しておきます)
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      
      return new CustomerDetailsImpl(customer, authorities);
    }catch(Exception e) {
      throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
    }
  }

}
