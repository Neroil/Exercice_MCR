package org.example;

import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;

class Personne {
    String nom;
    int force;

    public Personne(String nom, int force) {
        this.nom = nom;
        this.force = force;
    }

    public boolean isSuperiorOf(Personne p){
        return false;
    }

    public int force() {
        return force;
    }

    public String nom() {
        return nom;
    }
}

class Commandant extends Personne{

    final LinkedList<Personne> subordonnes = new LinkedList<>();
    int influence;

    public Commandant(String nom, int force, int influence) {
        super(nom, force);
        this.influence = influence;
    }

    public void enroler(Personne p) throws RuntimeException {
        if(p.isSuperiorOf(this)) throw new RuntimeException("Impossible d'enrôler " + p.nom() + " avec " + this.nom() + " car c'est l'un de ses supérieurs");
        else subordonnes.add(p);
    }

    @Override
    public boolean isSuperiorOf(Personne p) {
        if (this == p) {
            return true;
        }

        for (Personne sub : subordonnes) {
            if (sub == p || sub.isSuperiorOf(p)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int force(){
        int forceTot = super.force();
        for(Personne sub : subordonnes){
            forceTot += sub.force() * influence;
        }

        return forceTot;
    }

}

public class Main {
    public static void main(String... args) throws RuntimeException {
        Personne
                legolas = new Personne("Legolas", 5),
                gimli = new Personne("Gimli", 4),
                sam = new Personne("Sam", 1);

        Commandant gandalf = new Commandant("Gandalf", 10, 3),
                aragorn = new Commandant("Aragorn", 7, 2),
                frodo = new Commandant("Frodo", 2, 1);


        frodo.enroler(sam);
        aragorn.enroler(frodo);
        gandalf.enroler(gimli);
        gandalf.enroler(legolas);
        gandalf.enroler(aragorn);

        try {
            frodo.enroler(gandalf);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            // Impossible d'enrôler Gandalf avec Frodo car c'est l'un de ses supérieurs
        }

        System.out.println("Force totale du groupe de Gandalf: " + gandalf.force());
        // Force totale du groupe de Gandalf: 76
    }
}