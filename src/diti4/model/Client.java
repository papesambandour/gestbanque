package diti4.model;

import java.util.ArrayList;

public class Client {

    private int id;
    private String noms;
    private String adresse;
    private String tel;
    private String email;
    private ArrayList<CompteBanque> compteBanques ;
    public Client(){}

    public Client(int id,String noms,String adresse,String tel, String email)
    {
        this.id = id ; this.noms = noms; this.adresse = adresse ; this.tel = tel ; this.email = email ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<CompteBanque> getCompteBanques() {
        return compteBanques;
    }

    public void setCompteBanques(ArrayList<CompteBanque> compteBanques) {
        this.compteBanques = compteBanques;
    }
}
