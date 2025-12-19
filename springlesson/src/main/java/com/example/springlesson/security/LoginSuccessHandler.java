package com.example.springlesson.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.example.springlesson.entity.User;
import com.example.springlesson.repository.UserRepository;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final UserRepository userRepository;

  public LoginSuccessHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

@Override
public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication)
        throws IOException, ServletException {
  
    // ログイン成功時の処理をここに追加できます
  UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
  User user = userDetails.getUser();
  user.updateLastLogin();
  userRepository.save(user);
  super.onAuthenticationSuccess(request, response, authentication);
}
}