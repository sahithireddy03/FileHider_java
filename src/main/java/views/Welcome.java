package views;

import Model.User;
import dao.UserDAO;
import service.GenerateOtp;
import service.SendOtpService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Welcome {
    public void WelcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the app");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try{
                choice = Integer.parseInt(br.readLine());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        switch(choice){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }
//login function
    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExist(email)) {
                String genOtp = GenerateOtp.getOtp();
                SendOtpService.sendOTP(email, genOtp);
                System.out.println("Enter the OTP");
                String otp = sc.nextLine();
                if (otp.equals(genOtp)) {
                    new UserView(email).home();

                   // System.out.println("Welcome");
                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User not found");
            }
        }catch(SQLException ex){
                ex.printStackTrace();
            }
    }
    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        String name = sc.nextLine();
        System.out.println("Enter email");
        String email = sc.nextLine();
        String genOtp = GenerateOtp.getOtp();
        SendOtpService.sendOTP(email,genOtp);
        System.out.println("Enter the OTP");
        String otp = sc.nextLine();
        if(otp.equals(genOtp)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch(response){
                case 0 -> System.out.println("User registered");
                case 1 -> System.out.println("User already exists");
            }

            System.out.println("Welcome");
        }else{
            System.out.println("Wrong OTP");
        }

    }
}
