package org.example.subNiveau;

import org.example.Niveau;
import org.example.SuperHero;

public class Expert extends Niveau {
    protected Expert(Niveau oldNiveau) {
        super(oldNiveau);
    }

    @Override
    protected void stateChangeCheck() {
        if (credits < 50000 && nbArrestation < 10){
            hero.setNiveau(new Advanced(this));
        }
    }

    @Override
    protected int getNbDoseH() {
        return 5;
    }

}
