package view;

import config.ConfigReadAndWriteFile;
import dto.SignUpDTO;
import model.Staff;
import service.Staff.StaffServiceIMPL;

import java.util.List;
import java.util.Scanner;

public class LoginView {

    Scanner scanner = new Scanner(System.in);
    StaffServiceIMPL staffServiceIMPL = new StaffServiceIMPL();
    public static String path = "D:\\java\\GIT\\MODULE 2\\HUMAN - Copy\\src\\data\\login.csv";
    public static List<Staff> user = new ConfigReadAndWriteFile<Staff>().readFromFile(path);
    public LoginView(){
        System.out.println("===========LOGIN===========");
        System.out.println("Nhap username");
        String username="";
        boolean checkUsername;
        while (true){
            username = scanner.nextLine();
            checkUsername = staffServiceIMPL.searchByUsername(username);
            if (!checkUsername){
                System.out.println("Username khong dung");
            }else {
                break;
            }
        }
        System.out.println("Nhap password");
        String password;
        boolean checkPassword;
        while (true){
            password = scanner.nextLine();
            checkPassword = staffServiceIMPL.searchPasswordByUsername(username,password);
            if (!checkPassword){
                System.out.println("Username khong dung");
            }else {
                break;
            }
        }
        user.clear();
        user.add(new Staff(staffServiceIMPL.searchIdByUsername(username),staffServiceIMPL.searchNameByUsername(username),username,password,staffServiceIMPL.searchStatusByUsername(username),staffServiceIMPL.searchWorkingTypeByUsername(username), staffServiceIMPL.searchRoleByUsername(username)));
        new ConfigReadAndWriteFile<Staff>().writeToFile(path,user);
        if (staffServiceIMPL.searchStatusByUsername(username)==false){
            System.out.println(user);
            new Main();
        }else {
            new Menu();
        }
    }
}
