import java.util.Scanner;

public class Main{
    static Scanner input = new Scanner(System.in);

    //initializing the equipments class
    public static Equipments inventoryEquipment = new Equipments();

    //initializing the medicine class
    public static Medicine inventoryMedicine = new Medicine();

    static Main main = new Main();

    public void welcomeUser(){
        System.out.println("WELCOME TO NEUROLAB VAULT");
        System.out.println("What section do you want to work with today?");
        System.out.println("1. Equipments");
        System.out.println("2. Medicine");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");

        int sectionNumber = input.nextInt();

        //using switch-case to choose sections
        switch (sectionNumber)
        {
            case 1:
                inventoryEquipment.welcomeUser();
                break;
            case 2:
                inventoryMedicine.welcomeUser();
                break;
            case 3:
                System.out.println("Thank you for using NeuroLabVault!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

    }

    public static void main(String[] args){
        main.welcomeUser();
    }
}
