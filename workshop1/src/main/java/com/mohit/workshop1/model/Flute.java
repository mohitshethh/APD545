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

public class Flute extends WoodwindFamily {
    public Flute(double price) {
        super(price);
    }

    @Override
    public String makeSound() {
        return "guiding a stream of air";
    }

    @Override
    public String getPitchType() {
        return "Fundamental pitch is middle C";
    }

    @Override
    public String HowToPlay() {
        return "by blowing into the flute";
    }

    @Override
    public String HowToFix() {
        return "N/A:it cannot be fixed";
    }
}
