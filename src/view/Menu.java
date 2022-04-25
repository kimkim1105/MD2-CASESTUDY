package view;

import model.Role;
import model.RoleName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    StaffView staffView = new StaffView();
    Role role = new Role();

    public Menu() {
        List<Role> roleList = new ArrayList<>(LoginView.user.get(0).getRoleSet());
//        checkLogin(roleList);
        System.out.println("==========Menu==========");
        System.out.println("1. Thao tác với nhân viên");
        System.out.println("2. Hiển thị danh sách nhân viên");
        System.out.println("3. Thông tin tài khoản");
        System.out.println("4. Thay đổi mật khẩu");
        System.out.println("5. Công - lương");
        System.out.println("6. Đăng xuất");
        String chooseMenu = scanner.nextLine();
        switch (chooseMenu) {
            case "1":
                if (checkLogin(roleList) > 1) {
                    staffView.operation();
                    break;
                } else {
                    System.out.println("Khong co quyen truy cap");
                    new Menu();
                }
            case "2":
                if (checkLogin(roleList) > 1) {
                    staffView.searchStaff();
                    break;
                } else {
                    System.out.println("Khong co quyen truy cap");
                    new Menu();
                }
                break;
            case "3":
                staffView.showUserInfo();
                break;
            case "4":
                staffView.changPassword();
                break;
            case "5":
                staffView.payroll();
                break;
            case "6":
                new Main();
                break;
            default:
                new Menu();
                break;
        }
    }

    public static int checkLogin(List<Role> roleList) {
        int check = 0;
        if (roleList.get(0).getName() == RoleName.STAFF) {
            check = 1;
        } else if (roleList.get(0).getName() == RoleName.ADMIN) {
            check = 2;
        } else {
            check = 3;
        }
        return check;
    }
}
