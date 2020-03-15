package ba.unsa.etf.rpr;

public class Znamenitost {
    private String naziv;
    private String slika;

    public Znamenitost() {
    }
    public Znamenitost(String naziv, String slika) {
        this.naziv = naziv;
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
