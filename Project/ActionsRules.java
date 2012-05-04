package project;

class ActionsRules {
   boolean friendlyFire_On;
   boolean returnFire_On = false; //disable until fully tested
   double returnFirePercent = 0.5;
   boolean buffDefense_On;
   boolean buffAttack_On;
   boolean replenish_HP_ON;
   boolean RandomEvents_On;
   void returnFire(Unit src, Unit tgt){
     if((src == null || tgt == null) ||//units don't exist
        (src.getHP() <= 0 || tgt.getHP() <= 0) || //one of the units are dead
        (!src.isTargetInRange(tgt)) ) //check range, updated Dan 2012 May 3
       return;
     int returnAttack = (int)(src.getAttack() * returnFirePercent);
     tgt.setHP(tgt.getHP() - returnAttack);
     
   }
}
