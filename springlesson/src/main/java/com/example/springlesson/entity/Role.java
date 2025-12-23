package com.example.springlesson.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "name",nullable = false, unique = true, length = 50)
  private String name;
  
  @Column(name = "description", length = 255)
  private String description;
  
  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDate createdAt;
  
  @Column(name = "updated_at", insertable = false, updatable = false)
  private LocalDate updatedAt;
  

}
