package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Grad {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;
    private ArrayList<Znamenitost> znamenitosti;

    private int postanskiBroj;

    public Grad(int id, String naziv, int brojStanovnika, int postanskiBroj, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        this.postanskiBroj = postanskiBroj;
    }

    public Grad() {
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

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public ArrayList<Znamenitost> getZnamenitosti() {
        return znamenitosti;
    }

    public void setZnamenitosti(ArrayList<Znamenitost> znamenitosti) {
        this.znamenitosti = znamenitosti;
    }

    @Override
    public String toString() { return naziv; }


}
