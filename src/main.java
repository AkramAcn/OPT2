import java.util.Scanner;

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
        boolean voorwaardeA = (getMerk().equals("Toyota"));
        boolean voorwaardeB = (getModel().equals("Yaris"));

        if (voorwaardeB || (voorwaardeA && voorwaardeB)) {
            return (int) (getPrijs() + berekenBtw()) / 48;
        } else {
            return 0;
        }
    }
}

class ZakelijkeAuto extends Voertuig {
    public ZakelijkeAuto(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        boolean voorwaardeA = (getMerk().equals("Tesla"));
        boolean voorwaardeB = (getModel().equals("Model S"));

        if (voorwaardeB || (voorwaardeA && voorwaardeB)) {
            return (int) (getPrijs() + berekenBtw() + berekenBijtelling(true)) / 48;
        } else {
            return 0;
        }
    }
}

class Motor extends Voertuig {
    public Motor(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        boolean voorwaardeA = (getMerk().equals("Yamaha"));
        boolean voorwaardeB = (getModel().equals("MT125"));

        if (voorwaardeB || (voorwaardeA && voorwaardeB)) {
            return (int) (getPrijs() + berekenBtw()) / 24;
        } else {
            return 0;
        }
    }
}

class VoertuigAanmaken {
    public static Voertuig[] createVoertuigen() {
        Voertuig[] voertuigen = {
                new ParticuliereAuto("Toyota", "Yaris", 16500),
                new ZakelijkeAuto("Tesla", "Model S", 80000),
                new Motor("Yamaha", "MT125", 120000),
                new ParticuliereAuto("Audi", "A4", 25000),
        };
        return voertuigen;
    }
}

class VoertuigOverzicht {
    public static void toonOverzicht(Voertuig[] voertuigen) {
        System.out.println("Overzicht van alle voertuigen:");
        for (Voertuig voertuig : voertuigen) {
            System.out.println("Merk: " + voertuig.getMerk());
            System.out.println("Model: " + voertuig.getModel());
            System.out.println("Prijs: " + voertuig.getPrijs());
            System.out.println("Maandelijkse kosten: " + voertuig.berekenMaandkosten());
            System.out.println("--------------------");
        }
    }
}

class VoertuigSelector {
    public static Voertuig selecteerVoertuig(Voertuig[] voertuigen, String safetyPreference, String energyLabelPreference) {
        for (Voertuig voertuig : voertuigen) {
            if (voldoetAanVoorkeur(voertuig, safetyPreference, energyLabelPreference)) {
                return voertuig;
            }
        }
        return null;
    }


    private static boolean voldoetAanVoorkeur(Voertuig voertuig, String safetyPreference, String energyLabelPreference) {
        String merk = voertuig.getMerk();

        if (safetyPreference.equalsIgnoreCase("hoog") && energyLabelPreference.equalsIgnoreCase("laag")) {
            return merk.equals("Audi");
        }
        if (safetyPreference.equalsIgnoreCase("hoog") && energyLabelPreference.equalsIgnoreCase("hoog")) {
            return merk.equals("Tesla");
        }
        if (safetyPreference.equalsIgnoreCase("laag") && energyLabelPreference.equalsIgnoreCase("laag")) {
            return merk.equals("Audi");
        }

        return false;
    }

}


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Voertuig[] voertuigen = VoertuigAanmaken.createVoertuigen();
        VoertuigOverzicht.toonOverzicht(voertuigen);

        System.out.println("Kies een auto op basis van je voorkeuren:");
        System.out.print("Veiligheidswens (hoog, gemiddeld, laag): ");
        String safetyPreference = scanner.nextLine();
        System.out.print("Energielabelwens (hoog, gemiddeld, laag): ");
        String energyLabelPreference = scanner.nextLine();

        Voertuig selectedVehicle = VoertuigSelector.selecteerVoertuig(voertuigen, safetyPreference, energyLabelPreference);
        if (selectedVehicle != null) {
            System.out.println("Het beste voertuig voor jou is:");
            System.out.println("Merk: " + selectedVehicle.getMerk());
            System.out.println("Model: " + selectedVehicle.getModel());
            System.out.println("Maandelijkse kosten: " + selectedVehicle.berekenMaandkosten());
        } else {
            System.out.println("Geen auto gevonden die aan jouw voorkeuren voldoet.");
        }
    }
}



