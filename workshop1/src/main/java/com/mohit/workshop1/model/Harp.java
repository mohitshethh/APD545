/**********************************************
 Workshop #1
 Course:APD545
 Last Name: Sheth
 First Name: Mohit
 ID: 114498223
 Section: ZAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: 22nd Sep 2024
 **********************************************/

package com.mohit.workshop1.model;

public class Harp extends StringFamily {
    public Harp(double price) {
        super(price);
    }

    @Override
    public String makeSound() {
        return "vibrating strings";
    }

    @Override
    public String getPitchType() {
        return "Has seven levels of pitch";
    }

    @Override
    public String HowToPlay() {
        return "with the thumb and the first three fingers";
    }

    @Override
    public String HowToFix() {
        return "replace the strings";
    }
}
