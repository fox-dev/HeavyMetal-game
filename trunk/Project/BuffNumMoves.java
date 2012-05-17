package project;

public class BuffNumMoves extends Buff{
  private static final String DESCRIPTION = "Moves Buff";
  private static final int BUFF_ID = Buff.NUM_MOVES;
  private static final int BUFF_VALUE = 1;
  private static final int DURATION_MOVES = 3;    
  private static final int DURATION_ATK = Integer.MAX_VALUE;
  public BuffNumMoves(){
    super(DESCRIPTION, BUFF_ID, BUFF_VALUE, null, DURATION_MOVES, DURATION_ATK);
  }
  public BuffNumMoves(int newBuffValue){
    super(DESCRIPTION, BUFF_ID, newBuffValue, null, DURATION_MOVES, DURATION_ATK);
  }
  public void applyBuff(){ u.setNumMoves( u.getMoves() + this.buffValue ); }
  public void removeBuff(){ u.setNumMoves( u.getMoves() - this.buffValue ); }
}
