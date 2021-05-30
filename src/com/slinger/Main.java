package com.slinger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        String input = scanner.nextLine();

        //check if not number or not natural number
        if (!input.matches("\\d+") || Integer.parseInt(input) <= 0) {
            System.out.println("This number is not natural!");
        } else {
            int num = Integer.parseInt(input);
            boolean isEven = isEven(num);
            boolean isOdd = !isEven;
            boolean isBuzz = isBuzzNumber(num);
            boolean isDuck = isDuckNumber(num);

            System.out.println("Properties of " + num + "\n" +
                    "        even: " + isEven + "\n" +
                    "         odd: " + isOdd + "\n" +
                    "        buzz: " + isBuzz + "\n" +
                    "        duck: " + isDuck + "\n");
        }
    }

    //checks if even
    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    //buzz numbers are divisible by 7 or end with 7.
    public static boolean isBuzzNumber(int num) {
        boolean isDivisibleBy7 = num % 7 == 0;
        boolean endsIn7 = num % 10 == 7;

        return  isDivisibleBy7 || endsIn7;
    }

    //A Duck number is a positive number that contains zeroes
    public static boolean isDuckNumber(int num) {
        //loop through and check if a digit is 0
        while (num > 0) {
            int lastDigit = num % 10;
            if (lastDigit == 0 && num > 9) return true;
            num /= 10;
        }

        return false;
    }

}
