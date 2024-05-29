package org.example;

public class Simulation
{
    private static void encounter(SuperHero hero, SuperVillain villain) {
        System.out.print(hero.getName() + " fights " + villain.getName() + "... ");
        Person winner = hero.fight(villain);
        System.out.println(winner.getName() + " wins !");
        System.out.println(hero);
    }

    public static void main(String[] args)
    {
        SuperHero
                venom = new SuperHero("Venom", 10);

        SuperVillain
                lizard = new SuperVillain("Lizard", 5, 15000),
                vulture = new SuperVillain("Vulture", 17, 20000),
                greenGoblin = new SuperVillain("Green Goblin", 23, 25000),
                drOctopus = new SuperVillain("Dr Octopus", 27, 30000);

        System.out.println("-- 1 --");
        System.out.println(venom);
        System.out.println(lizard);
        System.out.println(vulture);
        System.out.println(greenGoblin);
        System.out.println(drOctopus);

        System.out.println("-- 2 --");
        encounter(venom, greenGoblin);

        System.out.println("-- 3 --");
        encounter(venom, drOctopus);

        System.out.println("-- 4 --");
        encounter(venom, lizard);

        System.out.println("-- 5 --");
        encounter(venom, vulture);

        System.out.println("-- 6 --");
        encounter(venom, drOctopus);
    }
}
