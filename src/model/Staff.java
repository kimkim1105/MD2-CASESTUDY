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
    private int daysOfWorking;
    private int dayOff;
    private double basicSalary;
    private double totalSalary;
    private int workingDayInMonth;

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

    public Staff(int id, int dayOff) {
        this.id = id;
        this.dayOff = dayOff;
    }

    public Staff(int id, String name, boolean status, String workingType, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.workingType = workingType;
        this.roleSet = roleSet;
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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    public int getDaysOfWorking() {
        return daysOfWorking;
    }

    public void setDaysOfWorking(int daysOfWorking) {
        this.daysOfWorking = daysOfWorking;
    }

    public int getDayOff() {
        return dayOff;
    }

    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getWorkingDayInMonth() {
        return workingDayInMonth;
    }

    public void setWorkingDayInMonth(int workingDayInMonth) {
        this.workingDayInMonth = workingDayInMonth;
    }

    @Override
    public String toString() {
//        return "Staff{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", status=" + status +
//                ", workingType='" + workingType + '\'' +
//                ", roleSet=" + roleSet+
//                "}\n";
        if (isStatus()) {
            return String.format("\t|%-3s | %-15s | %-15s | %-15s | %-15s | %-10s | %-30s |%n", id, name, username, password,"Dang lam viec",workingType,roleSet);
        }return String.format("\t|%-3s | %-15s | %-15s | %-15s | %-15s | %-10s | %-30s |%n", id, name, username, password,"Nghi viec",workingType,roleSet);
    }
public String printSalary(){
//    String salary = "id=" + id +
//                ", name='" + name + '\'' +
//                ", workingType='" + workingType + '\'' +
//                ", roleSet=" + roleSet +
//                ", DaysOfWork="+daysOfWorking+
//                ", DayOff="+dayOff+
//                ", basicSalary="+basicSalary+
//                ", totalSalary="+totalSalary+
//                ", wokingDayInMonth="+workingDayInMonth;
//        return salary;
    return String.format("\t|%-3s | %-15s | %-10s | %-30s |%-3s |%-3s |%-10s |%-10s |%-3s |%n", id, name, workingType,roleSet,daysOfWorking,dayOff,basicSalary,totalSalary,workingDayInMonth);
}

    //        if (isStatus()) {
//            return String.format("\t|%-3s | %-10s | %-15s | %-10s |%n", id, name, "Dang lam viec", workingType);
//        }
//        return String.format("\t|%-3s | %-10s | %-15s | %-10s |%n", id, name, "Da nghi viec", workingType);
//        }

}
