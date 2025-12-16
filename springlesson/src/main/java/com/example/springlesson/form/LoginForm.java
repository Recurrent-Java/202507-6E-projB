package com.example.springlesson.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {
// ログインID(メールアドレス)
  @NotBlank(message = "ログインIDを入力してください。")
  @Email(message = "有効なメールアドレスを入力してください。")
  private String loginId;
  // パスワード
  @NotBlank(message = "パスワードを入力してください。")
  private String password;
  
}
