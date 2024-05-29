package org.example.subNiveau;

import org.example.Niveau;
import org.example.SuperHero;

public class Novice extends Niveau {

    protected Novice(Niveau oldNiveau) {
        super(oldNiveau);
    }

    public Novice(SuperHero hero) {
        super(hero);
    }

    @Override
    protected void stateChangeCheck() {
        if(credits >= 10000){
            hero.setNiveau(new Advanced(this));
        }
    }

    @Override
    protected int getNbDoseH() {
        return 1;
    }


}
