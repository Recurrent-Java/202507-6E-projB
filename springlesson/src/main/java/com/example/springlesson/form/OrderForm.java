package com.example.springlesson.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderForm {

    @NotBlank
    private String recipientName;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String prefecture;

    @NotBlank
    private String city;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private String deliveryDate;

    @NotBlank
    private String deliveryTimeSlot;
}
