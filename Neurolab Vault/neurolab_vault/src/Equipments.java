import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Equipments extends Main {
    Scanner userInput = new Scanner(System.in);
    private static Connection connection1 = null;
    private static PreparedStatement preparedStatement1 = null;
    private static Statement statement1 = null;

    //Database connection details
    private static final String USERNAME1 = "root";
    private static final String PASSWORD1 = "GxCars24#Ssf2";

    private static final String URL1 = "jdbc:mysql://localhost:3306/neurlab_vault";
    //Equipments attributes
    private String equipmentName;
    private String equipmentNafdacNumber;
    private String equipmentManufacturer;
    private int equipmentQuantity;
    private String equipmentSupplier;
    private String equipmentPurchaseDate;
    private String equipmentExpirationDate;
    private double equipmentCost;
    private int QuantitySpareParts;

    private double finalCost;

    @Override
    public void welcomeUser()
    {
        System.out.println("Medicine section");
        System.out.println("What would you like to work with? ");
        System.out.println("1. Add equipment");
        System.out.println("2. Display equipment information");
        System.out.println("3. Update equipment information");
        System.out.println("4. Delete equipment information");
        System.out.println("5. Input restock details");
        System.out.println("6. Back");
        System.out.print("Enter an option: ");

        int equipmentChoice = userInput.nextInt();

        switch (equipmentChoice)
        {
            case 1:
                addEquipment();
                break;
            case 2:
                displayEquipment();
                break;
            case 3:
                updateEquipment();
                break;
            case 4:
                deleteEquipment();
                break;
            case 5:
                restockEquipment();
                break;
            case 6:
                main.welcomeUser();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }


    public void addEquipment() {
        System.out.println("1. Proceed");
        System.out.println("2. Back");
        System.out.print("Enter a choice: ");
        int proceedChoice = userInput.nextInt();

        switch (proceedChoice)
        {
            case 1:
                System.out.print("Enter number of equipments you would wish to add to the inventory: ");
                int counter = userInput.nextInt();

                try {
                    connection1 = DriverManager.getConnection(URL1, USERNAME1, PASSWORD1);

                    for (int i = 0; i < counter; i++) {
                        String sqlQuery = "INSERT INTO equipments (NAFDACRegNo, EquipmentName, EquipmentManufacturer, Supplier, PurchaseDate, ExpirationDate, Cost, Quantity, QuantitySpareParts, Category)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        preparedStatement1 = connection1.prepareStatement(sqlQuery);

                        System.out.print("Enter NAFDAC Reg. No: ");
                        equipmentNafdacNumber = userInput.next();
                        preparedStatement1.setString(1, equipmentNafdacNumber);

                        System.out.print("Enter Equipment Name: ");
                        equipmentName = userInput.next();
                        preparedStatement1.setString(2, equipmentName);

                        System.out.print("Enter Manufacturer Name: ");
                        equipmentManufacturer = userInput.next();
                        preparedStatement1.setString(3, equipmentManufacturer);

                        System.out.print("Enter Supplier name: ");
                        equipmentSupplier = userInput.next();
                        preparedStatement1.setString(4, equipmentSupplier);

                        System.out.print("Enter Purchase Date: ");
                        equipmentPurchaseDate = userInput.next();
                        preparedStatement1.setString(5, equipmentPurchaseDate);

                        System.out.print("Enter Expiration Date: ");
                        equipmentExpirationDate = userInput.next();
                        preparedStatement1.setString(6, equipmentExpirationDate);

                        System.out.print("Enter Cost per unit of Equipment: ");
                        equipmentCost = userInput.nextInt();
                        preparedStatement1.setDouble(7, equipmentCost);

                        System.out.print("Enter Quantity of Equipment: ");
                        equipmentQuantity = userInput.nextInt();
                        preparedStatement1.setInt(8, equipmentQuantity);

                        System.out.print("Enter Quantity of Equipment's Spare Parts: ");
                        QuantitySpareParts = userInput.nextInt();
                        preparedStatement1.setInt(9, QuantitySpareParts);

                        finalCost = equipmentCost * equipmentQuantity;
                        System.out.println("Total amount spent on Equipments = " + finalCost);

                        System.out.println("\n");
                        System.out.println("Choose Category of Medicine");
                        System.out.println("1. Consumables and accessories");
                        System.out.println("2. Spare Parts");
                        System.out.println("3. Medical Equipments");
                        System.out.println("4. Testing Equipments");

                        int categoryChoice = userInput.nextInt();

                        switch (categoryChoice) {
                            case 1:
                                preparedStatement1.setString(10, "Consumables and accessories");
                                break;
                            case 2:
                                preparedStatement1.setString(10, "Spare Parts");
                                break;
                            case 3:
                                preparedStatement1.setString(10, "Medical Equipments");
                                break;
                            case 4:
                                preparedStatement1.setString(10, "Testing Equipments");
                                break;
                        }

                        int status = preparedStatement1.executeUpdate();
                        if(status < 0)
                        {
                            System.out.println("Record is inserted successfully");
                        }
                    }

                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                inventoryEquipment.welcomeUser();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

    }

    public void displayEquipment()
    {
        System.out.println("1. Proceed");
        System.out.println("2. Back");
        System.out.print("Enter a choice: ");
        int proceedChoice1 = userInput.nextInt();

        switch (proceedChoice1)
        {
            case 1:
                try
                {
                    System.out.print("Enter NAFDAC Reg. No. of Medicine you wish to see: ");
                    String NAFDACchoice = userInput.next();

                    String sqlQuery = "select * from equipments where NAFDACRegNo = '" + NAFDACchoice + "'";

                    connection1 = DriverManager.getConnection(URL1, USERNAME1, PASSWORD1);
                    statement1 = connection1.createStatement();
                    ResultSet resultSet = statement1.executeQuery(sqlQuery);

                    if (resultSet.next())
                    {
                        String NAFDACNo = resultSet.getString("NAFDACRegNo");
                        String EquipmentName = resultSet.getString("EquipmentName");
                        String EquipmentManufacturer = resultSet.getString("EquipmentManufacturer");
                        String Supplier = resultSet.getString("Supplier");
                        String PurchaseDate = resultSet.getString("PurchaseDate");
                        String ExpirationDate = resultSet.getString("ExpirationDate");
                        int Cost = resultSet.getInt("Cost");
                        int Quantity = resultSet.getInt("Quantity");
                        int QuantitySpareParts = resultSet.getInt("QuantitySpareParts");

                        System.out.println("The NAFDAC No. of " + EquipmentName + " is " + NAFDACNo);
                        System.out.println("Manufacturer: " + EquipmentManufacturer);
                        System.out.println("Supplier: " + Supplier);
                        System.out.println("Purchase Date: " + PurchaseDate);
                        System.out.println("Expiration Date: " + ExpirationDate);
                        System.out.println("Total cost of Medicine: " + Cost);
                        System.out.println("Quantity of " + EquipmentName + " purchased: " + Quantity + "pcs");
                        System.out.println("Quantity of spare parts for " + EquipmentName + " purchased: " + QuantitySpareParts + "pcs");
                        System.out.println("\n");

                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e);
                }
                break;
            case 2:
                inventoryEquipment.welcomeUser();
                break;
        }

    }

    public void updateEquipment()
    {
        System.out.println("1. Proceed");
        System.out.println("2. Back");
        System.out.print("Enter a choice: ");
        int proceedChoice2 = userInput.nextInt();

        switch (proceedChoice2)
        {
            case 1:
                try
                {
                    System.out.print("Enter the NAFDAC Reg. No. of the medicine information to be updated: ");
                    String NAFDACchoice = userInput.next();

                    System.out.println("What do you want to update?");
                    System.out.println("1. NAFDAC Reg. No.");
                    System.out.println("2. Equipment Name");
                    System.out.println("3. Equipment Manufacturer");
                    System.out.println("4. Supplier");
                    System.out.println("5. Purchase Date");
                    System.out.println("6. Expiration Date");
                    System.out.println("7. Cost per unit of equipment");
                    System.out.println("8. Quantity purchased");
                    System.out.println("9. Quantity of spare parts purchased");
                    System.out.println("10. Amount of medicine used");
                    System.out.print("Enter an option: ");

                    int updateChoice = userInput.nextInt();

                    String sqluery = "update medicine set ";
                    connection1 = DriverManager.getConnection(URL1, USERNAME1, PASSWORD1);
                    preparedStatement1 = connection1.prepareStatement(sqluery);

                    //update
                    switch (updateChoice)
                    {
                        case 1:
                            System.out.print("Enter new NAFDAC Reg. No: ");
                            String newNAFDAC = userInput.next();
                            sqluery = sqluery + "NAFDACRegNo = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setString(1, newNAFDAC);

                            int rows = preparedStatement1.executeUpdate();
                            if (rows > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }
                            System.out.println("New NAFDAC Reg. No. is: " + newNAFDAC);
                            System.out.println("Do you wish to view equipment information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice = userInput.nextInt();
                            switch (choice)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;

                        case 2:
                            System.out.print("Enter new Medicine Name: ");
                            String newEquipmentName = userInput.next();
                            sqluery = sqluery + "EquipmentName = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setString(1, newEquipmentName);

                            int rows1 = preparedStatement1.executeUpdate();
                            if (rows1 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }
                            System.out.println("New equipment name is: " + newEquipmentName);
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice2 = userInput.nextInt();
                            switch (choice2)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }

                            break;

                        case 3:
                            System.out.print("Enter new manufacturer Name: ");
                            String newManufacturerName = userInput.next();
                            sqluery = sqluery + "EquipmentManufacturer = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setString(1, newManufacturerName);


                            int rows2 = preparedStatement1.executeUpdate();
                            if (rows2 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New manufacturer name is: " + newManufacturerName);
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice3 = userInput.nextInt();
                            switch (choice3)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;

                        case 4:
                            System.out.print("Enter new supplier Name: ");
                            String newSupplier = userInput.next();
                            sqluery = sqluery + "Supplier = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setString(1, newSupplier);

                            int rows3= preparedStatement1.executeUpdate();
                            if (rows3 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New supplier name is: " + newSupplier);
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice4 = userInput.nextInt();
                            switch (choice4)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;


                        case 5:
                            System.out.print("Enter new purchase Date: ");
                            String newPurchaseDate = userInput.next();
                            sqluery = sqluery + "PurchaseDate = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setString(1, newPurchaseDate);

                            int rows5= preparedStatement1.executeUpdate();
                            if (rows5 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New purchase date is: " + newPurchaseDate);
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice5 = userInput.nextInt();
                            switch (choice5)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;

                        case 6:
                            System.out.print("Enter new expiration Date: ");
                            String newExpirationDate = userInput.next();
                            sqluery = sqluery + "ExpirationDate = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setString(1, newExpirationDate);

                            int rows6= preparedStatement1.executeUpdate();
                            if (rows6 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New expiration date is: " + newExpirationDate);
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice6 = userInput.nextInt();
                            switch (choice6)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;

                        case 7:
                            System.out.print("Enter new cost: ");
                            int newCost = userInput.nextInt();
                            sqluery = sqluery + "Cost = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setInt(1, newCost);

                            int rows7= preparedStatement1.executeUpdate();
                            if (rows7 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New cost is: " + newCost);
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice7 = userInput.nextInt();
                            switch (choice7)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;

                        case 8:
                            System.out.print("Enter new quantity: ");
                            int newQuantity = userInput.nextInt();
                            sqluery = sqluery + "Quantity = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setInt(1, newQuantity);

                            int rows8= preparedStatement1.executeUpdate();
                            if (rows8 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New quantity is: " + newQuantity + " pcs");
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice8 = userInput.nextInt();
                            switch (choice8)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;

                        case 9:
                            System.out.print("Enter new quantity of spare parts: ");
                            int newQuantitySpare = userInput.nextInt();
                            sqluery = sqluery + "Quantity = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                            preparedStatement1 = connection1.prepareStatement(sqluery);
                            preparedStatement1.setInt(1, newQuantitySpare);

                            int rows9 = preparedStatement1.executeUpdate();
                            if (rows9 > 0)
                            {
                                System.out.println("Record updated successfully!");
                            }
                            else
                            {
                                System.out.println("No records found");
                            }

                            System.out.println("New spare parts quantity is: " + newQuantitySpare + " pcs");
                            System.out.println("Do you wish to view medicine information or go back? ");
                            System.out.println("1. View Equipment Information");
                            System.out.println("2. Back");
                            System.out.print("Enter an option: ");

                            int choice9 = userInput.nextInt();
                            switch (choice9)
                            {
                                case 1:
                                    displayEquipment();
                                    break;
                                case 2:
                                    inventoryEquipment.welcomeUser();
                                    break;
                            }
                            break;
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e);
                }
                break;
            case 2:
                inventoryEquipment.welcomeUser();
                break;
        }
    }

    public void deleteEquipment()
    {
        System.out.println("1. Proceed");
        System.out.println("2. Back");
        System.out.print("Enter a choice: ");
        int proceedChoice3 = userInput.nextInt();

        switch (proceedChoice3)
        {
            case 1:
                try
                {
                    System.out.print("Enter the NAFDAC Reg. No. of the equipment information to be deleted: ");
                    String NAFDACchoice = userInput.next();

                    String sqlQuery = "delete from equipments where NAFDACRegNo = '" + NAFDACchoice + "'";

                    connection1 = DriverManager.getConnection(URL1, USERNAME1, PASSWORD1);
                    statement1 = connection1.createStatement();

                    int rows = statement1.executeUpdate(sqlQuery);
                    if (rows > 0)
                    {
                        System.out.println("Record deleted successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }

                    System.out.println("\n");
                    System.out.println("Do you wish to view medicine information or go back? ");
                    System.out.println("1. View Medicine Information");
                    System.out.println("2. Back");
                    System.out.print("Enter an option: ");

                    int choice = userInput.nextInt();
                    switch (choice)
                    {
                        case 1:
                            displayEquipment();
                            break;
                        case 2:
                            inventoryEquipment.welcomeUser();
                            break;
                    }


                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
                break;
            case 2:
                inventoryEquipment.welcomeUser();
                break;
        }
    }

    public void restockEquipment()
    {
        System.out.println(" ");
    }
}
