package com.slinger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.");

        while (true) {
            System.out.println("\nEnter a request:");
            String input = scanner.nextLine();

            //check if not number or not natural number
            if (!input.matches("\\d+") || Long.parseLong(input) < 0) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            } else {
                long num = Long.parseLong(input);

                if (num == 0) break;

                boolean isEven = isEven(num);
                boolean isOdd = !isEven;
                boolean isBuzz = isBuzzNumber(num);
                boolean isDuck = isDuckNumber(num);
                boolean isPalindromic = isPalindromic(num);

                System.out.println("Properties of " + num + "\n" +
                        "        even: " + isEven + "\n" +
                        "         odd: " + isOdd + "\n" +
                        "        buzz: " + isBuzz + "\n" +
                        "        duck: " + isDuck + "\n" +
                        " palindromic: " + isPalindromic);
            }

        }

        System.out.println("Goodbye!");
    }

    //checks if even
    public static boolean isEven(long num) {
        return num % 2 == 0;
    }

    //buzz numbers are divisible by 7 or end with 7.
    public static boolean isBuzzNumber(long num) {
        boolean isDivisibleBy7 = num % 7 == 0;
        boolean endsIn7 = num % 10 == 7;

        return  isDivisibleBy7 || endsIn7;
    }

    //A Duck number is a positive number that contains zeroes
    public static boolean isDuckNumber(long num) {
        //loop through and check if a digit is 0
        while (num > 0) {
            long lastDigit = num % 10;
            if (lastDigit == 0 && num > 9) return true;
            num /= 10;
        }

        return false;
    }

    //A Palindromic number is symmetrical; in other words, it stays the same regardless of
    // whether we read it from left or right
    public static boolean isPalindromic(long num) {
        char[] holder = String.valueOf(num).toCharArray();
        int start = 0;
        int end = holder.length - 1;

        while (start < end) {
            if (holder[start] != holder[end]) return false;

            start++;
            end--;
        }

        return true;
    }


}
