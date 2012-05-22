package project;

import project.Unit;

public abstract class Buff {
  protected String description;
  protected int buffID;
  protected int buffValue;
  protected Unit u;
  protected int durationMoves;
  protected int durationAtk;
  
  //BUFF ID'S
  public static final int ATTACK = 100;
  public static final int NUM_MOVES = 101;
  public static final int HEAL = 102;
  public static final int RANGE = 103;
  public static final int POINTS = 104;
  public static final int MAX_NUM_BUFFS = 20;
  
  public Buff(String description, int buffID, int buffValue, Unit u, int durationMoves, int durationAtk) {
    this.description = description;
    this.buffID = buffID;
    this.buffValue = buffValue;
    this.u = u;
    this.durationMoves = durationMoves;
    this.durationAtk = durationAtk; 
  }
  public abstract void applyBuff();
  public abstract void removeBuff();
  public int getBuffID() { return buffID; }
  public void iAttacked() { durationAtk--; }
  public void iMoved() { durationMoves--; }
  public boolean isDurationOver() {
    if(durationMoves <= 0 || durationAtk <= 0)
      return true;
    return false;
  }
  public void setUnit(Unit u) { this.u = u; }
  public String getDescription() { return description; }
}
