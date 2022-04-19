package view;

import controller.StaffController;
import dto.SignUpDTO;
import model.Validate;
import service.Staff.StaffServiceIMPL;
import service.user.RoleServiceIMPL;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class RegisterView {
    RoleServiceIMPL roleServiceIMPL = new RoleServiceIMPL();
    Scanner scanner = new Scanner(System.in);
    StaffController staffController = new StaffController();
    StaffServiceIMPL staffServiceIMPL = new StaffServiceIMPL();

    public RegisterView(){
        roleServiceIMPL.findAll();
        System.out.println("Nhap ten:");
        String name = scanner.nextLine() ;
        System.out.println("Nhap username (chữ thường và số viết liền không dấu, ít nhất 6 ký tự:");
        String username;
        boolean checkUsername;
        while (true){
            username = scanner.nextLine();
            checkUsername = Validate.isvalid(username,Validate.USERNAME_REGEX);
//            checkUsername = Pattern.matches("^[a-z0-9]{6,}$",username);
            if (!checkUsername){
                System.err.println("Sai cau truc username, nhap lai");
            }else if (staffServiceIMPL.existedByUsername(username)){
                System.err.println("Username da ton tai, nhap lai");
            }else {
                break;
            }
        }
        System.out.println("Nhap password, ky tu dau la chu hoa, toi thieu 6 ky tu");
        String password;
        boolean checkPassword;
        while (true){
            password = scanner.nextLine();
            checkPassword = Validate.isvalid(password,Validate.PASSWORD);
//            checkPassword = Pattern.matches("^[A-Z]{1}[a-zA-Z0-9]{5,}$",password);
            if (!checkPassword){
                System.out.println("Nhap lai password");
            }else {
                break;
            }
        }
        System.out.println("Nhap vi tri lam viec");
        String role;
        boolean checkRole;
        while (true){
            role = scanner.nextLine();
            checkRole = Pattern.matches("admin|manager",role);
            if (!checkRole){
                System.out.println("vi tri lam viec sai");
            }else {
                break;
            }
        }
        Set<String> stringSetRole = new HashSet<>();
        stringSetRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(name,username,password,stringSetRole);
        staffController.register(signUpDTO);
        System.out.println("Nhan vien moi dang ky"+staffServiceIMPL.staffList.get(staffServiceIMPL.staffList.size()-1));
        new Main();
    }
}
