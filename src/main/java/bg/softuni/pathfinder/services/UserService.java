package bg.softuni.pathfinder.services;


import bg.softuni.pathfinder.services.dto.UserLoginDTO;
import bg.softuni.pathfinder.services.dto.UserProfileInfoDTO;
import bg.softuni.pathfinder.services.dto.UserRegisterDTO;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    boolean loginUser(UserLoginDTO userLoginDTO);

    boolean logoutUser();

    UserProfileInfoDTO getProfileInfo();
}
