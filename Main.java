package budget;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        double total = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            list.add(line);
        }

        for (String s : list) {
            Pattern pattern = Pattern.compile("([\\w']*\\s+)+([$])(\\d+[.]\\d{2})");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(matcher.group(0));
                total += Double.parseDouble(matcher.group(3));
            }
        }

        System.out.printf("\nTotal: $%.2f", total);
    }
}
