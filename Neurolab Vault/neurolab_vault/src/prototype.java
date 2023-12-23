import java.util.ArrayList;
import java.util.Scanner;

public class prototype{
    public static void main(String[] args) {
        //initializing the scaner object
        Scanner input = new Scanner(System.in);

        //taking input from the user
        System.out.println("What section do you want to work with today?");
        System.out.println("1. Equipments");
        System.out.println("2. Medicine");
        System.out.println("\n");
        System.out.println(".........");
        int sectionNumber = input.nextInt();



        switch (sectionNumber)
        {
            case 1:
                System.out.println("You are in the equipment section");
                System.out.println("What would you like to work with?");
                break;

            case 2:
                System.out.println("You are in the medicine section");
                System.out.println("What would you like to work with?");
                break;
        }


    }
}
