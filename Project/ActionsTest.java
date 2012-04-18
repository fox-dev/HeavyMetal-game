package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionsTest {
  //Default parameters neccassary for constructing a Actions object
  Player p1 = new Player(0,0 ,0, 19);
  Player p2 = new Player(19, 19, 0, 19);
  Unit myUnit = new UnitAir();
  Input in = new Input(p1, p2);
  Map map = new Map(); //nt current default is a 20x20, all filled with zeros
  Actions act = new Actions(p1, p2, in, map);
  //p1.units.get(0).sexLocationX(0);
  //HelloWorld me = new HelloWorld();
  
  @Test
  public void testMoveAirUnit() {
    myUnit.setXY(10,10);
    //cannot move ontop of self
    assertEquals(act.moveUnit(p1.getUnit(0), 0, 0), false);
    assertEquals(act.moveUnit(p1.getUnit(1), 0, 0), false);
    
    //cannot move than moves allow
    Unit myUnit = new UnitAir();
    myUnit.setXY(10,10);
    int maxMoves = myUnit.getMoves();
    //move this units max moves to the right
    assertEquals(act.moveUnit(myUnit, myUnit.getLocationX() + maxMoves, myUnit.getLocationY()), true);
    myUnit.setXY(10,10); //reset loc
    //move unit more than allowed, up 2, right 3
    assertEquals(act.moveUnit(myUnit, myUnit.getLocationX() + 3, myUnit.getLocationY()+ 2 ), false);
  }

}
