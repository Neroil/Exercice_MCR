package org.example;

interface AbstractFactory{
    Chair createChair();
    Table createTable();
    Sofa createSofa();
}

interface AbstractProduct{
    String getDescription();
}
interface Chair extends AbstractProduct {

}

interface Table extends AbstractProduct {

}

interface Sofa extends AbstractProduct {

}

class ModernChair implements Chair{

    @Override
    public String getDescription() {
        return "Modern Chair";
    }
}

class ModernTable implements Table{
    @Override
    public String getDescription() {
        return "Modern Table";
    }
}

class ModernSofa implements Sofa{

    @Override
    public String getDescription() {
        return "Modern Sofa";
    }
}

class VintageChair implements Chair{
    @Override
    public String getDescription() {
        return "Vintage Chair";
    }
}

class VintageTable implements Table{
    @Override
    public String getDescription() {
        return "Vintage Table";
    }
}

class VintageSofa implements Sofa{
    @Override
    public String getDescription() {
        return "Vintage Sofa";
    }
}

class ModernFurnitureFactory implements AbstractFactory{

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

class VintageFurnitureFactory implements AbstractFactory{

    @Override
    public Chair createChair() {
        return new VintageChair();
    }

    @Override
    public Table createTable() {
        return new VintageTable();
    }

    @Override
    public Sofa createSofa() {
        return new VintageSofa();
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating modern furniture
        AbstractFactory modernFurnitureFactory = new ModernFurnitureFactory();
        System.out.println("Creating modern furniture...");
        Chair modernChair = modernFurnitureFactory.createChair();
        Table modernTable = modernFurnitureFactory.createTable();
        Sofa modernSofa = modernFurnitureFactory.createSofa();

        System.out.println("Chair: " + modernChair.getDescription());
        System.out.println("Table: " + modernTable.getDescription());
        System.out.println("Sofa: " + modernSofa.getDescription());

        System.out.println();

        // Creating vintage furniture
        AbstractFactory vintageFurnitureFactory = new VintageFurnitureFactory();
        System.out.println("Creating vintage furniture...");
        Chair vintageChair = vintageFurnitureFactory.createChair();
        Table vintageTable = vintageFurnitureFactory.createTable();
        Sofa vintageSofa = vintageFurnitureFactory.createSofa();

        System.out.println("Chair: " + vintageChair.getDescription());
        System.out.println("Table: " + vintageTable.getDescription());
        System.out.println("Sofa: " + vintageSofa.getDescription());
    }
}