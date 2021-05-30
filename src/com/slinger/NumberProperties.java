package com.slinger;

public class NumberProperties {

    //checks if even
    public boolean isEven(long num) {
        return num % 2 == 0;
    }

    //buzz numbers are divisible by 7 or end with 7.
    public boolean isBuzzNumber(long num) {
        boolean isDivisibleBy7 = num % 7 == 0;
        boolean endsIn7 = num % 10 == 7;

        return isDivisibleBy7 || endsIn7;
    }

    //A Duck number is a positive number that contains zeroes
    public boolean isDuckNumber(long num) {
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
    public boolean isPalindromic(long num) {
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

    //Gapful numbers. It is a number that contains at least 3 digits and is divisible by
    //the concatenation of its first and last digit without a remainder
    public boolean isGapful(long num) {
        String[] strArray = String.valueOf(num).split("");

        if (strArray.length < 3) {
            return false;
        }

        String firstDigit = strArray[0];
        String lastDigit = strArray[strArray.length - 1];
        String concatenation = firstDigit + lastDigit;
        long toDivideBy = Long.parseLong(concatenation);

        return num % toDivideBy == 0;
    }

}
