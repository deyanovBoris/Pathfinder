package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.User;
import bg.softuni.pathfinder.models.UserRoles;
import bg.softuni.pathfinder.repositories.RoleRepository;
import bg.softuni.pathfinder.repositories.UserRepository;
import bg.softuni.pathfinder.services.UserService;
import bg.softuni.pathfinder.services.dto.CurrentUser;
import bg.softuni.pathfinder.services.dto.UserLoginDTO;
import bg.softuni.pathfinder.services.dto.UserProfileInfoDTO;
import bg.softuni.pathfinder.services.dto.UserRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(CurrentUser currentUser, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.getRoles().add(this.roleRepository.findByName(UserRoles.USER).get());

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        User user = this.userRepository
                .findByUsername(userLoginDTO.getUsername())
                .orElse(null);

        if (userLoginDTO.getPassword() == null
                || user == null
                || user.getPassword() == null){
            return false;
        }
        boolean passwordMatches = this.passwordEncoder.matches(
                userLoginDTO.getPassword(),
                user.getPassword());

        if (passwordMatches && !currentUser.isLoggedIn()){
            currentUser.setUser(user);
            currentUser.setLoggedIn(true);

        } else {
            currentUser.clean();
            return false;
        }

        return true;
    }

    @Override
    public boolean logoutUser() {
        if (!currentUser.isLoggedIn()){
            return false;
        }
        currentUser.clean();
        return true;
    }

    public UserProfileInfoDTO getProfileInfo(){
        return this.modelMapper.map(this.currentUser.getUser(), UserProfileInfoDTO.class);
    }
}
