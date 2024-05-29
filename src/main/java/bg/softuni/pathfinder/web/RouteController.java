package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.services.RouteService;
import bg.softuni.pathfinder.services.dto.RouteShortInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
