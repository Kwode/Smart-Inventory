import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    static Scanner input = new Scanner(System.in);

    //initializing the equipments class
    public static Equipments inventoryEquipment = new Equipments();

    //initializing the medicine class
    public static Medicine inventoryMedicine = new Medicine();

    public static void welcomeUser()
    {
        System.out.println("NEUROLAB VAULT");
        System.out.println("What section do you want to work with today?");
        System.out.println("1. Equipments");
        System.out.println("2. Medicine");
        System.out.print("\n");

        int sectionNumber = input.nextInt();

        //using switch-case to choose sections
        switch (sectionNumber)
        {
            case 1:
                inventoryEquipment.displayOptions();
                break;
            case 2:
                System.out.println("You are in the medicine section");
                System.out.println("What would you like to work with?");
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

    }

    public static void main(String[] args) {
        welcomeUser();
    }
}
