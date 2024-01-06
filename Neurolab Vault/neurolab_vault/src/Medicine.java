import java.sql.*;
import java.util.*;

public class Medicine extends Main {

    private String name;
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static Statement statement = null;
    Scanner assignment = new Scanner(System.in);

    //Database connection details
    private static  final String USERNAME = "root";
    private static final String PASSWORD = "GxCars24#Ssf2";

    private static final String URL = "jdbc:mysql://localhost:3306/neurlab_vault";
    //Medicine attributes
    private String nafdacNumber;
    private String manufacturer;
    private String dosageForm;
    private int quantity;
    private String supplier;
    private String purchaseDate;
    private String expirationDate;
    private double cost;
    private String category;
    private int quantityUsed;

    private double finalCost;

    private int minAmount;
    private int replenishAmount;




    //Constructor
    Medicine() {
        this.nafdacNumber = nafdacNumber;
        this.name = name;
        this.manufacturer = manufacturer;
        this.dosageForm = dosageForm;
        this.quantity = quantity;
        this.supplier = supplier;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
    }
    @Override
    public void welcomeUser()
    {
        System.out.println("Medicine section");
        System.out.println("What would you like to work with? ");
        System.out.println("1. Add medicine");
        System.out.println("2. Display medicine information");
        System.out.println("3. Update medicine information");
        System.out.println("4. Delete medicine information");
        System.out.println("5. Input restock details");
        System.out.println("6. Back");
        System.out.print("Enter an option: ");

        int medicineChoice = assignment.nextInt();

        switch (medicineChoice)
        {
            case 1:
                addMedicine();
                break;
            case 2:
                displayMedicine();
                break;
            case 3:
                updateMedicine();
                break;
            case 4:
                deleteMedicine();
                break;
            case 5:
                restockMedicine();
                break;
            case 6:
                main.welcomeUser();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    // Method to add a new medicine to the database
    public void addMedicine() {

        System.out.println("1. Proceed");
        System.out.println("2. Back");
        System.out.print("Enter a choice: ");
        int proceedChoice = assignment.nextInt();

        switch (proceedChoice)
        {
            case 1:
                System.out.println("NOTE: Respones should include no spacing");

                System.out.print("Enter amount of medicine to enter the inventory: ");
                int medAmount = assignment.nextInt();

                try {
                    connection = DriverManager.getConnection(URL, USERNAME , PASSWORD);

                    for (int i = 0; i < medAmount; i++) {
                        String sqlQuery = "INSERT INTO Medicine (NAFDACRegNo, MedicineName, MedicineManufacturer, Supplier, DosageForm, PurchaseDate, ExpirationDate, Cost, Quantity, Category)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(sqlQuery);

                        System.out.println("Enter NAFDAC Reg. No.: ");
                        nafdacNumber = assignment.next();
                        preparedStatement.setString(1, nafdacNumber);

                        System.out.println("Enter Medicine Name: ");
                        name = assignment.next();
                        preparedStatement.setString(2, name);

                        System.out.println("Enter Manufacturer Name: ");
                        manufacturer = assignment.next();
                        preparedStatement.setString(3, manufacturer);

                        System.out.println("Enter Supplier name: ");
                        supplier = assignment.next();
                        preparedStatement.setString(4, supplier);

                        System.out.println("Enter Dosage Form: ");
                        dosageForm = assignment.next();
                        preparedStatement.setString(5, dosageForm);

                        System.out.println("Enter Purchase Date: ");
                        purchaseDate = assignment.next();
                        preparedStatement.setString(6, purchaseDate);

                        System.out.println("Enter Expiration Date: ");
                        expirationDate = assignment.next();
                        preparedStatement.setString(7, expirationDate);

                        System.out.println("Enter Cost per unit of Medicine: ");
                        cost = assignment.nextInt();
                        preparedStatement.setDouble(8, cost);

                        System.out.println("Enter Quantity of Medicine: ");
                        quantity = assignment.nextInt();
                        preparedStatement.setInt(9, quantity);

                        finalCost = cost * quantity;
                        System.out.println("Total amount spent on Medicine = " + finalCost);

                        System.out.println("Choose Category of Medicine");
                        System.out.println("1. Antibiotics");
                        System.out.println("2. Antipyretics");
                        System.out.println("3. Antimalaria");
                        System.out.println("4. Antiseptics");
                        System.out.println("5. Analgesics");
                        System.out.println("6. Mood Stabilizers");
                        System.out.println("7. Hormone Replacements");
                        System.out.println("8. Oral Contraceptives");
                        System.out.println("9. Stimulants");
                        System.out.println("10. Tranquilizers");
                        System.out.println("11. Stains");
                        System.out.print("Enter an option: ");

                        int categoryChoice = assignment.nextInt();

                        switch (categoryChoice)
                        {
                            case 1:
                                preparedStatement.setString(10, "Antibiotics");
                                break;
                            case 2:
                                preparedStatement.setString(10, "Antipyretics");
                                break;
                            case 3:
                                preparedStatement.setString(10, "Antimalaria");
                                break;
                            case 4:
                                preparedStatement.setString(10, "Antiseptics");
                                break;
                            case 5:
                                preparedStatement.setString(10, "Analgesics");
                                break;
                            case 6:
                                preparedStatement.setString(10, "Mood Stabilizers");
                                break;
                            case 7:
                                preparedStatement.setString(10, "Hormone Replacements");
                                break;
                            case 8:
                                preparedStatement.setString(10, "Oral Contraceptives");
                                break;
                            case 9:
                                preparedStatement.setString(10, "Stimulants");
                                break;
                            case 10:
                                preparedStatement.setString(10, "Tranquilizers");
                                break;
                            case 11:
                                preparedStatement.setString(10, "Statins");
                                break;
                        }


                        int status = preparedStatement.executeUpdate();
                        if(status < 0)
                        {
                            System.out.println("Record is inserted successfully");
                        }
                        else
                        {
                            System.out.println("No records found");
                        }
                    }
                }
                catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                inventoryMedicine.welcomeUser();
                break;
        }

    }

    public void displayMedicine()
    {
        try
        {
            System.out.print("Enter NAFDAC Reg. No. of Medicine you wish to see: ");
            String NAFDACchoice = assignment.next();

            String sqlQuery = "select * from medicine where NAFDACRegNo = '" + NAFDACchoice + "'";

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next())
            {
                String NAFDACNo = resultSet.getString("NAFDACRegNo");
                String MedicineName = resultSet.getString("MedicineName");
                String MedicineManufacturer = resultSet.getString("MedicineManufacturer");
                String Supplier = resultSet.getString("Supplier");
                String DosageForm = resultSet.getString("DosageForm");
                String PurchaseDate = resultSet.getString("PurchaseDate");
                String ExpirationDate = resultSet.getString("ExpirationDate");
                int Cost = resultSet.getInt("Cost");
                int Quantity = resultSet.getInt("Quantity");

                System.out.println("The NAFDAC No. of " + MedicineName + " is " + NAFDACNo);
                System.out.println("Manufacturer: " + MedicineManufacturer);
                System.out.println("Supplier: " + Supplier);
                System.out.println("Dosage Form: " + DosageForm);
                System.out.println("Purchase Date: " + PurchaseDate);
                System.out.println("Expiration Date: " + ExpirationDate);
                System.out.println("Total cost of Medicine: " + Cost);
                System.out.println("Quantity of " + MedicineName + " purchased: " + Quantity + "pcs");
                System.out.println("\n");


                System.out.println("Remaining qunatity of medicine: ");

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
    }

    public void updateMedicine()
    {
        try
        {
            System.out.print("Enter the NAFDAC Reg. No. of the medicine information to be updated: ");
            String NAFDACchoice = assignment.next();

            System.out.println("What do you want to update?");
            System.out.println("1. NAFDAC Reg. No.");
            System.out.println("2. Medicine Name");
            System.out.println("3. Medicine Manufacturer");
            System.out.println("4. Supplier");
            System.out.println("5. Dosage Form");
            System.out.println("6. Purchase Date");
            System.out.println("7. Expiration Date");
            System.out.println("8. Cost per unit");
            System.out.println("9. Quantity purchased");
            System.out.println("10. Category");
            System.out.println("11. Amount of medicine used");
            System.out.print("Enter an option: ");

            int updateChoice = assignment.nextInt();

            String sqluery = "update medicine set ";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sqluery);

            //update
            switch (updateChoice)
            {
                case 1:
                    System.out.print("Enter new NAFDAC Reg. No: ");
                    String newNAFDAC = assignment.next();
                    sqluery = sqluery + "NAFDACRegNo = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newNAFDAC);

                    int rows = preparedStatement.executeUpdate();
                    if (rows > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    System.out.println("New NAFDAC Reg. No. is: " + newNAFDAC);
                    System.out.println("Do you wish to view medicine information or go back? ");
                    System.out.println("1. View Medicine Information");
                    System.out.println("2. Back");
                    System.out.print("Enter an option: ");

                    int choice = assignment.nextInt();
                    switch (choice)
                    {
                        case 1:
                            displayMedicine();
                            break;
                        case 2:
                            welcomeUser();
                            break;
                    }
                    break;

                case 2:
                    System.out.print("Enter new Medicine Name: ");
                    String newMedicineName = assignment.next();
                    sqluery = sqluery + "MedicineName = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newMedicineName);

                    int rows1 = preparedStatement.executeUpdate();
                    if (rows1 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    System.out.println("New medicine name is: " + newMedicineName);
                    System.out.println("Do you wish to view medicine information or go back? ");
                    System.out.println("1. View Medicine Information");
                    System.out.println("2. Back");
                    System.out.print("Enter an option: ");

                    int choice2 = assignment.nextInt();
                    switch (choice2)
                    {
                        case 1:
                            displayMedicine();
                            break;
                        case 2:
                            welcomeUser();
                            break;
                    }

                    break;

                case 3:
                    System.out.print("Enter new manufacturer Name: ");
                    String newManufacturerName = assignment.next();
                    sqluery = sqluery + "MedicineManufacturer = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newManufacturerName);


                    int rows2 = preparedStatement.executeUpdate();
                    if (rows2 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 4:
                    System.out.print("Enter new supplier Name: ");
                    String newSupplier = assignment.next();
                    sqluery = sqluery + "Supplier = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newSupplier);

                    int rows3= preparedStatement.executeUpdate();
                    if (rows3 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 5:
                    System.out.print("Enter new dosage Form: ");
                    String newDosageForm = assignment.next();
                    sqluery = sqluery + "DosageForm = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newDosageForm);

                    int rows4= preparedStatement.executeUpdate();
                    if (rows4 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 6:
                    System.out.print("Enter new purchase Date: ");
                    String newPurchaseDate = assignment.next();
                    sqluery = sqluery + "PurchaseDate = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newPurchaseDate);

                    int rows5= preparedStatement.executeUpdate();
                    if (rows5 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 7:
                    System.out.print("Enter new expiration Date: ");
                    String newExpirationDate = assignment.next();
                    sqluery = sqluery + "ExpirationDate = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setString(1, newExpirationDate);

                    int rows6= preparedStatement.executeUpdate();
                    if (rows6 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 8:
                    System.out.print("Enter new cost: ");
                    int newCost = assignment.nextInt();
                    sqluery = sqluery + "Cost = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setInt(1, newCost);

                    int rows7= preparedStatement.executeUpdate();
                    if (rows7 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 9:
                    System.out.print("Enter new quantity: ");
                    int newQuantity = assignment.nextInt();
                    sqluery = sqluery + "Quantity = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);
                    preparedStatement.setInt(1, newQuantity);

                    int rows8= preparedStatement.executeUpdate();
                    if (rows8 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 10:
                    System.out.print("Choose new category: ");
                    System.out.println("1. Antibiotics");
                    System.out.println("2. Antipyretics");
                    System.out.println("3. Antimalaria");
                    System.out.println("4. Antiseptics");
                    System.out.println("5. Analgesics");
                    System.out.println("6. Mood Stabilizers");
                    System.out.println("7. Hormone Replacements");
                    System.out.println("8. Oral Contraceptives");
                    System.out.println("9. Stimulants");
                    System.out.println("10. Tranquilizers");
                    System.out.println("11. Stains");
                    System.out.print("Enter an option: ");

                    int newCategory = assignment.nextInt();
                    sqluery = sqluery + "Quantity = ? where NAFDACRegNo = '" + NAFDACchoice + "'";
                    preparedStatement = connection.prepareStatement(sqluery);

                    switch (newCategory)
                    {
                        case 1:
                            preparedStatement.setString(10, "Antibiotics");
                            break;
                        case 2:
                            preparedStatement.setString(10, "Antipyretics");
                            break;
                        case 3:
                            preparedStatement.setString(10, "Antimalaria");
                            break;
                        case 4:
                            preparedStatement.setString(10, "Antiseptics");
                            break;
                        case 5:
                            preparedStatement.setString(10, "Analgesics");
                            break;
                        case 6:
                            preparedStatement.setString(10, "Mood Stabilizers");
                            break;
                        case 7:
                            preparedStatement.setString(10, "Hormone Replacements");
                            break;
                        case 8:
                            preparedStatement.setString(10, "Oral Contraceptives");
                            break;
                        case 9:
                            preparedStatement.setString(10, "Stimulants");
                            break;
                        case 10:
                            preparedStatement.setString(10, "Tranquilizers");
                            break;
                        case 11:
                            preparedStatement.setString(10, "Statins");
                            break;
                    }

                    int rows9= preparedStatement.executeUpdate();
                    if (rows9 > 0)
                    {
                        System.out.println("Record updated successfully!");
                    }
                    else
                    {
                        System.out.println("No records found");
                    }
                    break;

                case 11:
                    System.out.print("What amount of medicine did you take out of storage? ");
                    int quantityRemoved = assignment.nextInt();

                    quantityUsed = quantity - quantityRemoved;
            }

//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public void deleteMedicine()
    {
        try
        {
            System.out.print("Enter the NAFDAC Reg. No. of the medicine information to be deleted: ");
            String NAFDACchoice = assignment.next();

            String sqlQuery = "delete from medicine where NAFDACRegNo = '" + NAFDACchoice + "'";

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            int rows = statement.executeUpdate(sqlQuery);
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

            int choice = assignment.nextInt();
            switch (choice)
            {
                case 1:
                    displayMedicine();
                    break;
                case 2:
                    welcomeUser();
                    break;
            }


        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    public void restockMedicine()
    {
        System.out.println("Enter the minimum quantity of medicine that should be in stock before purchase order is automatically sent out: ");
        minAmount = assignment.nextInt();
        System.out.println("Enter replenish quantity: ");
        replenishAmount = assignment.nextInt();
        System.out.println("Details have been successfully saved!");
    }
}
