package com.example.springlesson.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.entity.User;
import com.example.springlesson.entity.UserAddress;
import com.example.springlesson.form.RegistForm;
import com.example.springlesson.repository.UserAddressRepository;
import com.example.springlesson.repository.UserRepository;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final UserAddressRepository userAddressRepository;
  private final PasswordEncoder passwordEncoder;
  
  
  public AuthService(
      UserRepository userRepository,
      UserAddressRepository userAddressRepository,
      PasswordEncoder passwordEncoder
      ) {
    this.userRepository = userRepository;
    this.userAddressRepository = userAddressRepository;
    this.passwordEncoder = passwordEncoder;
  }
  @Transactional
  public void SaveUser(RegistForm form) {
    User user =new User();
    user.setEmail(form.getEmail());
    user.setPassword(passwordEncoder.encode(form.getPassword()));
    user.setName(form.getName());
    user.setPhoneNumber(form.getPhoneNumber());
    user.setDmOptIn(form.isDmOptIn());
    
    UserAddress userAddress = new UserAddress();
    userAddress.setUser(user);
    userAddress.setRecipientName(form.getName());
    userAddress.setPostalCode(form.getPostalCode());
    userAddress.setPrefecture(form.getPrefecture());
    userAddress.setCity(form.getCity());
    userAddress.setAddressLine1(form.getAddressLine1());
    userAddress.setAddressLine2(form.getAddressLine2());
    //登録処理
    userRepository.save(user);
    userAddressRepository.save(userAddress);
  }
}
