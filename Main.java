package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    double balance = 0;
    ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Main obj = new Main();
        Scanner scanner = new Scanner(System.in);
        obj.processCommandNumber(scanner);
    }

    public void printMenu() {
        System.out.print("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                0) Exit
                """);
    }

    public void savePurchasesIntoFile() {
        try (FileWriter writer = new FileWriter("purchases.txt")) {
            if (!products.isEmpty()) {
                writer.write(balance + "\n");
                for (Product p : products) {
                    writer.write(p.getName() + "," + p.getPrice() + "," + p.getCategory() + "\n");
                }
                System.out.println("Purchases were saved!");
            } else {
                System.out.println("None purchases to save.");
            }
        } catch (IOException e) {
            System.out.println("Could not be saved: " + e);
        }
        printLine();
    }

    public void loadPurchasesFromFile() {
        try (Scanner scanner = new Scanner(new FileReader("purchases.txt"))) {
            if (!scanner.hasNextLine()) {
                System.out.println("Nothing to load.");
                printLine();
                return;
            } else {
                String balance = scanner.nextLine();
                this.balance = Double.parseDouble(balance);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    Product p = new Product(parts[0], Double.parseDouble(parts[1]), parts[2]);
                    products.add(p);
                }
                System.out.println("Purchases were loaded!");
            }
        } catch (IOException e) {
            System.out.println("File could not be loaded: " + e);
        }
        printLine();
    }

    public String processTypeOfPurchase(Scanner scanner) {
        int number = scanner.nextInt();
        scanner.nextLine();
        printLine();
        switch (number) {
            case 1 -> {
                return "Food";
            }
            case 2 -> {
                return "Clothes";
            }
            case 3 -> {
                return "Entertainment";
            }
            case 4 -> {
                return "Other";
            }
            case 5 -> {
                return "Back";
            }
        }
        return "";
    }

    public void processCommandNumber(Scanner scanner) {
        while (true) {
            printMenu();
            int number = scanner.nextInt();
            scanner.nextLine();
            printLine();
            switch (number) {
                case 1 -> {
                    addIncome(scanner);
                    printLine();
                }
                case 2 -> addPurchase(scanner);
                case 3 -> showPurchasedProducts(scanner);
                case 4 -> {
                    printBalance();
                    printLine();
                }
                case 5 -> savePurchasesIntoFile();
                case 6 -> loadPurchasesFromFile();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
            }
        }
    }

    public void addIncome(Scanner scanner) {
        System.out.println("Enter income:");
        setBalance(scanner.nextDouble() + getBalance());
        System.out.println("Income was added!");
    }

    public void addPurchase(Scanner scanner) {
        while (true) {
            printTypeOfPurchase(false);
            String category = processTypeOfPurchase(scanner);

            if (category.equals("Back")) {
                break;
            }

            if (!category.isEmpty()) {
                System.out.println("Enter purchase name:");
                String name = scanner.nextLine();
                System.out.println("Enter its price:");
                double price = scanner.nextDouble();
                products.add(new Product(name, price, category));
                setBalance(getBalance() - price);
                System.out.println("Purchase was added!");
            }
            printLine();
        }
    }

    public void printTypeOfPurchase(Boolean plural) {
        System.out.println("Choose the type of " + (plural ? "purchases" : "purchase"));
        System.out.println("""
                1) Food
                2) Clothes
                3) Entertainment
                4) Other""");
        if (plural) {
            System.out.println("5) All");
            System.out.println("6) Back");
        } else {
            System.out.println("5) Back");
        }
    }

    public String processTypeOfPurchases(Scanner scanner) {
        int number = scanner.nextInt();
        scanner.nextLine();
        printLine();
        switch (number) {
            case 1 -> {
                return "Food";
            }
            case 2 -> {
                return "Clothes";
            }
            case 3 -> {
                return "Entertainment";
            }
            case 4 -> {
                return "Other";
            }
            case 5 -> {
                return "All";
            }
            case 6 -> {
                return "Back";
            }
        }
        return "";
    }

    public void showPurchasedProducts(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("The purchase list is empty!");
            printLine();
            return;
        }
        while (true) {
            printTypeOfPurchase(true);
            String category = processTypeOfPurchases(scanner);

            if (category.equals("Back")) {
                break;
            }

            double sum = 0;
            System.out.println(category + ":");
            for (Product p : this.products) {
                if (category.equals("All") || p.getCategory().name().equals(category)) {
                    System.out.println(p);
                    sum += p.getPrice();
                }
            }

            if (sum == 0) {
                System.out.println("The purchase list is empty!");
            } else {
                System.out.printf("Total sum: $%.2f\n", sum);
            }
            printLine();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double income) {
        this.balance = income;
    }

    public void printBalance() {
        System.out.printf("Balance: $%.2f\n", getBalance());
    }

    public void printLine() {
        System.out.println();
    }

}
