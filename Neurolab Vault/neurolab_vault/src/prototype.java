import java.util.ArrayList;
import java.util.Scanner;

class Equipments
{
    private int equipmentSelection;
    void getInfo()
    {
        System.out.println("You are in the equipment section");
        System.out.println("What would you like to work with?");
        System.out.println("1. Consumables and accessories");
        System.out.println("2. Spare Parts");
        System.out.println("3. Medical Equipments");
        System.out.println("4. Testing Equipments");

        switch (equipmentSelection)
        {
            case 1:
                System.out.println("Would you like to...");
                System.out.println("1. Update Inventory");
                System.out.println("2. Look into inventory");
                break;
            case 2:
                System.out.println("Would you like to...");
                System.out.println("1. Update Inventory");
                System.out.println("2. Look into inventory");
                break;
            case 3:
                System.out.println("Would you like to...");
                System.out.println("1. Update Inventory");
                System.out.println("2. Look into inventory");
                break;
            case 4:
                System.out.println("Would you like to...");
                System.out.println("1. Update Inventory");
                System.out.println("2. Look into inventory");
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
}

public class prototype{
    public static void main(String[] args) {
        //initializing the scanner object
        Scanner input = new Scanner(System.in);

        //initializing the equipments class
        Equipments myEquipment = new Equipments();

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
                myEquipment.getInfo();
                break;
            case 2:
                System.out.println("You are in the medicine section");
                System.out.println("What would you like to work with?");
                break;
        }


    }
}
