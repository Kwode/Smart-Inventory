import java.util.ArrayList;
import java.util.Scanner;

public class prototype{
    //initializing the equipments class
    public static Equipments inventoryEquipment = new Equipments();

    //initializing the medicine class
    public static Medicine inventoryMedicine = new Medicine();

    public static void main(String[] args) {
        //initializing the scanner object
        Scanner input = new Scanner(System.in);

        //taking input from the user
        System.out.println("What section do you want to work with today?");
        System.out.println("1. Equipments");
        System.out.println("2. Medicine");
        System.out.println("\n");
        System.out.println(".........");
        int sectionNumber = input.nextInt();

        //using switch-case to choose sections
        switch (sectionNumber)
        {
            case 1:
                inventoryEquipment.getInfo();
                break;
            case 2:
                System.out.println("You are in the medicine section");
                System.out.println("What would you like to work with?");
                break;
        }


    }
}
