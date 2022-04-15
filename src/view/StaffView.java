package view;

import controller.StaffController;
import model.Staff;
import service.Staff.StaffServiceIMPL;

import java.util.List;
import java.util.Scanner;

public class StaffView {
    Scanner scanner = new Scanner(System.in);
    List<Staff> staffList = StaffServiceIMPL.staffList;
    StaffController staffController = new StaffController();
    public void createStaff(){
        while (true){
            int id;
            if (staffList.size()==0){
                id = 1;
            }else {
                id = staffList.get(staffList.size()-1).getId()+1;
            }
            System.out.println("Nhap ten nhan vien: ");
            String name = scanner.nextLine();
            System.out.println("Nhap hinh thuc lam viec: ");
            String workingType = scanner.nextLine();
            Staff staff = new Staff(id,name,true,workingType);
            staffController.addStaff(staff);
            System.out.println("Nhap ky tu bat ky de tiep tuc hoac nhap 'quit' de ve menu");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")){
                new Main();
            }
        }
    }
//    public void showStaffList(){
//        System.out.println("1. Danh sach nhan vien dang lam viec");
//        System.out.println("2. Danh sach nhan vien da nghi viec");
//        int choose = Integer.parseInt(scanner.nextLine());
//        switch (choose){
//            case 1:
//                System.out.println(staffController.showStaffList());
//                break;
//            case 2:
//                System.out.println(staffController.showStaffQuitJobList());
//                break;
//        }
//        new Main();
//    }
    public void searchStaff(){
        System.out.println("1. Tim theo ten");
        System.out.println("2. Tim theo trang thai");
        System.out.println("3. Tim theo loai hinh lam viec");
        System.out.println("4. Hien thi tat ca nhan vien");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                System.out.println("Nhap ten can tim kiem: ");
                String name = scanner.nextLine();
                System.out.println(staffController.searchStaffByName(name));
                new Main();
                break;
            case 2:
                System.out.println("1. Nhan vien dang lam viec");
                System.out.println("2. Nhan vien da nghi viec");
                int choosestatus = Integer.parseInt(scanner.nextLine());
                switch (choosestatus){
                    case 1:
                        System.out.println(staffController.searchStaffWithStatus(true));
                        break;
                    case 2:
                        System.out.println(staffController.searchStaffWithStatus(false));
                        break;
                }
                new Main();
                break;
            case 3:
                System.out.println("Nhap loai hinh lam viec: ");
                String workingType = scanner.nextLine();
                System.out.println(staffController.searchStaffByWorkingType(workingType));
                new Main();
                break;
            case 4:
                System.out.println(staffController.showStaffList());
                new Main();
                break;
        }

    }
    public void searchStatus(){
        System.out.println("Nhap ten tim kiem: ");
        String name = scanner.nextLine();
        for (String str:
                staffController.searchStatusByName(name)) {
            System.out.println(str);
        }
        new Main();
    }
    public void editStaffById(){
        System.out.println("Nhap Id can edit");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap ten moi hoac nhap 'skip' neu khong thay doi");
        String name = scanner.nextLine();
        System.out.println("Nhap loai hinh lam viec moi hoac nhap 'skip' neu khong thay doi");
        String workingType = scanner.nextLine();
        if (!name.equalsIgnoreCase("skip")){
            if (!workingType.equalsIgnoreCase("skip")){
                staffController.editStaffById(id,name,workingType);
            }else {
                for (int i = 0; i < staffList.size(); i++) {
                    if (id==staffList.get(i).getId()){
                        workingType = staffList.get(i).getWorkingType();
                    }
                }
                staffController.editStaffById(id,name,workingType);
            }
        }else {
            if (!workingType.equalsIgnoreCase("skip")){
                for (int i = 0; i < staffList.size(); i++) {
                    if (id==staffList.get(i).getId()){
                        name = staffList.get(i).getName();
                    }
                }
                staffController.editStaffById(id,name,workingType);
            }
        }
        new Main();
    }
    public void changeStatusById(){
        System.out.println("Nhap Id can edit");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap vao trang thai lam viec (Dang lam viec/Nghi viec)");
        String str = scanner.nextLine();
        boolean status = false;
        if (str.equalsIgnoreCase("Dang lam viec")){
            status = true;
        }
        if (str.equalsIgnoreCase("Nghi viec")){
            status = false;
        }
        staffController.changeStatusById(id,status);
        new Main();
    }
}
