package project;

import project.Actions;
import project.Player;
import project.Unit;

class ActionsRules {
	public static boolean friendlyFire_On = false;
	public static boolean returnFire_On = false; //disable until fully tested
	public static double returnFirePercent = 0.5;
  public static boolean buffDefense_On = false; //not implemented yet;
  public static boolean buff_On = true; //disable buffs here
  public static boolean replenish_HP_ON = false;//not implemented yet
  public static boolean RandomEvents_On = false; //not implemented yet
  public static boolean AI_On = true;
  static void returnFire(Unit src, Unit tgt){
    if((src == null || tgt == null) ||//units don't exist
       (src.getHP() <= 0 || tgt.getHP() <= 0) || //one of the units are dead
       (!src.isTargetInRange(tgt)) ) //check range, updated Dan 2012 May 3
      return;
    int returnAttack = (int)(src.getAttack() * returnFirePercent);
    tgt.setHP(tgt.getHP() - returnAttack);
  }
  static boolean friendlyUnits( Actions a, Unit src, Unit tgt ){
    if(src == null || tgt == null)
      throw new NullPointerException();
    Player srcPlayer = a.playerOfUnit(src);
    if(srcPlayer == null)
      return false; //unit is not apart of either p1 or p2
    //check of srcPlayer has a "tgt" unit
    return srcPlayer.isThisMyUnit(tgt);
  }
}
