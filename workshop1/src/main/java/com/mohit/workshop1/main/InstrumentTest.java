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

package com.mohit.workshop1.main;

import com.mohit.workshop1.controller.InstrumentController;
import com.mohit.workshop1.model.Instruments;
import com.mohit.workshop1.view.InstrumentView;

public class InstrumentTest {

    public static void main(String[] args) {
        Instruments model = new Instruments();
        InstrumentView view = new InstrumentView();
        InstrumentController controller = new InstrumentController(model, view);

        controller.Requirements();

    }
}
