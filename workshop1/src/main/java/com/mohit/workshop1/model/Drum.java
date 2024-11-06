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

public class Drum extends PercussionFamily{
    public Drum(double price) {
        super(price);
    }

    @Override
    public String makeSound() {
        return "vibrating streched membrane";
    }

    @Override
    public String getPitchType() {
        return "Sonic Pitch";
    }

    @Override
    public String HowToPlay() {
        return "by hitting the membrane";
    }

    @Override
    public String HowToFix() {
        return "replace the membrane";
    }
 }
