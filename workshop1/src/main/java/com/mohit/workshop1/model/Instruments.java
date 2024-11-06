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

import java.util.Arrays;

public class Instruments  {
    private MusicalInstrument[] instruments;

    public Instruments() {
        instruments = new MusicalInstrument[] {
                new Drum(0),
                new Flute(0),
                new Guitar(0),
                new Harp(0),
                new Xylophone(0)
        };
    }

    public MusicalInstrument getMostExpensiveInstrument(MusicalInstrument... instruments) {
        return Arrays.stream(instruments)
                .max(MusicalInstrument::compareTo)
                .orElse(null);
    }

    public MusicalInstrument[] getAllInstruments(){
        Arrays.sort(instruments);
        return instruments;
    }

}
