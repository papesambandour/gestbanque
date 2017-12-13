package diti4.dao;

import diti4.model.Agences;
import diti4.model.Users;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersDOA {
    DatabaseHelper db ;
    public UsersDOA(DatabaseHelper db)
    {
        this.db = db ;
    }
    public int addUser(String nomComplet,String login,String password,String profil,int idAgence) throws Exception {
        String sql="INSERT INTO users VALUES(NULL,?,?,?,?,?,?)";
        try {
            db.myPreparedStatement(sql);
            int index[]={1,2,3,4,5,6};
            int etat = 1 ;//etat =0 par defaut
            Object values[] = {nomComplet,login,password,profil,etat,idAgence};
            db.addParam(index,values);
            return db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public int updateUser(int idUser,String nomComplet,String login,String password,String profil,int idAgence,int etat) throws Exception {
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

    public boolean login(String login, String password) throws Exception {
        String sql="SELECT * FROM users WHERE login=? and password=?";
        try {
            db.myPreparedStatement(sql);
            int index[]={1,2};
            String values[] = {login,password};
            db.addParam(index,values);
            ResultSet res = db.myPrepareExecuteQuery();
            return res.next();
        } catch (Exception e) {
            throw e;
        }

    }

    public ArrayList<Users> getListUsers() throws Exception
    {
        String sql="SELECT * FROM users ORDER BY noms";
        try {

            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            ArrayList<Users> users = new ArrayList<Users>();
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

            return  users ;
        } catch (Exception e) {
            throw e;
        }
    }

    public Users getUserById(int id) throws Exception {
        String sql="SELECT * FROM users where id ='" + id + "'";
        try {
            db.myPreparedStatement(sql);
            ResultSet res = db.myPrepareExecuteQuery();
            Users user = new Users();
            if(res.first())
            {
                user.setId(res.getInt(1));
                user.setNoms(res.getString(2));
                user.setLogin(res.getString(3));
                user.setPassword(res.getString(4));
                user.setProfile(res.getString(5));
                user.setEtat(res.getInt(6));
                user.setAgence(new AgencesDOA(DatabaseHelper.getInstance()).getAgenceById(res.getInt(7)));
            }
            return user ;
        } catch (Exception e) {
            throw e;
        }
    }

    public Agences getAgenceByIdUser(int iduser) throws Exception {
        String sql="SELECT agences.*  FROM users, agences WHERE agences.id = users.id_agence and users.id = ? ";
        try {
            db.myPreparedStatement(sql);
            int[] index = {1};
            Object[] value = {iduser};
            db.addParam(index,value);
            ResultSet res = db.myPrepareExecuteQuery();
            Agences agence = new Agences();
            if(res.first())
            {
                agence.setId(res.getInt(1));
                agence.setCode(res.getInt(2));
                agence.setNom(res.getString(3));

                agence.setTel(res.getString(4));
                agence.setAdresse(res.getString(5));
            }
            return agence ;
        } catch (Exception e) {
            throw e;
        }
    }

    public int delUser(int idUser) throws  Exception
    {
        String sql ="delete from users where id = ?";
        try {
            db.myPreparedStatement(sql);
            int index[] = {1};
            Object values[] = {idUser};
            db.addParam(index,values);
          return  db.myPrepareExecuteUpdate();
        } catch (Exception e) {
            throw e ;
        }
    }

    public Users getUserByLoginPwd(String login, String password) throws Exception {
        String sql = "SELECT * FROM users WHERE login= ? AND password = ?";
        try {
            db.myPreparedStatement(sql);
            int[] inex={1,2};
            Object[] values = {login,password};
            db.addParam(inex,values);
            ResultSet res= db.myPrepareExecuteQuery();
            Users u;
            if(res.first())
            {
                u = new Users();
                u.setId(res.getInt(1));
                u.setNoms(res.getString(2));
                u.setLogin(res.getString(3));
                u.setPassword(res.getString(4));
                u.setProfile(res.getString(5));
                u.setEtat(res.getInt(6));
                u.setAgence(new AgencesDOA(DatabaseHelper.getInstance()).getAgenceById(res.getInt(7)));
                return u;
            }
            else
                return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
