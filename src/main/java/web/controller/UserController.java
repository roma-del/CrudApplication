package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getUsers(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/add")
    public ModelAndView ShowUsers() {
        ModelAndView mva = new ModelAndView();
        mva.setViewName("add");
        return mva;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        userService.addUser(user);
        return mav;
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteUsers(@RequestParam(name = "id", required = false) int id) {
        ModelAndView mva = new ModelAndView();
        mva.setViewName("redirect:/");
        userService.deleteUser(id);
        return mva;
    }


    @GetMapping(value = "/edit")
    public ModelAndView showEditForm(@RequestParam int id, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit");
        model.addAttribute("userId", id);
        return mav;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView mva = new ModelAndView();
        userService.changeUser(user);
        mva.setViewName("redirect:/");
        return mva;
    }

}
