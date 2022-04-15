package model;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
    private Set<Role> roleSet;

    public User() {
    }

    public User(int id, String name, String username, String password, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roleSet = roleSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }
}
