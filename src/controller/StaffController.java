package controller;

import model.Staff;
import service.Staff.StaffServiceIMPL;

import java.util.List;

public class StaffController {
    StaffServiceIMPL staffServiceIMPL = new StaffServiceIMPL();
    public void addStaff(Staff staff){
        staffServiceIMPL.add(staff);
    }
    public List<Staff> showStaffList(){
       return staffServiceIMPL.findAll();
    }
    public List<Staff> searchStaffByName(String name){
        return staffServiceIMPL.searchByName(name);
    }
    public List<Staff> searchStaffByWorkingType(String workingType){
        return staffServiceIMPL.searchByWorkingType(workingType);
    }
    public List<Staff> searchStaffWithStatus(boolean status){
        return staffServiceIMPL.searchByStatus(status);
    }
    public List<String> searchStatusByName(String name){
        return staffServiceIMPL.searchStatusByName(name);
    }
    public void editStaffById(int id,String name,String workingType){
        staffServiceIMPL.editStaff(id,name,workingType);
    }
    public void changeStatusById(int id, boolean status){
        staffServiceIMPL.changeStatus(id,status);
    }
}
