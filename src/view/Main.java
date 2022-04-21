package view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    Scanner scanner = new Scanner(System.in);
    public Main() {
        System.out.println("1. Dang ky");
        System.out.println("2. Dang nhap");
//        System.out.println("3. Show role");
        String make;
        boolean checkMake;
        while (true){
            make = scanner.nextLine();
            checkMake = Pattern.matches("[12]",make);
            if (checkMake){
                break;
            }else {
                new Main();
            }
        }
        switch (make) {
            case "1":
                new RegisterView();
                break;
            case "2":
                new LoginView();
                break;
//            case 3:
//                new RoleView().showListRole();
//                break;
        }
    }

    public static void main(String[] args) {
        new Main();

    }
}
