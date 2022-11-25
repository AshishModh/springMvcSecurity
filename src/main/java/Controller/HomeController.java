package Controller;


import Model.UserEntity;
import Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("")
@Controller
public class  HomeController {

    private UserService service;

    public HomeController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String PrintHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC FrameWork");
        return "HomePage";
    }

    @RequestMapping("/user-form")
    public String showForm(Model m) {
        m.addAttribute("command", new UserEntity());
        return "user-form";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("userEntity") UserEntity userEntity) {
        service.save(userEntity);
        return "redirect:/list-user";
    }

    @RequestMapping(path = "/list-user", method = RequestMethod.GET)
    public String getAllUser(Model m) {
        List<UserEntity> list = service.getAllUser();
        m.addAttribute("list", list);
        return "list-user";
    }

    @RequestMapping(path = "/editUser/{id}")
    public String edit(@PathVariable int id, Model m) {
        UserEntity user = service.getUserByID(id);
        m.addAttribute("command", user);
        return "user-edit-form";
    }

    @RequestMapping(path = "/editsave", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("userEntity") UserEntity userEntity) {
        service.update(userEntity);
        return "redirect:/list-user";
    }

    @RequestMapping(path = "/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "redirect:/list-user";

    }

    @RequestMapping(path = "/hello")
    public String helloUser(ModelMap model){
         model.addAttribute("message", "This is public page");
        return "helloUser";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(){
        return "login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logoutForm(ModelMap map){
        map.addAttribute("logout", "You've Logged Out Successfully!!!!");
        return "logout";
    }




}
