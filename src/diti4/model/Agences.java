package diti4.model;
import diti4.dao.AgencesDOA;
import diti4.dao.DatabaseHelper;

import java.util.ArrayList;

public class Agences {
    private int id;
    private String nom;
    private int code ;
    private String adresse;
    private String tel;
    private ArrayList<Users> users;
    public Agences(){users = new ArrayList<>();}

    public Agences(int id ,String nom, int code, String adreese, String tel)
    {
        this.id = id;
        this.adresse = adreese; this.code = code; this.nom = nom;
        this.tel = tel;
        try {
            this.users = new AgencesDOA(DatabaseHelper.getInstance()).usersByIdAgence(this.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public ArrayList<Users> getUsers() throws Exception {
        try {
            return new AgencesDOA(DatabaseHelper.getInstance()).usersByIdAgence(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }


    public String toString(){
        return getNom();
    }
}
