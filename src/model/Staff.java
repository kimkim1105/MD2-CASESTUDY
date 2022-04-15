package model;

import java.io.Serializable;
import java.util.Set;

public class Staff implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
    private boolean status;
    private String workingType;
    private Set<Role> roleSet;

    public Staff() {
    }

    public Staff(int id, String name, String username, String password, boolean status, String workingType, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.status = status;
        this.workingType = workingType;
        this.roleSet = roleSet;
    }

    public Staff(int id, String name, boolean status, String workingType) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.workingType = workingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", workingType='" + workingType + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }

//        if (isStatus()) {
//            return String.format("\t|%-3s | %-10s | %-15s | %-10s |%n", id, name, "Dang lam viec", workingType);
//        }
//        return String.format("\t|%-3s | %-10s | %-15s | %-10s |%n", id, name, "Da nghi viec", workingType);
//        }

}
