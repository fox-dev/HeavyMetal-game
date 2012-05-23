package project;

import project.Actions;
import project.Map;
import project.Player;
import project.Unit;

public class Player_AI extends Player{
  private Actions actions;
  private Map map;
  private int[][] buffArray;
  private Player enemyPlayer;
 // Boolean for explosions with the AI since it cannot select units like a real player
  protected static boolean hasJustMovedUnits;
  java.util.ArrayList<Unit> tgtFocusList;
  public Player_AI(){
    super(Player.P_AI);    
  }
  //requirement that Player_AI get "REBUILT"
  public void seed(Actions actions, Map map, int[][] buffArray, Player enemyPlayer){
    this.actions = actions;
    this.map = map;
    this.buffArray = buffArray;
    tgtFocusList = new java.util.ArrayList<Unit>();
    this.enemyPlayer = enemyPlayer;
  }
  
  //moves and attacks closest enemy unit if possible
  void moveAttackAll(){
	setHasJustMovedUnitsTrue();
    for(Unit u: units){
      moveAndAttack(u);
      //.35 second delay between moving units
			try	{
				Thread.sleep(350); // do nothing for 350 miliseconds
			}	catch(InterruptedException ex) {
				ex.printStackTrace();
			}
    }
    setHasJustMovedUnitsFalse();
  }
  private void moveAndAttack(Unit u){
    Unit tgt = findClosestEnemy(u);
    // Fixes the null pointer exception when Player 1 explodes and the AI can't find units
    if (tgt != null) {
    	moveUnitTo(u, tgt);
    	actions.fire(u, tgt);
    }
  }
  //moves unit to closest square by tgt
  private void moveUnitTo(Unit src, Unit tgt){
    int [][] tempArr = actions.makeNewMovementDisplay(src);
    //cycle through tempArr and find the movable location closest to tgt
    int tgtX = tgt.getLocationX();
    int tgtY = tgt.getLocationY();
    int xDest = -1, yDest = -1;
    int xTemp, yTemp;
    double rangeDest = Double.MAX_VALUE, tempRange;
    for(int x = 0; x < tempArr.length; x++){
      for(int y = 0; y < tempArr[x].length; y++){
        if(tempArr[x][y] == Actions.LEGAL_MOVE_HERE){
          xTemp = x - tgtX;
          yTemp = y - tgtY;
          tempRange = Math.sqrt(xTemp*xTemp+yTemp*yTemp);
          if(tempRange < rangeDest){
            xDest = x;
            yDest = y;
            rangeDest = tempRange;
          }
        }
      }
    }
  if(xDest >= 0 && yDest >= 0){
    	actions.moveUnit(src, xDest, yDest);
       setHasJustMovedUnitsTrue();
  }
  }
  
  private Unit findClosestEnemy(Unit src){
    if(src == null || enemyPlayer == null)
      return null;
    int x, y;
    double minRange = Double.MAX_VALUE, tempRange;
    Unit tgt = null;
    //cycle through enemyPlayer's units and find the closest one
    for(Unit u: (enemyPlayer.units) ){
      x = src.getLocationX() - u.getLocationX();
      y = src.getLocationY() - u.getLocationY();
      tempRange = Math.sqrt(x*x+y*y);
      if(tempRange < minRange){
        minRange = tempRange;
        tgt = u;
      }
    }
    return tgt;
  }
  
  // Set moved units true
  public void setHasJustMovedUnitsTrue(){
    this.setAImovedTrue();
  }
  
  // Set moved units false
  public void setHasJustMovedUnitsFalse(){
	  this.setAImovedFalse();
   }
  
  //NOT IMPLEMENTED YET
  void moveToClosestBuff(){
    if(buffArray == null)
      return;
  }
  //NOT IMPLEMENTED YET
  double rangeToClosestBuff(){
    if(buffArray == null)
      return -1.0;
    return 0;
  }  
}
