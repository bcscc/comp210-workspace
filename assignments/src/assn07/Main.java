package assn07;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        String masterpass = "";
        while(!(passwordManager.checkMasterPassword(masterpass))) {
            System.out.println("Enter Master Password");
            masterpass = scanner.nextLine();
        }
        boolean exit = false;
        while(!exit) {
            String command = scanner.nextLine();
            if (command.equals("New password")) {
                String website = scanner.nextLine();
                String password = scanner.nextLine();
                passwordManager.put(website, password);
                System.out.println("New password added");
            } else if (command.equals("Get password")) {
                String website = scanner.nextLine();
                String password = passwordManager.get(website);
                if (password == null) {
                    System.out.println("Account does not exist");
                } else {
                    System.out.println(password);
                }
            } else if (command.equals("Delete account")) {
                String website = scanner.nextLine();
                String password = passwordManager.remove(website);
                if (password == null) {
                    System.out.println("Account does not exist");
                } else {
                    System.out.println("Account deleted");
                }
            } else if (command.equals("Check duplicate password")) {
                String password = scanner.nextLine();
                List<String> accounts = passwordManager.checkDuplicate(password);
                if (accounts.size() == 0) {
                    System.out.println("No account uses that password");
                } else {
                    System.out.println("Websites using that password:");
                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.println(accounts.get(i));
                    }
                }
            } else if (command.equals("Get accounts")) {
                Set<String> accounts = passwordManager.keySet();
                System.out.println("Your accounts:");
                for (String account : accounts) {
                    System.out.println(account);
                }
            } else if (command.equals("Generate random password")) {
                int length = scanner.nextInt();
                String password = passwordManager.generateRandomPassword(length);
                System.out.println(password);
                scanner.nextLine();
            } else if (command.equals("Exit")) {
                exit = true;
            } else {
                System.out.println("Command not found");
            }

        }
    }
}
