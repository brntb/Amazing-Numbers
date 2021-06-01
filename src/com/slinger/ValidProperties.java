package com.slinger;

public enum ValidProperties {

    BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING, HAPPY, SAD;

    public static boolean isValid(String property) {
        for (ValidProperties validProperties : ValidProperties.values()) {
            if (validProperties.name().equals(property.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

}
