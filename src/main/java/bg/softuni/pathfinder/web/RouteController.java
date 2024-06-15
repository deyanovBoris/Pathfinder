package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.models.Category;
import bg.softuni.pathfinder.models.CategoryType;
import bg.softuni.pathfinder.models.Level;
import bg.softuni.pathfinder.services.RouteService;
import bg.softuni.pathfinder.services.dto.AddRouteDTO;
import bg.softuni.pathfinder.services.dto.RouteShortInfoDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model){
        List<RouteShortInfoDTO> allRoutes = routeService.getAll();

        model.addAttribute("allRoutes", allRoutes);

        return "routes";
    }

    @ModelAttribute("addRouteDTO")
    public AddRouteDTO addRouteDTO(){
        return new AddRouteDTO();
    }

    @ModelAttribute("allLevelTypes")
    public Level[] allLevelTypes(){
        return Level.values();
    }
    @ModelAttribute("allCategories")
    public CategoryType[] allCategories(){
        return CategoryType.values();
    }

    @GetMapping("/routes/add-route")
    public String addRoute(Model model){

        return "add-route";
    }

    @PostMapping("/routes/add-route")
    public String addRoute(Model model,
                           @Valid AddRouteDTO addRouteDTO){
        System.out.println();

        return "redirect:/";
    }
}
