package model;

import java.io.Serializable;
import java.util.Set;

public class Salary implements Serializable {
    private int daysOfWorking;
    private int dayOff;
    private double basicSalary;
    private double totalSalary;
    private int workingDayInMonth;

    public Salary() {
    }

    public Salary(int daysOfWorking, int dayOff, double basicSalary, double totalSalary, int workingDayInMonth) {
        this.daysOfWorking = daysOfWorking;
        this.dayOff = dayOff;
        this.basicSalary = basicSalary;
        this.totalSalary = totalSalary;
        this.workingDayInMonth = workingDayInMonth;
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
}
