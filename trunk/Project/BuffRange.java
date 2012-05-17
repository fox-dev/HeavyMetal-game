package project;

public class BuffRange extends Buff{
  private static final String DESCRIPTION = "Extends range of unit";
  private static final int BUFF_ID = Buff.RANGE;
  private static final int BUFF_VALUE = 1;
  private static final int DURATION_MOVES = Integer.MAX_VALUE;    
  private static final int DURATION_ATK = 3;
  public BuffRange(){
    super(DESCRIPTION, BUFF_ID, BUFF_VALUE, null, DURATION_MOVES, DURATION_ATK);
  }
  public BuffRange(int newBuffValue){
    super(DESCRIPTION, BUFF_ID, newBuffValue, null, DURATION_MOVES, DURATION_ATK);
  }
  public void applyBuff(){ u.setRange( u.getRange() + this.buffValue ); }
  public void removeBuff(){ u.setRange( u.getRange() - this.buffValue ); }
}