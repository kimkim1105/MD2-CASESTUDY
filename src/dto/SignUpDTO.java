package dto;

import java.util.Set;

public class SignUpDTO {
    private String name;
    private String username;
    private String password;
    private Set<String> stringSetRole;

    public SignUpDTO() {
    }

    public SignUpDTO(String name, String username, String password, Set<String> stringSetRole) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.stringSetRole = stringSetRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<String> getStringSetRole() {
        return stringSetRole;
    }

    public void setStringSetRole(Set<String> stringSetRole) {
        this.stringSetRole = stringSetRole;
    }
}
