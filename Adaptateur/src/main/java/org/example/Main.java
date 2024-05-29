package org.example;

abstract class Peg{
    private static int total = 0;
    private int id;

    public Peg(){
        id = total++;
    }

    abstract public void insert(String s);

    public String toString(){
        return "Peg #" + id + "> ";
    }
}

class SquarePeg extends Peg{

    @Override
    public void insert(String s) {
        System.out.println(this + "SquarePeg.insert(): " + s);
    }
}

class RoundPeg{
    public void insertIntoHole(String s){
        System.out.println("RoundPeg.insertIntoHole(): " + s);
    }
}


class RoundPegAdapter extends Peg{

    private RoundPeg rp;

    RoundPegAdapter(RoundPeg rp){
        this.rp = rp;
    }

    @Override
    public void insert(String s){
        System.out.print(this);
        rp.insertIntoHole(s);
    }
}


public class Main {
    public static void main(String[] args) {
        Peg squarePeg = new SquarePeg();
        squarePeg.insert("Inserting square peg...");
        RoundPeg roundPeg = new RoundPeg();
        Peg adapter = new RoundPegAdapter(roundPeg);
        adapter.insert("Inserting round peg...");
    }
}

//Resultats
//Peg #0> SquarePeg.insert(): Inserting square peg...
//Peg #1> RoundPeg.insertIntoHole(): Inserting round peg...