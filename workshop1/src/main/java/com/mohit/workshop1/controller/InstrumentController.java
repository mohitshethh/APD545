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

package com.mohit.workshop1.controller;

import com.mohit.workshop1.model.*;
import com.mohit.workshop1.view.InstrumentView;

import java.util.Scanner;

public class InstrumentController {

    private Instruments model;
    private InstrumentView view;

    public InstrumentController(Instruments model, InstrumentView view) {
        this.model = model;
        this.view = view;
    }

    public void Requirements() {
        System.out.println("--:Requirement 1:--");
        Drum drum = new Drum(view.getPrice("Drum"));
        Flute flute = new Flute(view.getPrice("Flute"));
        Guitar guitar = new Guitar(view.getPrice("Guitar"));
        Harp harp = new Harp(view.getPrice("Harp"));
        Xylophone xylophone = new Xylophone(view.getPrice("Xylophone"));

        view.showInstrumentInfo(model.getMostExpensiveInstrument(drum,flute,guitar,harp,xylophone));

        view.DescendingSort(model.getAllInstruments());

        view.displayFamilyInfo(model);
    }
}
// new Scanner(System.in)