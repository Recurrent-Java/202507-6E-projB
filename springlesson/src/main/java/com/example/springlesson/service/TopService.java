package com.example.springlesson.service;

import java.util.List;

import com.example.springlesson.entity.Voice;
import com.example.springlesson.repository.VoiceRepository;

public class TopService {
private final VoiceRepository voiceRepository;

public TopService(VoiceRepository voiceRepository) {
  this.voiceRepository = voiceRepository;
}

public List<Voice> getTop2Voices() {
  return voiceRepository.findTop2ByOrderByIdAsc();
}

}
