package budget;

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
                0) Exit
                """);
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
