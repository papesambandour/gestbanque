package diti4.dao;

import diti4.model.Client;
import diti4.model.CompteBanque;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDOA {
    private DatabaseHelper db ;
    public ClientDOA(DatabaseHelper db)
    {
        this.db = db ;
    }
    public int addClient(String noms,String adresse,String tel, String email) throws Exception {
        String sql="INSERT INTO client VALUES(NULL,?,?,?,?)";
        try {
            db.myPreparedStatement(sql);
            int index[]={1,2,3,4};
            Object values[] = {noms,adresse,tel,email};
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
    public int updateClient(int id,String noms,String adresse,String tel, String email) throws Exception {
        String sql = "UPDATE client SET noms = ?, adresse = ?, tel = ?, email=? WHERE id = ?";
        try {
            db.myPreparedStatement(sql);
            int index[] = {1,2,3,4,5};
            Object values[] = {noms,adresse,tel,email,id};
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e ;
        }
    }

    public ArrayList<Client> getListClient() throws Exception
    {
        String sql="SELECT * FROM client";
        try {
            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            ArrayList<Client> clients = new ArrayList<Client>();
            while (res.next())
            {
                Client client = new Client();
                client.setId(res.getInt(1));
                client.setNoms(res.getString(2));
                client.setAdresse(res.getString(3));
                client.setTel(res.getString(4));
                client.setEmail(res.getString(5));
                clients.add(client);
            }

            return  clients ;
        } catch (Exception e) {
            throw e;
        }
    }
    public Client getClientById(int id) throws Exception {
        String sql="SELECT * FROM client where id ='"+id +"'";
        try {
            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            Client client = new Client();
            if(res.first())
            {
                client.setId(res.getInt(1));
                client.setNoms(res.getString(2));
                client.setAdresse(res.getString(3));
                client.setTel(res.getString(4));
                client.setEmail(res.getString(5));
            }
            return client ;
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<CompteBanque> compteBanqueByIdClient(int idClient) throws Exception
    {
        String sql = "SELECT * FROM comptebanque WHERE idclient = ?";
        try {
            ArrayList<CompteBanque> compteBanques = new ArrayList<CompteBanque>();
            db.myPreparedStatement(sql);
            int[] index = {1};
            Object[] value = {idClient};
            db.addParam(index,value);
            ResultSet res = db.myPrepareExecuteQuery();
            while (res.next())
            {
                CompteBanque compteBanque = new CompteBanque();
                compteBanque.setId(res.getInt(1));
                compteBanque.setSolde(res.getDouble(2));
                compteBanque.setDecouvert(res.getDouble(3));
                compteBanque.setTypecompte(res.getString(4));
                compteBanque.setClient(new ClientDOA(DatabaseHelper.getInstance()).getClientById(res.getInt(5)));
                compteBanque.setTauxrenumeration(res.getDouble(6));
                compteBanque.setNumeroCompte(res.getString(7));
                compteBanques.add(compteBanque);
            }
            res.close();
            return compteBanques;
        } catch (Exception e) {
            throw  e;
        }
    }

    public int deleteClient(int id) throws Exception {
        String sql = "DELETE FROM client WHERE id = ?";
        try {
            db.myPreparedStatement(sql);
            int[] index = {1};
            Object[] values = {id};
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e ;
        }
    }

   /* public void editAgence(Client ag)
    {
        String sql = "UPDATE client SET noms = ?, adresse = ?, tel = ?, email=? WHERE id = ?"; // on ne doit pas changer le code
        try {
            db.myPreparedStatement(sql);
            int[] index = {1,2,3,4};
            Object[] values = new Object[]{ag.getNom(), ag.getAdresse(), ag.getTel(), ag.getId()};
            db.addParam(index,values);
            db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
