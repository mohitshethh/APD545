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

import com.mohit.workshop1.interfaces.IFixable;
import com.mohit.workshop1.interfaces.IPlayable;

import java.util.Comparator;

public abstract class MusicalInstrument implements IFixable, IPlayable, Comparable<MusicalInstrument> {

    protected double price;

    public MusicalInstrument(double price) {
        this.price = price;
    }

    public double GetPrice() {
        return price;
    }

    public abstract String makeSound();
    public abstract String getPitchType();

    @Override
    public int compareTo(MusicalInstrument other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }


}

