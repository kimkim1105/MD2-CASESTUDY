package view;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import controller.StaffController;
import dto.SignUpDTO;
import model.Role;
import model.RoleName;
import model.Staff;
import model.Validate;
import service.Staff.StaffServiceIMPL;
import service.user.RoleServiceIMPL;

import java.util.*;
import java.util.regex.Pattern;

public class StaffView {
    Scanner scanner = new Scanner(System.in);
    List<Staff> staffList = StaffServiceIMPL.staffList;
    StaffController staffController = new StaffController();
    StaffServiceIMPL staffServiceIMPL = new StaffServiceIMPL();
    RoleServiceIMPL roleServiceIMPL = new RoleServiceIMPL();
    List<Role> roleList = new ArrayList<>(LoginView.user.get(0).getRoleSet());

    public void operation() {
        System.out.println("Lua chon thao tac: ");
        System.out.println("1. Them nhan vien moi" +
                "\n2. Sua thong tin nhan vien" +
                "\n3. Thay doi trang thai nhan vien" +
                "\n4. Menu");
        String operate = scanner.nextLine();
            switch (operate) {
                case "1":
                    createStaff();
                    break;
                case "2":
                    editStaffById();
                    break;
                case "3":
                    if (Menu.checkLogin(roleList)==3){
                        changeStatusById();
                    }else {
                        System.out.println("Khong co quyen sua");
                        operation();
                    }
                    break;
                case "4":
                    new Menu();
                    break;
                default:
                    operation();
                    break;
        }
    }

    public void createStaff() {
        while (true) {
            int id;
            if (staffServiceIMPL.staffList.size() == 0) {
                id = 1;
            } else {
                id = staffServiceIMPL.staffList.get(staffServiceIMPL.staffList.size() - 1).getId() + 1;
            }
            System.out.println("Nhap ten:");
            String name = scanner.nextLine();
            System.out.println("Nhap username (chữ thường và số viết liền không dấu, ít nhất 6 ký tự::");
            String username;
            boolean checkUsername;
            while (true) {
                username = scanner.nextLine();
                checkUsername = Validate.isvalid(username, Validate.USERNAME_REGEX);
                if (!checkUsername) {
                    System.err.println("Sai dinh dang username, nhap lai");
                } else if (staffServiceIMPL.existedByUsername(username)) {
                    System.err.println("Username da ton tai, nhap lai");
                } else {
                    break;
                }
            }
            System.out.println("Nhap password, ky tu dau la chu hoa, do dai 6 ky tu");
            String password;
            boolean checkPassword;
            while (true) {
                password = scanner.nextLine();
                checkPassword = Validate.isvalid(password, Validate.PASSWORD);
                if (!checkPassword) {
                    System.out.println("Nhap lai password");
                } else {
                    break;
                }
            }
            System.out.println("Nhap hinh thuc lam viec (fulltime/parttime)");
            String workingType;
            boolean checkWorkingType;
            while (true) {
                workingType = scanner.nextLine();
                checkWorkingType = Validate.isvalid(workingType.toLowerCase(), Validate.WORKINGTYPE);
                if (checkWorkingType) {
                    break;
                } else {
                    System.out.println("Nhap lai hinh thuc lam viec");
                }
            }
            System.out.println("Nhap vi tri lam viec(admin/staff/manager)");
            String role;
            boolean checkRole;
            while (true) {
                role = scanner.nextLine();
                checkRole = Validate.isvalid(role.toLowerCase(), Validate.ROLE);
                if (!checkRole) {
                    System.out.println("vi tri lam viec sai, nhap lai");
                } else {
                    break;
                }
            }
            Staff staff = new Staff(id, name, username, password, true, workingType, checkRoleSet(role));
            staffController.addStaff(staff);
            System.out.println("Them nhan vien moi" + staffList.get(staffList.size() - 1));
            System.out.println("Nhap ky tu bat ky de tiep tuc hoac nhap 'quit' de thoat");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                operation();
            }
        }
    }

    public void searchStaff() {
        System.out.println("1. Tim theo ten");
        System.out.println("2. Tim theo trang thai");
        System.out.println("3. Tim theo loai hinh lam viec");
        System.out.println("4. Hien thi tat ca nhan vien");
        System.out.println("5. Tro ve thao tac");
        String choose = scanner.nextLine();
        switch (choose) {
            case "1":
                System.out.println("Nhap ten can tim kiem: ");
                String name = scanner.nextLine();
                if (staffController.searchStaffByName(name).size() == 0) {
                    System.out.println("Ten nhan vien khong ton tai");
                } else {
                    System.out.println(staffController.searchStaffByName(name));
                }
                searchStaff();
                break;
            case "2":
                int choosestatus = 0;
                do {
                    System.out.println("1. Nhan vien dang lam viec");
                    System.out.println("2. Nhan vien da nghi viec");
                    choosestatus = Integer.parseInt(scanner.nextLine());
                    switch (choosestatus) {
                        case 1:
                            System.out.println(staffController.searchStaffWithStatus(true));
                            break;
                        case 2:
                            System.out.println(staffController.searchStaffWithStatus(false));
                            break;
                    }
                } while (choosestatus < 0 || choosestatus > 2);

                searchStaff();
                break;
            case "3":
                System.out.println("Nhap hinh thuc lam viec");
                String workingType;
                boolean checkWorkingType;
                while (true) {
                    workingType = scanner.nextLine();
                    checkWorkingType = Validate.isvalid(workingType, Validate.WORKINGTYPE);
                    if (checkWorkingType) {
                        break;
                    } else {
                        System.out.println("Nhap lai hinh thuc lam viec");
                    }
                }
                System.out.println(staffController.searchStaffByWorkingType(workingType));
                searchStaff();
                break;
            case "4":
                System.out.println(staffController.showStaffList());
                searchStaff();
                break;
            case "5":
                operation();
                break;
            default:
                searchStaff();
                break;
        }

    }

    public void searchStatus() {
        System.out.println("Nhap ten tim kiem: ");
        String name = scanner.nextLine();
        System.out.println(staffController.searchStatusByName(name));
        new Menu();
    }

    public void editStaffById() {
        Set<Role> roleSet = new HashSet<>();
        System.out.println("Nhap Id can edit");
        int id = Integer.parseInt(scanner.nextLine());
        if (id <= 0 || id > staffList.get(staffList.size() - 1).getId()) {
            System.out.println("Id khong ton tai");
            editStaffById();
        }
        for (int i = 0; i < staffList.size(); i++) {
            if (id == staffList.get(i).getId()) {
                if (staffList.get(i).isStatus() == false) {
                    System.out.println("Nhan vien da nghi viec");
                    editStaffById();
                }
            }
        }
        System.out.println("Nhap ten moi hoac nhap 'skip' neu khong thay doi");
        String name;
        boolean checkName;
        while (true) {
            name = scanner.nextLine();
            checkName = Pattern.matches("^.+", name);
            if (checkName) {
                break;
            } else {
                System.out.println("Ten khong duoc bo trong");
            }
        }
        System.out.println("Nhap loai hinh lam viec moi hoac nhap 'skip' neu khong thay doi");
        String workingType;
        boolean checkWorkingType;
        while (true) {
            workingType = scanner.nextLine();
            checkWorkingType = Pattern.matches("(fulltime|parttime|skip)", workingType);
            if (checkWorkingType) {
                break;
            } else {
                System.out.println("Nhap lai hinh thuc lam viec");
            }
        }
        System.out.println("Nhap vi tri lam viec moi");
        String newRole;
        boolean checkrole;
        while (true) {
            newRole = scanner.nextLine();
            checkrole = Pattern.matches("staff|admin|manager|skip", newRole);
            if (checkrole) {
                break;
            } else {
                System.out.println("Nhap lai vi tri lam viec");
            }
        }
//        staffController.editStaffById(id,name,workingType,checkRoleSet(newRole));
        if (!name.equalsIgnoreCase("skip")) {
            if (!workingType.equalsIgnoreCase("skip")) {
                if (!newRole.equalsIgnoreCase("skip")) {
                    staffController.editStaffById(id, name, workingType, checkRoleSet(newRole));
                } else {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id == staffList.get(i).getId()) {
                            roleSet = staffList.get(i).getRoleSet();
                        }
                    }
                    staffController.editStaffById(id, name, workingType, roleSet);
                }
            } else {
                if (!newRole.equalsIgnoreCase("skip")) {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id == staffList.get(i).getId()) {
                            workingType = staffList.get(i).getWorkingType();
                        }
                    }
                    staffController.editStaffById(id, name, workingType, checkRoleSet(newRole));
                } else {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id == staffList.get(i).getId()) {
                            workingType = staffList.get(i).getWorkingType();
                            roleSet = staffList.get(i).getRoleSet();
                        }
                    }
                    staffController.editStaffById(id, name, workingType, roleSet);
                }
            }
        } else {
            if (!workingType.equalsIgnoreCase("skip")) {
                if (!newRole.equalsIgnoreCase("skip")) {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id == staffList.get(i).getId()) {
                            name = staffList.get(i).getName();
                        }
                    }
                    staffController.editStaffById(id, name, workingType, checkRoleSet(newRole));
                } else {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id == staffList.get(i).getId()) {
                            roleSet = staffList.get(i).getRoleSet();
                        }
                    }
                    staffController.editStaffById(id, name, workingType, roleSet);
                }
            } else {
                if (!newRole.equalsIgnoreCase("skip")) {
                    for (int i = 0; i < staffList.size(); i++) {
                        if (id == staffList.get(i).getId()) {
                            workingType = staffList.get(i).getWorkingType();
                            name = staffList.get(i).getName();
                        }
                    }
                    staffController.editStaffById(id, name, workingType, checkRoleSet(newRole));
                }
            }
        }
        operation();
    }

    public void changeStatusById() {
        System.out.println("Nhap Id can edit");
        int id = Integer.parseInt(scanner.nextLine());
        if (id > staffList.get(staffList.size() - 1).getId() || id <= 0) {
            System.out.println("Id k ton tai");
            changeStatusById();
        }
        System.out.println("Nhap vao trang thai lam viec (Dang lam viec/Nghi viec)");
        String str;
        boolean checkStatus;
        while (true) {
            str = scanner.nextLine();
            checkStatus = Validate.isvalid(str, Validate.STATUS);
            if (checkStatus) {
                break;
            } else {
                System.out.println("Nhap lai trang thai lam viec");
            }
        }
        boolean status = false;
        if (str.equalsIgnoreCase("Dang lam viec")) {
            status = true;
        }
        if (str.equalsIgnoreCase("Nghi viec")) {
            status = false;
        }
        staffController.changeStatusById(id, status);
        operation();
    }

    public void showUserInfo() {
        System.out.println(LoginView.user);
        new Menu();
    }

    public int fillDayOfWork() {
        int workingDayInMonth = 0;
        System.out.println("Nhap so ngay lam viec");
        try {
            workingDayInMonth = Integer.parseInt(scanner.nextLine());
            if (workingDayInMonth > 0 && workingDayInMonth <= 27) {
                return workingDayInMonth;
            }
        } catch (NumberFormatException e) {
            System.out.println("so ngay lam viec >0 va <27");
            fillDayOfWork();
        }
        return fillDayOfWork();
    }
    public void changeDayOff(){
        System.out.println("Nhap vao id can sua");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (id <= 0 || id > staffList.get(staffList.size() - 1).getId()) {
                System.out.println("Id khong ton tai");
                changeDayOff();
            }
            for (int i = 0; i < staffList.size(); i++) {
                if (id == staffList.get(i).getId()) {
                    if (staffList.get(i).isStatus() == false) {
                        System.out.println("Nhan vien da nghi viec");
                        changeDayOff();
                    }
                }
            }
            System.out.println("Nhap so ngay nghi");
            int dayOff = Integer.parseInt(scanner.nextLine());
            staffController.changeDayOffById(id,dayOff);
        }catch (NumberFormatException e){

        }

    }

    public Map<Integer, Integer> fillDayOff() {
        Map<Integer, Integer> staffOff = new TreeMap<>();
        System.out.println("Nhap 'quit' de thoat hoac ky tu bat ky de dien so ngay nghi nhan vien neu co");
            String back = scanner.nextLine();
            if (back.equalsIgnoreCase("quit")) {
                payroll();
            }
        System.out.println("Nhap id nhan vien co ngay nghi");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                if (id <= 0 || id > staffList.get(staffList.size() - 1).getId()) {
                    System.out.println("Id khong ton tai");
                    fillDayOff();
                }
                for (int i = 0; i < staffList.size(); i++) {
                    if (id == staffList.get(i).getId()) {
                        if (staffList.get(i).isStatus() == false) {
                            System.out.println("Nhan vien da nghi viec");
                            fillDayOff();
                        }
                    }
                }
                System.out.println("Nhap so ngay nghi trong thang ");
                int dayoff = Integer.parseInt(scanner.nextLine());
                if (dayoff>0&&dayoff<=7){
                 staffOff.put(id, dayoff);
                 return staffOff;
                }else {
                    System.out.println("Thong tin nhap vao k dung, nhap lai hoac thoat");
                }
            }catch (NumberFormatException e){
                System.out.println("Thong tin nhap vao k dung, nhap lai hoac thoat");
                fillDayOff();
            }
        return fillDayOff();
    }

    public void showSalary(int workingDayInMonth, Map<Integer, Integer> staffOff) {
        staffController.takeSalary(workingDayInMonth, staffOff);
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).isStatus()) {
                System.out.println(staffList.get(i).printSalary());
            }
        }
    }

    public void payroll() {

        System.out.println("Chon thao tac");
        System.out.println("1. Tinh cong" +
                "\n2. Thay doi so ngay nghi cua nhan vien" +
                "\n3. Xem bang luong ca nhan" +
                "\n4. Xem toan bo bang luong" +
                "\n5. Menu");
        String choose;
        boolean checkChoose;
        while (true){
            choose = scanner.nextLine();
            checkChoose = Pattern.matches("[1-5]",choose);
            if (checkChoose){
                break;
            }else {
                payroll();
            }
        }
            switch (choose) {
                case "1":
                    if (Menu.checkLogin(roleList) >= 2) {
//                       int workingDayInMonth = fillDayOfWork();
//                        Map<Integer, Integer> staffOff = fillDayOff();
//                        staffServiceIMPL.payroll(workingDayInMonth,staffOff);
                        staffController.takeSalary(fillDayOfWork(),fillDayOff());
                    } else {
                        System.err.println("Khong co quyen truy cap");
                        payroll();
                    }
                    payroll();
                    break;
                case "2":
                    changeDayOff();
                    payroll();
                    break;
                case "3":
                    staffController.getSalaryById(LoginView.user.get(0).getId(), staffList);
                    payroll();
                    break;
                case "4":
                    if (Menu.checkLogin(roleList) == 3) {
                        for (int i = 0; i < staffList.size(); i++) {
                            System.out.println(staffList.get(i).printSalary());
                            payroll();
                        }
                    } else {
                        System.err.println("Khong co quyen truy cap");
                        payroll();
                    }
                    break;
                case "5":
                    new Menu();
                    break;
                default:
                    payroll();
                    break;
            }
    }

    public Set<Role> checkRoleSet(String newRole) {
        Set<String> stringSetRole = new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        stringSetRole.add(newRole);
        stringSetRole.forEach(role -> {
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
        return roleSet;
    }
}
