package diti4.model;

public class CompteBanque {
    private int id ;
    private double solde;
    private double decouvert ;
    private Client client ;
    private String typecompte ;
    private double tauxrenumeration ;

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

    public CompteBanque(){}
    public CompteBanque(int id,double solde, double decouvert)
    {
        this.id = id ; this.solde = solde ; this.decouvert = decouvert ;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
