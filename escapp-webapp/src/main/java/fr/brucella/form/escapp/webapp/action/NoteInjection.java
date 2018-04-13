package fr.brucella.form.escapp.webapp.action;

import org.springframework.stereotype.Component;

@Component
public class NoteInjection {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    String nom;

    public void test(){
        System.out.println("Test note inject");
    }

}
