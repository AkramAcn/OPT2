package com.javaCode;


abstract class Voertuig {
    private String merk;
    private String model;
    private double prijs;

    public Voertuig(String merk, String model, double prijs) {
        this.merk = merk;
        this.model = model;
        this.prijs = prijs;
    }

    public String getMerk() {
        return merk;
    }

    public String getModel() {
        return model;
    }


    public double getPrijs() {
        return prijs;
    }

    public abstract double berekenMaandkosten();

    public double berekenBtw() {
        return prijs * 0.21;
    }

    public double berekenBijtelling(boolean zakelijk) {
        if (zakelijk) {
            return prijs * 0.22;
        } else {
            return 0;
        }
    }
}

class ParticuliereAuto extends Voertuig {
    public ParticuliereAuto(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        return (int) (getPrijs() + berekenBtw()) / 48;
    }
}

class ZakelijkeAuto extends Voertuig {
    public ZakelijkeAuto(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        return (int) (getPrijs() + berekenBtw() + berekenBijtelling(true)) / 48;
    }
}

class Motor extends Voertuig {

    public Motor(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        return (int) (getPrijs() + berekenBtw()) / 24;  // Aangepaste berekening voor vrachtwagens
    }
}

class Main {
    public static void main(String[] args) {
        Voertuig myAuto = new ParticuliereAuto("Toyota", "Yaris", 16500);
        Voertuig myAuto2 = new ZakelijkeAuto("Tesla", "Model S", 80000);
        Voertuig myMotor = new Motor("Yamaha", "MT125", 120000);

        Voertuig[] voertuigen = new Voertuig[3];
        voertuigen[0] = myAuto;
        voertuigen[1] = myAuto2;
        voertuigen[2] = myMotor;

        for (Voertuig voertuig : voertuigen) {
            System.out.println("Merk: " + voertuig.getMerk());
            System.out.println("Model: " + voertuig.getModel());
            System.out.println("Maandelijkse kosten: " + voertuig.berekenMaandkosten());
            System.out.println("---------");
        }
    }
}