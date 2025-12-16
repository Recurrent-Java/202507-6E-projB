package com.example.springlesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.Voice;

public interface VoiceRepository extends JpaRepository<Voice, Long> {

 public  List<Voice> findTop2ByOrderByIdAsc();
}
