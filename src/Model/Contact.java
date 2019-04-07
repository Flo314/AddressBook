/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author florent losada
 */
public abstract class Contact {
    
    private String nom;
    private String prenom;

    final public String getNom() {
        return nom;
    }

    final public void setNom(String nom) {
        this.nom = nom;
    }

    final public String getPrenom() {
        return prenom;
    }

    final public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
}
