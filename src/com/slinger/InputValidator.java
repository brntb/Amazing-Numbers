package com.slinger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//validates input user passes
public class InputValidator {

    private final List<String> invalidProperties; //holds all invalid properties given for each request

    public InputValidator() {
        invalidProperties = new ArrayList<>();
    }

    public boolean isValid(String input) {
        List<String> inputList = Arrays.asList(input.split("\\s+"));

        //check to make sure first argument is whole number
        if (!inputList.get(0).matches("\\d+") || Long.parseLong(inputList.get(0)) < 0) {
            System.out.println("\nThe first parameter should be a natural number or zero.\n");
            return false;
        }

        //if second argument give, check to make sure second argument is whole number
        if (inputList.size() == 2 && (!inputList.get(1).matches("\\d+") || Long.parseLong(inputList.get(1)) < 0)) {
            System.out.println("\nThe second parameter should be a natural number.\n");
            return false;
        }


        //check for mutually exclusive properties
        if (inputList.contains("even") && inputList.contains("odd")) {
            printMutuallyExclusiveProperties("even", "odd");
            return  false;
        } else if (inputList.contains("-even") && inputList.contains("-odd")) {
            printMutuallyExclusiveProperties("-even", "-odd");
            return  false;
        }

        if (inputList.contains("duck") && inputList.contains("spy")) {
            printMutuallyExclusiveProperties("duck", "spy");
            return  false;
        } else if (inputList.contains("-duck") && inputList.contains("-spy")) {
            printMutuallyExclusiveProperties("-duck", "-spy");
            return false;
        }

        if (inputList.contains("sunny") && inputList.contains("square")) {
            printMutuallyExclusiveProperties("sunny", "square");
            return  false;
        }

        if (inputList.contains("happy") && inputList.contains("sad")) {
            printMutuallyExclusiveProperties("happy", "sad");
            return  false;
        } else if (inputList.contains("-happy") && inputList.contains("-sad")) {
            printMutuallyExclusiveProperties("-happy", "-sad");
            return false;
        }

        //check for properties that contradict themselves
        //for example odd and -odd, or even and -even
        for (String current : inputList) {
            String negated = "-" + current;

            if (inputList.contains(negated)) {
                printMutuallyExclusiveProperties(current, negated);
                return false;
            }
        }

        //clear invalid input from previous check
        invalidProperties.clear();

        //add all invalid properties to list
        for (int i = 0; i < inputList.size(); i++) {
            String property = inputList.get(i);

            //ignore number arguments, first two arguments
            if (i < 2) {
                continue;
            }

            //ignore '-' in property
            if (property.startsWith("-")) {
                property = property.substring(1);
            }

            if (!ValidProperties.isValid(property)) {
                invalidProperties.add(property.toUpperCase());
            }
        }

        //print all invalid properties back to user
        if (!invalidProperties.isEmpty()) {
            printInvalidProperties();
            return false;
        }

        return true;
    }

    private void printMutuallyExclusiveProperties(String property1, String property2) {
        System.out.println("\nThe request contains mutually exclusive properties: [" + property1.toUpperCase() + ", " + property2.toUpperCase() +"]\n" +
                "There are no numbers with these properties.\n");
    }

    private void printInvalidProperties() {
        if (invalidProperties.size() == 1) {
            System.out.println("\nThe property " + invalidProperties + " is wrong.");
        } else {
            System.out.println("\nThe properties " + invalidProperties + " are wrong.");
        }

        System.out.println("Available properties: " + Arrays.asList(ValidProperties.values()) + "\n");
    }

}
