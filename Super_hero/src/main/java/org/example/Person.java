package org.example;

public abstract class Person
{
  private final String name;
  private final int strength;

  protected Person(String name, int strength) {
    this.name = name;
    this.strength = strength;
  }

  public String getName() {
    return name;
  }

  public int getStrength() {
    return strength;
  }

  public String toString() {
    return name + ", strength: " + strength;
  }
}
