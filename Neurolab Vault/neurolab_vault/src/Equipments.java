import java.util.HashMap;
import java.util.Scanner;

public class Equipments extends Main{
    Scanner userInput = new Scanner(System.in);
    HashMap<String, Integer> inventoryInfo = new HashMap<>();

    public void addToInventory()
    {
        System.out.print("Enter number of equipments you would wish to add to the inventory: ");
        int counter = userInput.nextInt();
        for (int i = 0; i < counter; i++) {
            System.out.print("Enter the name of the equipment: ");
            String inventoryInput = userInput.next();
            System.out.print("Enter the entry date in the format dmmyy - 3112006: ");
            int inventoryInputDate1 = userInput.nextInt();
            System.out.print("Enter the expiry date in the format dmmyy - 3112006: ");
            int inventoryInputDate2 = userInput.nextInt();
            System.out.print("Enter the NAFDAC Reg. No.: ");
            int NAFDACInput = userInput.nextInt();
            inventoryInfo.put(inventoryInput, inventoryInputDate1);
        }

    }

    public void displayInventory()
    {
        System.out.println(inventoryInfo);
    }
    public void displayOptions()
    {
        System.out.println("You are in the equipment section");
        System.out.println("What would you like to work with?");
        System.out.println("1. Consumables and accessories");
        System.out.println("2. Spare Parts");
        System.out.println("3. Medical Equipments");
        System.out.println("4. Testing Equipments");
        System.out.println("5. Home");

        int equipmentSelection = userInput.nextInt();

        switch (equipmentSelection)
        {
            //Working on Consumables and accessories
            case 1:
                System.out.println("Would you like to: ");
                System.out.println("1. Add new equipment");
                System.out.println("2. Update Inventory");
                System.out.println("3. Look into inventory");
                System.out.println("4. Back");

                int consumableSelection = userInput.nextInt();

                switch (consumableSelection)
                {
                    case 1:
                        addToInventory();
                        System.out.print("\n");
                        System.out.println("Would you like to: ");
                        System.out.println("1. Look into inventory");
                        System.out.println("2. Back");

                        int addToInventorySelection = userInput.nextInt();

                        switch (addToInventorySelection)
                        {
                            case 1:
                                displayInventory();
                                System.out.println("\n");
                                displayOptions();
                                break;
                            case 2:
                                displayOptions();
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("There's nothing yet");
                        break;
                    case 3:
                        displayInventory();

                        break;
                    case 4:
                        displayOptions();
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
                break;
            case 2:

                //working on Spare Parts
                System.out.println("Would you like to...");
                System.out.println("1. Add new equipment");
                System.out.println("2. Update Inventory");
                System.out.println("3. Look into inventory");
                break;
            case 3:

                //working on Medical Equipments
                System.out.println("Would you like to...");
                System.out.println("1. Add new equipment");
                System.out.println("2. Update Inventory");
                System.out.println("3. Look into inventory");
                break;
            case 4:

                //working on Testing Equipments
                System.out.println("Would you like to...");
                System.out.println("1.Add new equipment");
                System.out.println("2. Update Inventory");
                System.out.println("3. Look into inventory");
                break;
            case 5:

                //Redirecting back to  main page
                welcomeUser();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
}

