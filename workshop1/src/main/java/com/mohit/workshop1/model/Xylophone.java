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

public class Xylophone extends PercussionFamily {
    public Xylophone(double price) {
        super(price);
    }

    @Override
    public String makeSound() {
        return "through resonators";
    }

    @Override
    public String getPitchType() {
        return "Each bar produces different pitch";
    }

    @Override
    public String HowToPlay() {
        return "with two mallets";
    }

    @Override
    public String HowToFix() {
        return "replace bars";
    }
}
