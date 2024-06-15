package bg.softuni.pathfinder.services.dto;

import bg.softuni.pathfinder.models.Level;

public class UserProfileInfoDTO {

    private String username;
    private String fullName;
    private Integer age;
    private Level level;

    public UserProfileInfoDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserProfileInfoDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileInfoDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfileInfoDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserProfileInfoDTO setLevel(Level level) {
        this.level = level;
        return this;
    }
}
