package bg.softuni.pathfinder.services.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotEmpty
    @NotNull
    @Size(min = 4, max = 20)
    private String username;
    @NotEmpty
    @NotNull
    @Size(min = 4, max = 30)
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
