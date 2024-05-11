package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // Возвращаем имя представления (шаблона), где будут отображаться пользователи
    }
}
