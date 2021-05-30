package com.slinger;

import java.util.Scanner;

public class NumberController {

    private final NumberProperties properties;
    private final Scanner scanner;

    public NumberController(NumberProperties properties, Scanner scanner) {
        this.properties = properties;
        this.scanner = scanner;
    }

    public void run() {
        boolean isRunning = true;

        printGreeting();
        printMenu();

        while (isRunning) {
            System.out.print("Enter a request: ");
            String[] input = scanner.nextLine().split("\\s+");

            //check for empty input
            if (input[0].isEmpty()) {
                printMenu();
                continue;
            }
            //check to make sure first argument is int
            if (!input[0].matches("\\d+")) {
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
                continue;
            }

            //check to make sure second argument is int
            if (input.length > 1 && (!input[1].matches("\\d+") || Long.parseLong(input[1]) < 0)) {
                System.out.println("\nThe second parameter should be a natural number.\n");
                continue;
            }

            long number = Long.parseLong(input[0]);

            //check if user wants to quit
            if (number == 0) {
                isRunning =false;
                System.out.println("\nGoodbye!");
                continue;
            }

            //get number properties
            if (input.length == 1) {
                printNumberProperties(number);
            } else if (input.length == 2 && input[1].matches("\\d+")) {
                int range = Integer.parseInt(input[1]);
                printRangeOfProperties(number, range);
            }
        }
    }

    private void printNumberProperties(long num) {
        boolean isEven = properties.isEven(num);
        boolean isOdd = !isEven;
        boolean isBuzz = properties.isBuzzNumber(num);
        boolean isDuck = properties.isDuckNumber(num);
        boolean isPalindromic = properties.isPalindromic(num);
        boolean isGapful = properties.isGapful(num);

        System.out.println("\nProperties of " + num + "\n" +
                "        even: " + isEven + "\n" +
                "         odd: " + isOdd + "\n" +
                "        buzz: " + isBuzz + "\n" +
                "        duck: " + isDuck + "\n" +
                " palindromic: " + isPalindromic + "\n" +
                "      gapful: " + isGapful + "\n");

    }

    private void printRangeOfProperties(long num, int range) {
        System.out.println();
        long upTo = num + range;

        while (num < upTo) {
            boolean isEven = properties.isEven(num);
            boolean isOdd = !isEven;
            boolean isBuzz = properties.isBuzzNumber(num);
            boolean isDuck = properties.isDuckNumber(num);
            boolean isPalindromic = properties.isPalindromic(num);
            boolean isGapful = properties.isGapful(num);

            StringBuilder holder = new StringBuilder();
            holder.append("\t\t").append(num).append(" is ");

            if (isEven) {
                holder.append("even, ");
            }

            if (isOdd) {
                holder.append("odd, ");
            }

            if (isBuzz) {
                holder.append("buzz, ");
            }

            if (isDuck) {
                holder.append("duck, ");
            }

            if (isPalindromic) {
                holder.append("palindromic, ");
            }

            if (isGapful) {
                holder.append("gapful, ");
            }

            //remove last , from holder
            holder.setLength(holder.length() - 2);
            System.out.println(holder);
            num++;
        }

        System.out.println();
    }

    private void printGreeting() {
        System.out.println("Welcome to Amazing Numbers!");
    }

    private void printMenu() {
        System.out.println("\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }


}
