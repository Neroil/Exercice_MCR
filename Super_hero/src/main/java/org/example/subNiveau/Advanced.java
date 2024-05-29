package org.example.subNiveau;

import org.example.Niveau;
import org.example.SuperHero;

public class Advanced extends Niveau {
    protected Advanced(Niveau oldNiveau) {
        super(oldNiveau);
    }

    @Override
    protected void stateChangeCheck() {
        if(credits >= 50000){
            hero.setNiveau(new Expert(this));
        } else if (credits < 10000){
            hero.setNiveau(new Novice(this));
        }
    }

    @Override
    protected int getNbDoseH() {
        return 2;
    }
}
