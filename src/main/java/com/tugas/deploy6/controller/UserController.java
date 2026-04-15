package com.tugas.deploy6.controller;

import com.tugas.deploy6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"dataMahasiswa", "nim"})
public class UserController {

    // Inisialisasi attribute list kosong jika belum ada di session
    @ModelAttribute("dataMahasiswa")
    public List<User> initDataMahasiswa() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        if (model.containsAttribute("nim")) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        if ("admin".equals(username)) {
            // Kita simpan password sebagai nim ke dalam session model
            model.addAttribute("nim", password);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username harus 'admin'.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        if (!model.containsAttribute("nim")) {
            return "redirect:/";
        }
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        if (!model.containsAttribute("nim")) {
            return "redirect:/";
        }
        // Membuat object kosong untuk bind data form
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute User user, 
                             @ModelAttribute("dataMahasiswa") List<User> dataMahasiswa,
                             Model model) {
        if (!model.containsAttribute("nim")) {
            return "redirect:/";
        }
        dataMahasiswa.add(user);
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Menghapus semua SessionAttributes
        return "redirect:/";
    }
}
