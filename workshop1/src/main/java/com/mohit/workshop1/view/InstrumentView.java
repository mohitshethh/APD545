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

package com.mohit.workshop1.view;

import com.mohit.workshop1.model.*;

import java.util.Scanner;

public class InstrumentView {

    Scanner scanner = new Scanner(System.in);

    public double getPrice(String instrument) {
        System.out.print("Enter the price for " + instrument + ": ");
        return scanner.nextDouble();
    }

    public void showInstrumentInfo(MusicalInstrument mostExpensive) {
        System.out.println("\n--:Requirement 2:--");
        System.out.println("The most expensive instrument is: " + mostExpensive);
        System.out.println(mostExpensive + " cost is: $" + mostExpensive.GetPrice());
        System.out.println(mostExpensive + " is played: " + mostExpensive.HowToPlay());
        System.out.println(mostExpensive + " fixing: " + mostExpensive.HowToFix());
        System.out.println(mostExpensive + " pitch type: " + mostExpensive.getPitchType() + "\n");
    }

    public void DescendingSort(MusicalInstrument[] instruments) {
        System.out.println("--: Requirement 3 :--");
        System.out.println("Instruments in price descending order:");
        System.out.print("[");
        for (MusicalInstrument instrument : instruments) {
            System.out.print(instrument + ",");
        }
        System.out.print("]\n");
    }

    public void displayFamilyInfo(Instruments model) {
        System.out.println("\n--: Requirement 4 :--");
        System.out.print("Enter an instrument family: ");
        String familyName = scanner.next();

        switch (familyName.toLowerCase()) {
            case "string":
                sound(new StringFamily[]{new Guitar(0), new Harp(0)});
                break;
            case "percussion":
                sound(new PercussionFamily[]{new Drum(0), new Xylophone(0)});
                break;
            case "woodwind":
                sound(new WoodwindFamily[]{new Flute(0)});
                break;
            default:
                System.out.println("Family name is incorrect");
        }
    }

    public void sound(MusicalInstrument[] instruments) {
        for (MusicalInstrument instrument : instruments) {
            System.out.println(instrument + " makes sound " + instrument.makeSound() + ".");
        }
    }
}
