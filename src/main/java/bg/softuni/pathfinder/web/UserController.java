package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.models.Level;
import bg.softuni.pathfinder.services.UserService;
import bg.softuni.pathfinder.services.dto.UserLoginDTO;
import bg.softuni.pathfinder.services.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO(){
        return new UserLoginDTO();
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO registerDTO(){
        return new UserRegisterDTO();
    }

    @ModelAttribute("allLevelTypes")
    public Level[] levelTypes(){
        return Level.values();
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model,
                        @Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes rAtt){

        if (bindingResult.hasErrors()){
            rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult
            );
            return "redirect:/users/login";
        }

        boolean successLogin = this.userService.loginUser(userLoginDTO);
        if (!successLogin){
            rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult
            );
            return "redirect:/users/login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String viewRegister(Model model, UserRegisterDTO userRegisterDTO){

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(Model model,
                             @Valid UserRegisterDTO userRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt){
        if (bindingResult.hasErrors()
//                || !userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())
                ){
            rAtt.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO"
                    , bindingResult);

            return "redirect:/users/register";
        }
        //if successful reg
        this.userService.registerUser(userRegisterDTO);

        return "redirect:/users/login";
    }

    @PostMapping("/logout")
    public String logout(){
        this.userService.logoutUser();

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("userProfileDTO", this.userService.getProfileInfo());

        return "profile";
    }
}
