package com.slinger;

import java.util.ArrayList;
import java.util.List;

public class NumberProperties {

    //return a string of all properties a number has
    public String getProperties(long num) {
        StringBuilder holder = new StringBuilder();
        holder.append("\t\t").append(num).append(" is ");

        if (isEven(num)) {
            holder.append("even, ");
        } else {
            holder.append("odd, ");
        }

        if (isBuzzNumber(num)) {
            holder.append("buzz, ");
        }

        if (isDuckNumber(num)) {
            holder.append("duck, ");
        }

        if (isPalindromicNumber(num)) {
            holder.append("palindromic, ");
        }

        if (isGapfulNumber(num)) {
            holder.append("gapful, ");
        }

        if (isSpyNumber(num)) {
            holder.append("spy, ");
        }

        if (isPerfectSquareNumber(num)) {
            holder.append("square, ");
        }

        if (isSunnyNumber(num)) {
            holder.append("sunny, ");
        }

        if (isJumpingNumber(num)) {
            holder.append("jumping, ");
        }

        if(isHappyNumber(num)) {
            holder.append("happy, ");
        } else {
            holder.append("sad, ");
        }



        //remove last , from holder
        holder.setLength(holder.length() - 2);

        return holder.toString();
    }

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
    public boolean isPalindromicNumber(long num) {
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
    public boolean isGapfulNumber(long num) {
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

    //A number is said to be Spy if the sum of all digits is equal to the product of all digits.
    public boolean isSpyNumber(long num) {
        String[] numArray = String.valueOf(num).split("");

        int product = 1;
        int sum = 0;

        //get product/sum of digits
        for (String strDigit : numArray) {
            int digit = Integer.parseInt(strDigit);
            product *= digit;
            sum += digit;
        }

        return sum == product;
    }

    public boolean isPerfectSquareNumber(long num) {
        long sr = (long) Math.sqrt(num);
        return (sr * sr) == num;
    }

    //N is a sunny number if N+1 is a perfect square numb
    public boolean isSunnyNumber(long num) {
        return isPerfectSquareNumber(num + 1);
    }

    // Jumping number if the adjacent digits inside the number differ by 1.
    public boolean isJumpingNumber(long num) {
        char[] digitArray = String.valueOf(num).toCharArray();

        //check two digit numbers
        if (digitArray.length == 2) {
            int leftDigit = Character.getNumericValue(digitArray[0]);
            int rightDigit = Character.getNumericValue(digitArray[1]);
            return Math.abs(leftDigit - rightDigit) == 1;
        }

        //check 3 digits or more
        for (int i = 1; i < digitArray.length - 1; i++) {
            int centerDigit = Character.getNumericValue(digitArray[i]);
            int leftDigit = Character.getNumericValue(digitArray[i - 1]);
            int rightDigit = Character.getNumericValue(digitArray[i + 1]);

            int leftDif = Math.abs(centerDigit - leftDigit);
            int rightDif = Math.abs(centerDigit - rightDigit);

            if (leftDif != 1 || rightDif != 1) {
                return false;
            }

        }

        return true;
    }

    //a happy number is a number that reaches 1 after a sequence during which the number is replaced by the sum of
    //each digit squares. For example, 13 is a happy number, as 12 + 32 = 10 which leads to 12 + 02 = 1.
    public boolean isHappyNumber(long num) {
        List<Long> seen = new ArrayList<>();

        while (true) {
            long squaredDigits = getSquaredDigits(num);

            if (seen.contains(squaredDigits)) {
                return false;
            }

            if (squaredDigits == 1) {
                return true;
            }

            seen.add(num);
            num = squaredDigits;
        }

    }

    private long getSquaredDigits(long num) {
        long sum = 0;

        while (num > 0) {
            long lastDigit = num % 10;
            sum += (lastDigit * lastDigit);
            num /= 10;
        }

        return sum;
    }


}
