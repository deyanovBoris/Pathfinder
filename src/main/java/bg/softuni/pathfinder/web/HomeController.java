package bg.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        double firstTemp = Math.round(new Random().nextDouble()*10) + 10;
        double secondTemp = Math.round(new Random().nextDouble()*10) + 10;

        model.addAttribute("firstTemperature", firstTemp);
        model.addAttribute("secondTemperature", secondTemp);

        return "index";
    }
}
