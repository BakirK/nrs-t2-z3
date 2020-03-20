package ba.unsa.etf.nrs;

public class Znamenitost {
    private int id;
    private String naziv;
    private String slika;
    private int gradID;

    public Znamenitost() {
    }
    public Znamenitost(int id, String naziv, String slika, int gradID) {
        this.id = id;
        this.naziv = naziv;
        this.slika = slika;
        this.gradID = gradID;
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

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
