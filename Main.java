package budget;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\nTotal: $%.2f", getTotal(readInput(scanner)));
    }

    public static ArrayList<String> readInput(Scanner scanner) {
        ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            list.add(line);
        }

        return list;
    }

    public static double getTotal(ArrayList<String> list) {
        double total = 0;

        Pattern pattern = Pattern.compile("([\\w']*\\s+)+([$])(\\d+[.]\\d{2})");

        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(matcher.group(0));
                total += Double.parseDouble(matcher.group(3));
            }
        }

        return total;
    }
}
