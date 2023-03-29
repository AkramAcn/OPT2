import java.util.*;

abstract class Auto {
    private String merk;
    private String model;
    private double prijs;

    public Auto(String merk, String model, double prijs) {
        this.merk = merk;
        this.model = model;
        this.prijs = prijs;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
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

class ParticuliereAuto extends Auto {

    public ParticuliereAuto(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        return (getPrijs() + berekenBtw()) / 48;
    }
}

class ZakelijkeAuto extends Auto {

    public ZakelijkeAuto(String merk, String model, double prijs) {
        super(merk, model, prijs);
    }

    @Override
    public double berekenMaandkosten() {
        return (getPrijs() + berekenBtw() + berekenBijtelling(true)) / 48;
    }
}

 class AutoKeuze {

    private List<Auto> autoLijst = new ArrayList<Auto>();

    public AutoKeuze() {
        autoLijst.add(new ParticuliereAuto("Toyota", "Yaris", 16500));
        autoLijst.add(new ParticuliereAuto("Ford", "Fiesta", 17500));
        autoLijst.add(new ZakelijkeAuto("Tesla", "Model S", 80000));
        autoLijst.add(new ZakelijkeAuto("BMW", "X5", 75000));
    }

    public Auto kiesAuto() {
        System.out.println("Kies een auto:");
        for (int i = 0; i < autoLijst.size(); i++) {
            System.out.println((i + 1) + ". " + autoLijst.get(i).getMerk() + " " + autoLijst.get(i).getModel());
        }

        Scanner scanner = new Scanner(System.in);
        int keuzeAuto = scanner.nextInt();
        scanner.nextLine();
        return autoLijst.get(keuzeAuto - 1);
    }

}

 class AutoBerekening {

    private Auto auto;

    public AutoBerekening(Auto auto) {
        this.auto = auto;
    }

    public void toonResultaten(boolean isZakelijk, double bedrag) {
        auto.setPrijs(bedrag);

        System.out.println("De BTW bedraagt: " + auto.berekenBtw());
        System.out.println("De bijtelling bedraagt: " + auto.berekenBijtelling(isZakelijk));
        System.out.println("De maandelijkse kosten bedragen: " + auto.berekenMaandkosten());
    }

}

 class Main {

    public static void main(String[] args) {
        AutoKeuze autoKeuze = new AutoKeuze();
        Auto gekozenAuto = autoKeuze.kiesAuto();

        System.out.println("Voer het bedrag van de auto in:");
        Scanner scanner = new Scanner(System.in);
        double bedrag = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Is dit een zakelijke auto? (ja/nee)");
        String keuze = scanner.nextLine();
        boolean isZakelijk = keuze.equalsIgnoreCase("ja");

        AutoBerekening autoBerekening = new AutoBerekening(gekozenAuto);
        autoBerekening.toonResultaten(isZakelijk, bedrag);
    }

}





