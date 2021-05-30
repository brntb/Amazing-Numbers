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
            checkEvenOrOdd(num);
            checkBuzzNumber(num);
        }


    }

    public static void checkEvenOrOdd(int num) {
        if (num % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
    }

    //buzz numbers are divisible by 7 or end with 7.
    public static void checkBuzzNumber(int num) {
        boolean isDivisibleBy7 = num % 7 == 0;
        boolean endsIn7 = num % 10 == 7;

        //check if it is a buzz number
        if (isDivisibleBy7 || endsIn7)  {
            System.out.println("It is a Buzz number.");
        } else {
            System.out.println("It is not a Buzz number.");
        }

        //print out explanations
        System.out.println("Explanation:");
        if (isDivisibleBy7 && endsIn7) {
            System.out.println(num + " is divisible by 7 and it ends with 7.");
        } else if (isDivisibleBy7) {
            System.out.println(num + " is divisible by 7.");
        } else if (endsIn7) {
            System.out.println(num + " is ends with 7.");
        } else {
            System.out.println(num + " is neither divisible by 7 nor it ends with 7.");
        }

    }

}
