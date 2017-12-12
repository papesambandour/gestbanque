package diti4.dao;

import diti4.model.Client;
import diti4.model.CompteBanque;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ComptBanqueDOA {
    DatabaseHelper db ;
    public ComptBanqueDOA(DatabaseHelper db)
    {
        this.db = db ;
    }
    public int addCompteBanque(Double solde, double decouvert,String typecompte,int idClient,double tauxrenumeration,int idagence,int iduser) throws Exception {
        db.beginTransaction();
        String sql="INSERT INTO comptebanque VALUES(NULL,?,?,?,?,?)";
        String sql2 = "INSERT INTO agence_user_compte values(?,?,?)";
        try {
            db.myPreparedStatement(sql);
            int index[]={1,2,3,4,5};
            Object values[] = {solde,decouvert,typecompte,idClient,tauxrenumeration};
            db.addParam(index,values);
            int res = db.myPrepareExecuteUpdate();
            if(res == 1)
            {
                int idcompte = db.lastInsertId();
                int index2[]={1,2,3};
                Object values2[] = {idagence,iduser,idcompte};
                db.addParam(index2,values2);
                int res2 = db.myPrepareExecuteUpdate();
                if(res2==1)
                {
                    db.myCommit();
                    db.endTransaction();
                    return res ;
                }
                else
                {
                    db.myRollback();
                    db.endTransaction();
                    return 0 ;
                }
            }
            return 0;
        } catch (Exception e) {
            throw e;
        }
    }

   /* public int updateCompteBanque(int id, nomComplet,String login,String password,String profil,int idAgence,int etat) throws Exception {
        String sql="UPDATE users SET  noms = ?,login = ?, password = ?, profil = ?,id_agence = ?,etat=? where id = ?";
        try {
            db.myPreparedStatement(sql);
            int index[]={1,2,3,4,5,6,7};
            Object values[] = {nomComplet,login,password,profil,idAgence,etat,idUser};
            String a = "Mon nom est: "+ sql;
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
*/

    public ArrayList<CompteBanque> getListCompteBanque() throws Exception
    {
        String sql="SELECT * FROM comptebanque";
        try {

            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            ArrayList<CompteBanque> compteBanques = new ArrayList<CompteBanque>();
            while (res.next())
            {
                CompteBanque compteBanque = new CompteBanque();
                compteBanque.setId(res.getInt(1));
                compteBanque.setSolde(res.getDouble(2));
                compteBanque.setDecouvert(res.getDouble(3));
                compteBanque.setTypecompte(res.getString(4));
                compteBanque.setClient(new ClientDOA(DatabaseHelper.getInstance()).getClientById(res.getInt(5)));
                compteBanque.setTauxrenumeration(res.getInt(6));

                compteBanques.add(compteBanque);
            }

            return  compteBanques ;
        } catch (Exception e) {
            throw e;
        }
    }

    public CompteBanque getCompteBanqueById(int id) throws Exception {
        String sql="SELECT * FROM comptebanque where id ='" + id + "'";
        try {
            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            CompteBanque compteBanque = new CompteBanque();
            if(res.first())
            {
                compteBanque.setId(res.getInt(1));
                compteBanque.setSolde(res.getDouble(2));
                compteBanque.setDecouvert(res.getDouble(3));
                compteBanque.setTypecompte(res.getString(4));
                compteBanque.setClient(new ClientDOA(DatabaseHelper.getInstance()).getClientById(res.getInt(5)));
                compteBanque.setTauxrenumeration(res.getInt(6));
            }
            return compteBanque ;
        } catch (Exception e) {
            throw e;
        }
    }

    public Client getClientByIdCompteBanque(int idCompteBanque) throws Exception {
        String sql="SELECT client.*  FROM comptebanque, client WHERE client.id = comptebanque.idclient and comptebanque.id = ? ";
        try {
            db.myPreparedStatement(sql);
            int[] index = {1};
            Object[] value = {idCompteBanque};
            db.addParam(index,value);
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

    public int delCompteBanque(int idCompteBanque) throws  Exception
    {
        String sql ="delete from comptebanque where id = ?";
        try {
            db.myPreparedStatement(sql);
            int index[] = {1};
            Object values[] = {idCompteBanque};
            db.addParam(index,values);
            return  db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e ;
        }
    }


}
