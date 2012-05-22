package project;

public class BuffPoints extends Buff {
  private static final int BUFF_ID = Buff.POINTS;
  private static final int BUFF_VALUE = 125;
  private static final int DURATION_MOVES = 0;    
  private static final int DURATION_ATK = 0;
  public static final String DESCRIPTION = "Points +" + BUFF_VALUE;
  public BuffPoints(){
    super(DESCRIPTION, BUFF_ID, BUFF_VALUE, null, DURATION_MOVES, DURATION_ATK);
  }
  public BuffPoints(int newBuffValue){
    super(DESCRIPTION, BUFF_ID, newBuffValue, null, DURATION_MOVES, DURATION_ATK);
  }
  public void applyBuff(){ }
  public void removeBuff(){ }
  public void applyPointBuff(UnitBase u){ u.setPoint(u.getPoint() + buffValue); }
}
