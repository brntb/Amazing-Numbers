package com.slinger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumberController {

    private final NumberProperties properties;
    private final Scanner scanner;
    private final List<String> validProperties;

    public NumberController(NumberProperties properties, Scanner scanner) {
        this.properties = properties;
        this.scanner = scanner;

        validProperties = Arrays.asList(
                "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY"
        );
    }

    public void run() {
        boolean isRunning = true;

        //holds properties user wants to search for
        List<String> givenProperties = new ArrayList<>();

        printGreeting();
        printMenu();

        start:while (isRunning) {
            givenProperties.clear();

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
            if (input.length == 2 && (!input[1].matches("\\d+") || Long.parseLong(input[1]) < 0)) {
                System.out.println("\nThe second parameter should be a natural number.\n");
                continue;
            }

            //add all given properties to list
            if (input.length >= 3) {
                for (int i = 2; i < input.length; i++) {
                    String property = input[i];

                    givenProperties.add(property.toLowerCase());

                    //check for invalid pairs given, Even and Odd, Duck and Spy, Sunny and Square
                    if (givenProperties.contains("even") && givenProperties.contains("odd")) {
                        printMutuallyExclusiveProperties("odd", "even");
                        continue start;
                    }

                    if (givenProperties.contains("duck") && givenProperties.contains("spy")) {
                        printMutuallyExclusiveProperties("spy", "duck");
                        continue start;
                    }

                    if (givenProperties.contains("sunny") && givenProperties.contains("square")) {
                        printMutuallyExclusiveProperties("square", "sunny");
                        continue start;
                    }
                }
            }

            //get all invalid properties if any are given
            List<String> invalidProperties = new ArrayList<>();
            for (String property : givenProperties) {
                if (!validProperties.contains(property.toUpperCase())) {
                    invalidProperties.add(property.toUpperCase());
                }
            }

            //print all invalid properties back to user
            if (!invalidProperties.isEmpty()) {
                printInvalidProperties(invalidProperties);
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
            } else if (input.length == 2) {
                int range = Integer.parseInt(input[1]);
                printRangeOfProperties(number, range);
            } else {
                int howMany = Integer.parseInt(input[1]);
                printSpecificRangeOfProperties(number, howMany, givenProperties);
            }
        }
    }

    private void printNumberProperties(long num) {
        boolean isEven = properties.isEven(num);
        boolean isOdd = !isEven;
        boolean isBuzz = properties.isBuzzNumber(num);
        boolean isDuck = properties.isDuckNumber(num);
        boolean isPalindromic = properties.isPalindromicNumber(num);
        boolean isGapful = properties.isGapfulNumber(num);
        boolean isSpy = properties.isSpyNumber(num);
        boolean isSquare = properties.isPerfectSquareNumber(num);
        boolean isSunny = properties.isSunnyNumber(num);

        System.out.println("\nProperties of " + num + "\n" +
                "        even: " + isEven + "\n" +
                "         odd: " + isOdd + "\n" +
                "        buzz: " + isBuzz + "\n" +
                "        duck: " + isDuck + "\n" +
                " palindromic: " + isPalindromic + "\n" +
                "      gapful: " + isGapful + "\n" +
                "         spy: " + isSpy + "\n" +
                "      square: " + isSquare + "\n" +
                "       sunny: " + isSunny + "\n"
        );

    }

    private void printRangeOfProperties(long num, int range) {
        System.out.println();
        long upTo = num + range;

        while (num < upTo) {
            String numberProperties = properties.getProperties(num);
            System.out.println(numberProperties);
            num++;
        }

        System.out.println();
    }

    private void printSpecificRangeOfProperties(long num, int howMany, List<String> givenProperties) {
        System.out.println();
        int found = 0;

        while (found < howMany) {
            String numberProperties = properties.getProperties(num);
            boolean hasAllProperties = true;

            //check if number properties has all given properties
            for (String current : givenProperties) {
                if (!numberProperties.contains(current)) {
                    hasAllProperties = false;
                    break;
                }
            }

            if (hasAllProperties) {
                found++;
                System.out.println(numberProperties);
            }

            num++;
        }

        System.out.println();
    }


    private void printGreeting() {
        System.out.println("Welcome to Amazing Numbers!");
    }

    private void printMenu() {
        System.out.println("\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }


    private void printInvalidProperties(List<String> invalidProperties) {

        if (invalidProperties.size() == 1) {
            System.out.println("\nThe property " + invalidProperties + " is wrong.");
        } else {
            System.out.println("\nThe properties " + invalidProperties + " are wrong.");
        }

        System.out.println("Available properties: " + validProperties + "\n");
    }

    private void printMutuallyExclusiveProperties(String property1, String property2) {
        System.out.println("\nThe request contains mutually exclusive properties: [" + property1.toUpperCase() + ", " + property2.toUpperCase() +"]\n" +
                "There are no numbers with these properties.\n");
    }


}
