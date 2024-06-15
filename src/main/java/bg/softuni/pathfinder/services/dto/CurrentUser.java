package bg.softuni.pathfinder.services.dto;

import bg.softuni.pathfinder.models.Role;
import bg.softuni.pathfinder.models.User;
import bg.softuni.pathfinder.models.UserRoles;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {
    private User user;
    private boolean isLoggedIn;

    public CurrentUser() {
    }


    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public User getUser() {
        return user;
    }

    public CurrentUser setUser(User user) {
        this.user = user;
        return this;
    }

    public void clean(){
        this.user = null;
        this.isLoggedIn = false;
    }

    public boolean isAdmin(){
        for (Role role : this.user.getRoles()) {
            if (role.getName().equals(UserRoles.ADMIN)){
                return true;
            }
        }
        return false;
    }
}
