package com.x.quant.fundamentals.datastructures;

import java.util.*;

class Vehicle {
    protected String brand = "Ford";
    public void honk() {
        System.out.println("Tuut, tuut!");
    }
}

class Car extends Vehicle {

    String modelName;

    public Car(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void honk() {
        System.out.println(modelName + " " + modelName);
    }
}

interface Instrument {
    void printInstrument();
}

class Cryptocurrency implements Instrument {
    String name;
    public Cryptocurrency(String name) {
        this.name = name;
    }

    public void printInstrument() {
        System.out.println(name);
    }
}

public class DataStructures {

    public static void main(String[] args) {

        Car car = new Car("Fiesta");
        car.honk();
        String brand = car.brand;
        String model = car.modelName;
        System.out.println(brand + model);

        Cryptocurrency cryptocurrency = new Cryptocurrency("BONK");
        cryptocurrency.printInstrument();

        ArrayList<Cryptocurrency> cryptocurrencies = new ArrayList<>(List.of(cryptocurrency, cryptocurrency, cryptocurrency));
        LinkedList<Cryptocurrency> cryptos = new LinkedList<>(List.of(cryptocurrency, cryptocurrency, cryptocurrency));
        cryptocurrencies.set(0, null);

        // enhanced for loop
        for (Cryptocurrency crypto : cryptocurrencies) {
            System.out.println(crypto);
        }
    }
}
