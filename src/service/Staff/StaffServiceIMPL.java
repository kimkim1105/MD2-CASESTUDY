package service.Staff;

import config.ConfigReadAndWriteFile;
import model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffServiceIMPL implements IstaffService {
    public static String path = "D:\\java\\GIT\\MODULE 2\\HUMAN\\src\\data\\staffList.csv";
//    public static String path2 = "D:\\java\\GIT\\MODULE 2\\HUMAN\\src\\data\\quitjob.csv";
    public static List<Staff> staffList = new  ConfigReadAndWriteFile<Staff>().readFromFile(path);
//    public static List<Staff> listQuitJob = new  ConfigReadAndWriteFile<Staff>().readFromFile(path2);

    @Override
    public List<Staff> findAll() {
        return staffList;
    }
    public void add(Staff staff) {
        staffList.add(staff);
        save();
    }
//    @Override
//    public void addStaff(Staff staff) {
//        staffList.add(staff);
//        save();
//    }

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
    public void editStaff(int id,String name,String workingType){
        for (int i = 0; i < staffList.size(); i++) {
            if (id == staffList.get(i).getId()){
                staffList.get(i).setName(name);
                staffList.get(i).setWorkingType(workingType);
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
