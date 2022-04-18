package controller;

import dto.SignUpDTO;
import model.Role;
import model.RoleName;
import model.Staff;
import service.Staff.StaffServiceIMPL;
import service.user.RoleServiceIMPL;

import java.util.*;

public class StaffController {
    RoleServiceIMPL roleServiceIMPL = new RoleServiceIMPL();
    StaffServiceIMPL staffServiceIMPL = new StaffServiceIMPL();

    public void register(SignUpDTO signUpDTO){
        Scanner scanner = new Scanner(System.in);
        int id;
        if (staffServiceIMPL.staffList.size()==0){
            id = 1;
        }else {
            id = staffServiceIMPL.staffList.get(staffServiceIMPL.staffList.size()-1).getId()+1;
        }
        System.out.println("Nhap hinh thuc lam viec");
        String workingType = scanner.nextLine();
        Set<String> stringSetRole = signUpDTO.getStringSetRole();
//        System.out.println(stringSetRole);
        Set<Role> roleSet = new HashSet<>();
        stringSetRole.forEach(role->{
            switch (role) {
                case "staff":
                    Role staffRole = roleServiceIMPL.findByName(RoleName.STAFF);
                    roleSet.add(staffRole);
                    break;
                case "admin":
                    Role adminRole = roleServiceIMPL.findByName(RoleName.ADMIN);
                    roleSet.add(adminRole);
                    break;
                case "manager":
                    Role managerRole = roleServiceIMPL.findByName(RoleName.MANAGER);
                    roleSet.add(managerRole);
                    break;
            }
        });
        Staff staff = new Staff(id,signUpDTO.getName(),signUpDTO.getUsername(),signUpDTO.getPassword(),true,workingType,roleSet);
//        System.out.println(roleSet);
        staffServiceIMPL.add(staff);
    }

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
    public void editStaffById(int id,String name,String workingType,Set<Role> roleSet){
        staffServiceIMPL.editStaff(id,name,workingType,roleSet);
    }
    public void changeStatusById(int id, boolean status){
        staffServiceIMPL.changeStatus(id,status);
    }
    public void takeSalary(int workingDayInMonth,Map<Integer,Integer> staffOff){
        staffServiceIMPL.payroll(workingDayInMonth,staffOff);
    }
    public void getSalaryById(int id, List<Staff> staffList){
        staffServiceIMPL.getSalaryById(id,staffList);
    }
}
