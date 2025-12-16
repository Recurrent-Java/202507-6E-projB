package com.example.springlesson.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springlesson.entity.Voice;
import com.example.springlesson.service.TopService;
// コントローラーからhtmlを呼び出す
@Controller
public class TopController {
  
 private final TopService topService;
 
 public TopController(TopService topService) {
   this.topService = topService;
   }
 
  @GetMapping("/")
  public String index(Model model) {
    //商品レビューの取得
    List<Voice> voiceList = topService.getTop2Voices();
    model.addAttribute("voiceList", voiceList);
    return "index";  // index.htmlを呼び出す
    
  }

}
