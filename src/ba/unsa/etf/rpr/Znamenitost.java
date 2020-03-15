package ba.unsa.etf.rpr;

public class Znamenitost {
    private int id;
    private String naziv;
    private String slika;

    public Znamenitost() {
    }
    public Znamenitost(int id, String naziv, String slika) {
        this.id = id;
        this.naziv = naziv;
        this.slika = slika;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
