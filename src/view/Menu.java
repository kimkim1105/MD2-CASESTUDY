package view;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    StaffView staffView = new StaffView();
    public Menu() {
        System.out.println("==========Menu==========");
        System.out.println("1. Thêm Nhân viên");
        System.out.println("2. Tìm kiếm Nhân viên");
        System.out.println("3. Kiểm tra trạng thái Nhân viên");
        System.out.println("4. Sửa thông tin Nhân viên");
        System.out.println("5. Thay đổi trạng thái Nhân viên");
        System.out.println("6. Thông tin tài khoản");
        System.out.println("7. Công - lương");
        System.out.println("8. Đăng xuất");
        int chooseMenu = Integer.parseInt(scanner.nextLine());
        switch (chooseMenu) {
            case 1:
                staffView.createStaff();
                break;
            case 2:
                staffView.searchStaff();
                break;
            case 3:
                staffView.searchStatus();
                break;
            case 4:
                staffView.editStaffById();
                break;
            case 5:
                staffView.changeStatusById();
                break;
            case 6:
                staffView.showUserInfo();
                break;
            case 8:
                new Main();
                break;
        }
    }
}
