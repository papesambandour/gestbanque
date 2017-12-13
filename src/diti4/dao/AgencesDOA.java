package diti4.dao;

import diti4.model.Agences;
import diti4.model.Users;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AgencesDOA {
    private DatabaseHelper db ;
    public AgencesDOA(DatabaseHelper db)
    {
        this.db = db ;
    }
    public int addAgence(int code,String nom,String adresse,String tel) throws Exception {
        String sql="INSERT INTO agences VALUES(NULL,?,?,?,?)";
        try {
            db.myPreparedStatement(sql);
            int index[]={1,2,3,4};
            Object values[] = {code,nom,tel,adresse};
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
    public int updateAgence(int idagence,int code,String nom,String adresse,String tel) throws Exception {
        String sql = "UPDATE agences SET code = ?, nom = ?, tel = ?, adresse=? WHERE id = ?";
        try {
            db.myPreparedStatement(sql);
            int index[] = {1,2,3,4,5};
            Object values[] = {code,nom,tel,adresse,idagence};
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e ;
        }
    }

    public ArrayList<Agences> getListAgence() throws Exception
    {
        String sql="SELECT * FROM agences ORDER BY nom";
        try {
            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            ArrayList<Agences> agences = new ArrayList<Agences>();
            while (res.next())
            {
                Agences ag = new Agences();
                ag.setId(res.getInt(1));
                ag.setCode(res.getInt(2));
                ag.setNom(res.getString(3));
                ag.setTel(res.getString(4));
                ag.setAdresse(res.getString(5));
                agences.add(ag);
            }

        return  agences ;
        } catch (Exception e) {
            throw e;
        }
    }
    public Agences getAgenceById(int id) throws Exception {
        String sql="SELECT * FROM agences where id ="+id ;
        try {
            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            Agences ag = new Agences();
            if(res.first())
            {
                ag.setId(res.getInt(1));
                ag.setCode(res.getInt(2));
                ag.setNom(res.getString(3));
                ag.setTel(res.getString(4));
                ag.setAdresse(res.getString(5));
            }
            return ag ;
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Users> usersByIdAgence(int idAgence) throws Exception
    {
       String sql = "SELECT * FROM users WHERE id_agence = ?";
        try {
            ArrayList<Users> users = new ArrayList<Users>();
            db.myPreparedStatement(sql);
            int[] index = {1};
            Object[] value = {idAgence};
            db.addParam(index,value);
            ResultSet res = db.myPrepareExecuteQuery();
            while (res.next())
            {
                Users user = new Users();
                user.setId(res.getInt(1));
                user.setNoms(res.getString(2));
                user.setLogin(res.getString(3));
                user.setPassword(res.getString(4));
                user.setProfile(res.getString(5));
                user.setEtat(res.getInt(6));
                user.setAgence(new AgencesDOA(DatabaseHelper.getInstance()).getAgenceById(res.getInt(7)));
                users.add(user);
            }
            res.close();
        return users;
        } catch (Exception e) {
            throw  e;
        }
    }

    public int deleteAgence(int id) throws Exception {
        String sql = "DELETE FROM agences WHERE id = ?";
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

    public void editAgence(Agences ag)
    {
        String sql = "UPDATE agences SET nom =? , addresse=?,tel=? WHERE id = ?"; // on ne doit pas changer le code
        try {
            db.myPreparedStatement(sql);
            int[] index = {1,2,3,4};
            Object[] values = new Object[]{ag.getNom(), ag.getAdresse(), ag.getTel(), ag.getId()};
            db.addParam(index,values);
            db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
