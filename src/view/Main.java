package view;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    public Main() {
        System.out.println("1. Dang ky");
        System.out.println("2. Dang nhap");
        System.out.println("3. Show role");
        int make = Integer.parseInt(scanner.nextLine());
        switch (make) {
            case 1:
                new RegisterView();
                break;
            case 2:
                new LoginView();
                break;
            case 3:
                new RoleView().showListRole();
                break;
        }
    }

    public static void main(String[] args) {
        new Main();

    }
}
