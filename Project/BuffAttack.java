package project;

public class BuffAttack extends Buff{
  private static final String DESCRIPTION = "Attack Buff";
  private static final int BUFF_ID = Buff.ATTACK;
  private static final int BUFF_VALUE = 1;
  private static final int DURATION_MOVES = Integer.MAX_VALUE;    
  private static final int DURATION_ATK = 3;
  public BuffAttack(){
    super(DESCRIPTION, BUFF_ID, BUFF_VALUE, null, DURATION_MOVES, DURATION_ATK);
  }
  public BuffAttack(int newBuffValue){
    super(DESCRIPTION, BUFF_ID, newBuffValue, null, DURATION_MOVES, DURATION_ATK);
  }
  public void applyBuff(){ u.setAtk( u.getAttack() + this.buffValue ); }
  public void removeBuff(){ u.setAtk( u.getAttack() - this.buffValue ); }
}
