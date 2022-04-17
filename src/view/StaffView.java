package view;

import controller.StaffController;
import dto.SignUpDTO;
import model.Role;
import model.RoleName;
import model.Staff;
import service.Staff.StaffServiceIMPL;
import service.user.RoleServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class StaffView {
    Scanner scanner = new Scanner(System.in);
    List<Staff> staffList = StaffServiceIMPL.staffList;
    StaffController staffController = new StaffController();
    StaffServiceIMPL staffServiceIMPL = new StaffServiceIMPL();
    RoleServiceIMPL roleServiceIMPL = new RoleServiceIMPL();
    public void createStaff(){
        while (true){
            int id;
            if (staffServiceIMPL.staffList.size()==0){
                id = 1;
            }else {
                id = staffServiceIMPL.staffList.get(staffServiceIMPL.staffList.size()-1).getId()+1;
            }
            System.out.println("Nhap ten:");
            String name = scanner.nextLine();
            System.out.println("Nhap username:");
            String username;
            boolean checkUsername;
            while (true){
                username = scanner.nextLine();
                checkUsername = Pattern.matches("[a-z0-9_-]{6,}",username);
                if (!checkUsername){
                    System.err.println("Sai username, nhap lai");
                }else if (staffServiceIMPL.existedByUsername(username)){
                    System.err.println("Username da ton tai, nhap lai");
                }else {
                    break;
                }
            }
            System.out.println("Nhap password, ky tu dau la chu hoa, do dai 4 ky tu");
            String password;
            boolean checkPassword;
            while (true){
                password = scanner.nextLine();
                checkPassword = Pattern.matches("[A-Z]{1}[a-z0-9]{5,}",password);
                if (!checkPassword){
                    System.out.println("Nhap lai password");
                }else {
                    break;
                }
            }
            System.out.println("Nhap hinh thuc lam viec");
            String workingType = scanner.nextLine();
            System.out.println("Nhap vi tri lam viec");
            String role;
            boolean checkRole;
            while (true){
                role = scanner.nextLine();
                checkRole = Pattern.matches("staff|admin|manager",role);
                if (!checkRole){
                    System.out.println("vi tri lam viec sai");
                }else {
                    break;
                }
            }
            Staff staff = new Staff(id,name,username,password,true,workingType,checkRoleSet(role));
            staffController.addStaff(staff);
            System.out.println("Them nhan vien moi"+staffList.get(staffList.size()-1));
            System.out.println("Nhap ky tu bat ky de tiep tuc hoac nhap 'quit' de ve menu");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")){
                new Menu();
            }
        }

//        while (true){
//            int id;
//            if (staffList.size()==0){
//                id = 1;
//            }else {
//                id = staffList.get(staffList.size()-1).getId()+1;
//            }
//            System.out.println("Nhap ten nhan vien: ");
//            String name = scanner.nextLine();
//            System.out.println("Nhap hinh thuc lam viec: ");
//            String workingType = scanner.nextLine();
//            System.out.println("Nhap username");
//            String username = scanner.nextLine();
//            System.out.println("Nhap password");
//            String password = scanner.nextLine();
//            System.out.println("Nhap vi tri lam viec");
//            String role = scanner.nextLine();
//            Staff staff = new Staff(id,name,username,password,true,workingType,role);
//            staffController.addStaff(staff);

//            System.out.println("Nhap ky tu bat ky de tiep tuc hoac nhap 'quit' de ve menu");
//            String backMenu = scanner.nextLine();
//            if (backMenu.equalsIgnoreCase("quit")){
//                new Menu();
//            }
//        }
    }

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
                new Menu();
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
                new Menu();
                break;
            case 3:
                System.out.println("Nhap loai hinh lam viec: ");
                String workingType = scanner.nextLine();
                System.out.println(staffController.searchStaffByWorkingType(workingType));
                new Menu();
                break;
            case 4:
                System.out.println(staffController.showStaffList());
                new Menu();
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
        new Menu();
    }
    public void editStaffById(){
        Set<Role> roleSet = new HashSet<>();
        System.out.println("Nhap Id can edit");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap ten moi hoac nhap 'skip' neu khong thay doi");
        String name = scanner.nextLine();
        System.out.println("Nhap loai hinh lam viec moi hoac nhap 'skip' neu khong thay doi");
        String workingType = scanner.nextLine();
        System.out.println("Nhap vi tri lam viec moi");
        String newRole = scanner.nextLine();
//        staffController.editStaffById(id,name,workingType,checkRoleSet(newRole));
        if (!name.equalsIgnoreCase("skip")){
            if (!workingType.equalsIgnoreCase("skip")){
                if (!newRole.equalsIgnoreCase("skip")){
                    staffController.editStaffById(id,name,workingType,checkRoleSet(newRole));
                }else {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id==staffList.get(i).getId()){
                           roleSet = staffList.get(i).getRoleSet();
                        }
                    }
                    staffController.editStaffById(id,name,workingType,roleSet);
                }
            }else {
                if (!newRole.equalsIgnoreCase("skip")){
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id==staffList.get(i).getId()){
                            workingType = staffList.get(i).getWorkingType();
                        }
                    }
                    staffController.editStaffById(id,name,workingType,checkRoleSet(newRole));
                }else {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id==staffList.get(i).getId()){
                            workingType = staffList.get(i).getWorkingType();
                            roleSet = staffList.get(i).getRoleSet();
                        }
                    }
                    staffController.editStaffById(id,name,workingType,roleSet);
                }
            }
        }else {
            if (!workingType.equalsIgnoreCase("skip")){
                if (!newRole.equalsIgnoreCase("skip")){
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id==staffList.get(i).getId()){
                            name = staffList.get(i).getName();
                        }
                    }
                    staffController.editStaffById(id,name,workingType,checkRoleSet(newRole));
                }else {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id==staffList.get(i).getId()){
                            roleSet = staffList.get(i).getRoleSet();
                        }
                    }
                    staffController.editStaffById(id,name,workingType,roleSet);
                }
            }else{
                if (!newRole.equalsIgnoreCase("skip")){
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id==staffList.get(i).getId()){
                            workingType = staffList.get(i).getWorkingType();
                            name = staffList.get(i).getName();
                        }
                    }
                    staffController.editStaffById(id,name,workingType,checkRoleSet(newRole));
                }
            }
        }
        new Menu();
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
        new Menu();
    }
    public void showUserInfo(){
        System.out.println(LoginView.user);
        new Menu();
    }
    public Set<Role> checkRoleSet(String newRole){
        Set<String> stringSetRole = new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        stringSetRole.add(newRole);
        stringSetRole.forEach(role->{
            switch (role){
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
        return roleSet;
    }
}
