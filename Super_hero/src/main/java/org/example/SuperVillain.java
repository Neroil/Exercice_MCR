package org.example;

class SuperVillain extends Person
{
  private int bounty;

  public SuperVillain(String name, int strength, int bounty) {
    super(name, strength);
    this.bounty = bounty;

  }

  public int getBounty() {
    return bounty;
  }
  
  public String toString() {
    return super.toString() + ", bounty: " + bounty;
  }
}
