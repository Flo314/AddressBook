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
public class TestModel {
    
     public static void main(String[] args) {
         
        Personne personne1 = new Personne("Bob", "Marley", "28 rue gueu", "31200", "toulouse");
        
        Telephone tel1 = new Telephone("0606060606");
        Telephone tel2 = new Telephone("0505050505");
        Telephone tel3 = new Telephone("0303030303");
        
        personne1.addPhone(tel1);
        personne1.addPhone(tel2);
        personne1.addPhone(tel3);
        
        Email mail1 = new Email("bob@orange.fr");
        Email mail2 = new Email("bobo@hotmail.fr");
        
        personne1.addEmail(mail1);
        personne1.addEmail(mail2);
        
        System.out.println(personne1.toString());
        
        Personne personne2 = new Personne("Maria", "BObo", "19 rue pardal", "31700", "blagnac");
        
        Telephone tel11 = new Telephone("0101010101");
        Telephone tel21 = new Telephone("0202020202");
        Telephone tel31 = new Telephone("0303030303");
        
        personne2.addPhone(tel11);
        personne2.addPhone(tel21);
        personne2.addPhone(tel31);
        
        Email mail11 = new Email("m.bobo@gmail.fr");
        Email mail21 = new Email("bobomaria@hotmail.fr");
        
        personne2.addEmail(mail11);
        personne2.addEmail(mail21);
        
        System.out.println(personne2.toString());
        
        
     }
}
