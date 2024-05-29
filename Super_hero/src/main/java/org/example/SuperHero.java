package org.example;

import org.example.subNiveau.Novice;

public class SuperHero extends Person{

    public SuperHero(String name, int strength) {
        super(name, strength);
        nbDoseH = 2;
        niveau = new Novice(this);
    }

    public Person fight(SuperVillain villain){
        if(villain.getStrength() > this.getStrength()){
            int howMuchDose = (int)Math.ceil((double) villain.getStrength() / (double)this.getStrength()) - 1;
            if(howMuchDose > nbDoseH){
                niveau.loseToEnemy(villain);
                nbDoseH = 0;
                return villain;
            } else nbDoseH -= howMuchDose;
        }
        niveau.defeatEnemy(villain);
        return this;
    }

    protected void addNbDoseH(int nbDoseH){
        this.nbDoseH += nbDoseH;
    }

    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }

    @Override
    public String toString(){
        return super.toString() + ", doses: " + nbDoseH + ", reputation: " + niveau;
    }

    private int nbDoseH;
    private Niveau niveau;
}
