package com.example.springlesson.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@IdClass(UserRoleId.class)
public class UserRole {

  @Id
  @Column(name = "user_id")
  private Long userId;
  
  @Id
  @Column(name = "role_id")
  private Long roleId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private Role role;
   
  @Column(name  ="created_at" , insertable = false, updatable = false)
  private LocalDateTime createdAt;
  
  @Column(name  ="updated_at" , insertable = false, updatable = false)
  private LocalDateTime updatedAt;
  
}
