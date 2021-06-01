package com.slinger;

import java.util.Scanner;

public class NumberController {

    private final NumberProperties properties;
    private final InputValidator validator;
    private final Scanner scanner;


    public NumberController(NumberProperties properties, Scanner scanner) {
        this.properties = properties;
        this.validator = new InputValidator();
        this.scanner = scanner;
    }

    public void run() {
        boolean isRunning = true;

        printGreeting();
        printMenu();

        while (isRunning) {
            System.out.print("Enter a request: ");
            String input = scanner.nextLine().toLowerCase();

            //check for empty input
            if (input.isEmpty()) {
                printMenu();
                continue;
            }

            //check for invalid input
            if (!validator.isValid(input)) {
                continue;
            }

            //input is valid, split to array
            String[] arguments = input.split("\\s+".toLowerCase().trim());

            //grab number user wants info about
            long number = Long.parseLong(arguments[0]);

            //check if user wants to quit
            if (number == 0) {
                isRunning = false;
                System.out.println("\nGoodbye!");
                continue;
            }

            //get number properties
            if (arguments.length == 1) {
                printNumberProperties(number);
            } else if (arguments.length == 2) {
                int range = Integer.parseInt(arguments[1]);
                printRangeOfProperties(number, range);
            } else {
                int howMany = Integer.parseInt(arguments[1]);
                printSpecificRangeOfProperties(number, howMany, arguments);
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
        boolean isJumping = properties.isJumpingNumber(num);
        boolean isHappy = properties.isHappyNumber(num);
        boolean isSad = !isHappy;

        System.out.println("\nProperties of " + num + "\n" +
                "        even: " + isEven + "\n" +
                "         odd: " + isOdd + "\n" +
                "        buzz: " + isBuzz + "\n" +
                "        duck: " + isDuck + "\n" +
                " palindromic: " + isPalindromic + "\n" +
                "      gapful: " + isGapful + "\n" +
                "         spy: " + isSpy + "\n" +
                "      square: " + isSquare + "\n" +
                "       sunny: " + isSunny + "\n" +
                "     jumping: " + isJumping + "\n" +
                "       happy: " + isHappy + "\n" +
                "         sad: " + isSad + "\n"
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

    private void printSpecificRangeOfProperties(long num, int howMany, String[] givenProperties) {
        System.out.println();
        int found = 0;

        while (found < howMany) {
            String numberProperties = properties.getProperties(num); //string holds all properties of given num
            boolean hasAllProperties = true;

            //check if number properties has all given properties
            //start at i = 2, because first two numbers in array are just numbers
            for (int i = 2; i < givenProperties.length; i++) {
                String current = givenProperties[i];

                //check for negations
                if (current.startsWith("-")) {
                    current = current.substring(1);

                    if (numberProperties.contains(current)) {
                        hasAllProperties = false;
                        break;
                    }


                } else if (!numberProperties.contains(current)) { //check if property is in string
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
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }
}
