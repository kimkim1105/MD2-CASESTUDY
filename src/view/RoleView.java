package view;

import controller.RoleController;

import java.awt.*;
import java.util.Scanner;

public class RoleView {
    Scanner scanner = new Scanner(System.in);
    RoleController roleController = new RoleController();
    public void showListRole(){
        System.out.println(roleController.showListRole());
        System.out.println("quit de thoat");
        String backMenu = scanner.nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            new Main();
        }
    }
}
