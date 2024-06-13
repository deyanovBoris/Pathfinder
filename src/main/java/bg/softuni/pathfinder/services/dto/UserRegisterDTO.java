package bg.softuni.pathfinder.services.dto;

import bg.softuni.pathfinder.models.Level;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class UserRegisterDTO {

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 20)
    private String username;
    @NotEmpty
    @NotNull
    @Size(min = 5)
    private String fullName;
    @Email
    @NotNull
    @NotEmpty
    private String email;
    @Min(0)
    @Max(90)
    private Integer age;
    @NotNull
    private Level level;
    @Size(min = 5)
    @NotEmpty
    @NotNull
    private String password;
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
