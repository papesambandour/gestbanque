package diti4.model;

import diti4.dao.ClientDOA;
import diti4.dao.ComptBanqueDOA;
import diti4.dao.DatabaseHelper;

public class CompteBanque {
    private int id ;
    private double solde;
    private double decouvert ;
    private String numeroCompte ;
    private Client client ;
    private String typecompte ;
    private double tauxrenumeration ;
    private Users user;
    private Agences agence ;

    public CompteBanque(){}
    public CompteBanque(int id,double solde, double decouvert)
    {
        this.id = id ; this.solde = solde ; this.decouvert = decouvert ;
    }
    public double getTauxrenumeration() {
        return tauxrenumeration;
    }

    public void setTauxrenumeration(double tauxrenumeration) {
        this.tauxrenumeration = tauxrenumeration;
    }

    public String getTypecompte() {
        return typecompte;
    }

    public void setTypecompte(String typecompte) {
        this.typecompte = typecompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public Client getClient() throws Exception {
        try {
            return new ComptBanqueDOA(DatabaseHelper.getInstance()).getClientByIdCompteBanque(this.id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Agences getAgence() {
        return agence;
    }

    public void setAgence(Agences agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
            return getNumeroCompte();
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }
}
