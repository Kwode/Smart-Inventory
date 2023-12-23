import java.util.Scanner;

public class Equipments extends Main{
    Scanner userInput = new Scanner(System.in);
    public void getInfo()
    {
        System.out.println("You are in the equipment section");
        System.out.println("What would you like to work with?");
        System.out.println("1. Consumables and accessories");
        System.out.println("2. Spare Parts");
        System.out.println("3. Medical Equipments");
        System.out.println("4. Testing Equipments");

        int equipmentSelection = userInput.nextInt();

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

