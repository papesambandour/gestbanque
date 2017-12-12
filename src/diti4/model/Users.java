package diti4.model;

import diti4.dao.DatabaseHelper;
import diti4.dao.UsersDOA;

/**
 * Created by Pape Ndour on 26/10/2017.
 */
public class Users {
    private int id ;
    private String noms ;
    private String login ;
    private String password;
    private String profile;
    private Agences agence;

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    private int etat ;
    public Users()
    {

    }
    public Users(int id,String noms, String login, String password,String profile)
    {
        this.id = id ;
        this.login =login ;
        this.password = password ;
        this.noms =noms ;
        this.profile = profile ;

    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getProfile() { return profile; }

    public void setProfile(String profile) { this.profile = profile; }

    public Agences getAgence() {
        try {
            return new UsersDOA(DatabaseHelper.getInstance()).getAgenceByIdUser(this.id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setAgence(Agences agence) { this.agence = agence; }

    @Override
    public String toString() {
        return getNoms();
    }
}
