package com.example.springlesson.controller;

import java.security.Principal;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springlesson.form.UserUpdateForm;
import com.example.springlesson.service.UserService;

@Controller
@RequestMapping("/mypage")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** マイページ表示 */
    @GetMapping
    public String mypage(Principal principal, Model model) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "mypage/mypage";
    }

    /** 会員情報編集画面（editpro.html） */
    @GetMapping("/editpro")
    public String editPro(Principal principal, Model model) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("userUpdateForm", new UserUpdateForm());
        return "mypage/editpro"; 
    }

    /** 更新処理 */
    @PostMapping("/editpro")
    public String update(
            @Valid @ModelAttribute UserUpdateForm form,
            BindingResult result,
            Principal principal) {

        if (result.hasErrors()) {
            return "mypage/editpro"; 
        }

        userService.updateUser(principal.getName(), form);
        return "redirect:/mypage";
    }

    /** 退会 */
    @PostMapping("/withdraw")
    public String withdraw(Principal principal) {
        userService.withdraw(principal.getName());
        return "redirect:/logout";
    }
}
