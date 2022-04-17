package service.Staff;

import config.ConfigReadAndWriteFile;
import model.Role;
import model.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StaffServiceIMPL implements IstaffService {
    public static String path = "D:\\java\\GIT\\MODULE 2\\HUMAN - Copy\\src\\data\\staffList.csv";
    public static List<Staff> staffList = new  ConfigReadAndWriteFile<Staff>().readFromFile(path);

    @Override
    public List<Staff> findAll() {
        return staffList;
    }
    public void add(Staff staff) {
        staffList.add(staff);
        save();
    }

    @Override
    public void save() {
      new ConfigReadAndWriteFile<Staff>().writeToFile(path,staffList);
    }

    public List<Staff> searchByName(String name) {
        List<Staff> listSearchByName = new ArrayList<>();
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getName().contains(name)){
                listSearchByName.add(staffList.get(i));
            }
        }
        return listSearchByName;
    }
    public boolean searchByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public boolean searchPasswordByUsername(String username,String password) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                if (staffList.get(i).getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    public Set<Role> searchRoleByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                return staffList.get(i).getRoleSet();
            }
        }
        return null;
    }
    public String searchNameByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                return staffList.get(i).getName();
            }
        }
        return null;
    }
    public int searchIdByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                return staffList.get(i).getId();
            }
        }
        return -1;
    }
    public boolean searchStatusByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                return staffList.get(i).isStatus();
            }
        }
        return false;
    }
    public String searchWorkingTypeByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUsername().equals(username)){
                return staffList.get(i).getWorkingType();
            }
        }
        return null;
    }
    public List<Staff> searchByStatus(boolean status) {
        List<Staff> listSearchByStatus = new ArrayList<>();
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).isStatus()==status){
                listSearchByStatus.add(staffList.get(i));
            }
        }
        return listSearchByStatus;
    }
    public List<Staff> searchByWorkingType(String workingType) {
        List<Staff> listSearchByWorkingType = new ArrayList<>();
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getWorkingType().equalsIgnoreCase(workingType)){
                listSearchByWorkingType.add(staffList.get(i));
            }
        }
        return listSearchByWorkingType;
    }

    public List<String> searchStatusByName(String name) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < staffList.size(); i++) {
            String status = "";
            if (staffList.get(i).getName().contains(name)){
                boolean h = staffList.get(i).isStatus();
                if (h){
                    status = "Dang lam viec";
                }else {
                    status= "Nghi viec";
                }
                list.add(staffList.get(i).getName()+" : "+status);
            }
        }
        return list;
    }
    public void editStaff(int id,String name,String workingType,Set<Role> roleSet){
        for (int i = 0; i < staffList.size(); i++) {
            if (id == staffList.get(i).getId()){
                staffList.get(i).setName(name);
                staffList.get(i).setWorkingType(workingType);
                staffList.get(i).setRoleSet(roleSet);
                System.out.println("Ban vua chinh sua nhan vien "+staffList.get(i));
            }
        }
        save();
    }
    public void changeStatus(int id,boolean status){
        for (int i = 0; i < staffList.size(); i++) {
            if (id == staffList.get(i).getId()){
                staffList.get(i).setStatus(status);
                System.out.println("Ban vua chinh sua nhan vien "+staffList.get(i));
            }
        }
        save();
    }

    @Override
    public boolean existedByUsername(String username) {
        for (int i = 0; i < staffList.size(); i++) {
            if (username.equals(staffList.get(i).getUsername())){
                return true;
            }
        }
        return false;
    }
}
