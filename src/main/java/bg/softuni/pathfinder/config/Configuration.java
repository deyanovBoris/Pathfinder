package bg.softuni.pathfinder.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Random random(){
        return new Random();
    }
}
