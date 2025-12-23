package com.example.springlesson.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserUpdateForm {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "\\d{7}", message = "郵便番号は7桁で入力してください")
    private String postalCode;

    @NotBlank
    private String prefecture;

    @NotBlank
    private String city;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    private boolean dmOptIn;
}
