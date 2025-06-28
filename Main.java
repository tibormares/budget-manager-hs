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

    public void processCommandNumber(Scanner scanner) {
        while (true) {
            printMenu();
            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (number) {
                case 1 -> addIncome(scanner);
                case 2 -> addPurchase(scanner);
                case 3 -> showPurchasedProducts();
                case 4 -> printBalance();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
            }
            System.out.println();
        }
    }

    public void addIncome(Scanner scanner) {
        System.out.println("Enter income:");
        setBalance(scanner.nextDouble() + getBalance());
        System.out.println("Income was added!");
    }

    public void addPurchase(Scanner scanner) {
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        products.add(new Product(name, price));
        setBalance(getBalance() - price);
        System.out.println("Purchase was added!");
    }

    public void showPurchasedProducts() {
        if (products.isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            double sum = 0;
            for (Product p : this.products) {
                System.out.println(p);
                sum += p.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", sum);
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

}
