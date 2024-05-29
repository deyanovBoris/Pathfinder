package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.Picture;
import bg.softuni.pathfinder.models.Route;
import bg.softuni.pathfinder.repositories.RouteRepository;
import bg.softuni.pathfinder.services.RouteService;
import bg.softuni.pathfinder.services.dto.RouteShortInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final Random random;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, Random random) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.random = random;
    }



    @Override
    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routeCount = this.routeRepository.count();
        long randomId = random.nextLong(routeCount) + 1;

        Optional<Route> byId = this.routeRepository.findById(randomId);
        return mapToShortInfo(byId.get());
    }

    @Override
    @Transactional
    public List<RouteShortInfoDTO> getAll() {
        List<RouteShortInfoDTO> allRoutes = this.routeRepository
                .findAll()
                .stream()
                .map(this::mapToShortInfo)
                .collect(Collectors.toList());
        return allRoutes;
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO routeDTO = modelMapper.map(route, RouteShortInfoDTO.class);

        Picture picture = route.getPictures().stream().findFirst().get();
        routeDTO.setImageUrl(picture.getUrl());

        return routeDTO;
    }
}
