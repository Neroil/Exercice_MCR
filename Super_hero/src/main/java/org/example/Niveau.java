package org.example;

public abstract class Niveau {
    protected SuperHero hero;
    protected int credits;
    protected int nbArrestation;

    protected Niveau(SuperHero hero){
        this.hero = hero;
        this.credits = 0;
        nbArrestation = 0;
    }

    protected Niveau(Niveau oldNiveau){
        this.hero = oldNiveau.hero;
        this.credits = oldNiveau.credits;
        this.nbArrestation = oldNiveau.nbArrestation;
    }

    private void changeReputation(int credits){
        this.credits += credits;
        if(this.credits < 0) this.credits = 0;
        stateChangeCheck();
    }

    protected void defeatEnemy(SuperVillain villain){
        ++nbArrestation;
        changeReputation(villain.getBounty());
        hero.addNbDoseH(getNbDoseH());

    }

    protected void loseToEnemy(SuperVillain villain){
        changeReputation(-villain.getBounty());
    }

    public String toString(){
        return getClass().getSimpleName() + " (arrests: " + nbArrestation + ", credits: " + credits + ")";
    }


    protected abstract void stateChangeCheck();
    protected abstract int getNbDoseH();


}

