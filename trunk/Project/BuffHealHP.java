package project;

public class BuffHealHP extends Buff{
  private static final int BUFF_ID = Buff.HEAL;
  private static final int BUFF_VALUE = 3;
  private static final int DURATION_MOVES = 0;    
  private static final int DURATION_ATK = 0;
  public static final String DESCRIPTION = "Heal " + BUFF_VALUE + "hp";
  public BuffHealHP(){
    super(DESCRIPTION, BUFF_ID, BUFF_VALUE, null, DURATION_MOVES, DURATION_ATK);
  }
  public BuffHealHP(int newBuffValue){
    super(DESCRIPTION, BUFF_ID, newBuffValue, null, DURATION_MOVES, DURATION_ATK);
  }
  public void applyBuff(){ u.setHP( u.getHP() + this.buffValue ); }
  public void removeBuff(){ }
}