package bg.softuni.pathfinder.services;


import bg.softuni.pathfinder.models.Route;
import bg.softuni.pathfinder.services.dto.RouteShortInfoDTO;

import java.util.List;

public interface RouteService {
    public RouteShortInfoDTO getRandomRoute();
    public List<RouteShortInfoDTO> getAll();
}
