/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author florent losada
 */
public class Personne extends Contact{
    
    private ArrayList<Telephone> phones = new ArrayList<>();
    private String adresse;
    private String cpt;
    private String ville;
    private ArrayList<Email> emails = new ArrayList();
    
    public Personne(String nom, String prenom,String adresse, String cpt, String ville) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.adresse = adresse;
        this.cpt = cpt;
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCpt() {
        return cpt;
    }

    public void setCpt(String cpt) {
        this.cpt = cpt;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public void addPhone(Telephone tel){
        this.phones.add(tel);
    }
    
    public void addEmail(Email mail){
        this.emails.add(mail);
    }
    
    public void removePhone(Telephone tel){
        this.phones.remove(tel);
    }
    
    public void removeEmail(Email mail){
        this.emails.remove(mail);
    }

    public ArrayList getPhones() {
        return phones;
    }

    public void setPhones(ArrayList phones) {
        this.phones = phones;
    }

    public ArrayList getEmails() {
        return emails;
    }

    public void setEmails(ArrayList emails) {
        this.emails = emails;
    }
    
    @Override
    public String toString() {
        return "Personne [nom : " + this.getNom() 
                + ", prenom : " + this.getPrenom()
                + ", telephones : " + this.getPhones()
                + ", adresse : " + this.getAdresse() 
                + ", code : " + this.getCpt()
                + ", ville : "+ this.getVille()
                + ", emails : "+ this.getEmails() + "]";
    }
    
}
