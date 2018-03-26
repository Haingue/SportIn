package fr.univ_lille1.iutinfo.metier;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hainguef on 23/03/18.
 */

public class UserDao {

    private int id;
    private String nom, alias, email, password;
    //private String prenom;

    public UserDao() {
    }

    public UserDao(int id, String nom, /*String prenom,*/ String alias, String email, String password) {
        this.id = id;
        this.nom = nom;
        //this.prenom = prenom;
        this.alias = alias;
        this.email = email;
        this.password = password;
    }
    public UserDao(String nom,String pwd){

    }

    public static UserDao initUserDao(JSONObject response) throws JSONException {
        UserDao tmp = new UserDao();
        tmp.setId(response.getInt("id"));
        //tmp.setAlias(response.getString("alias"));
        tmp.setNom(response.getString("name"));
        //tmp.setPrenom(response.getString("prenom"));
        //tmp.setEmail(response.getString("email"));
        //tmp.setPassword(response.getString("password"));
        System.out.println(tmp.toString());
        return tmp;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    /*public String getPrenom() {
        return prenom;
    }*/

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /*public void setPrenom(String prenom) {
        this.prenom = prenom;
    }*/

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "UserDao{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
