/** Program Name: Actions.java
 *  Name: Dan Q. Nguyen
 *  Professor: Yang, David
 *  Class: CS4310 Software Engineering I
 *  Assignment Name: Project for class
 *  Description: CONTAINS ONLY 3 PUBLIC METHODS plus a constructor 
 *    all others are private helpers(you won't need them)
 *    
 *    - public boolean fire(Unit src, Unit tgt) 
 *    - public boolean fire(Unit src, int x, int y)
 *      -returns true if "fire" is done and updates appropriate data
 *        to include cleaning up dead units with Player.removeDeadUnits()
 *      -returns false if "fire" is NOT valid and makes no changes
 *      -2 above methods check if a fire action is legal by:
 *        - if one of the units don't exist
 *        - if one of the units are dead
 *        - if the unit attack has already attacked it can't
 *        - if the target unit is in range of the attacking unit
 *            
 *    - public boolean moveUnit(Unit u, int x, int y)
 *      -returns true if "move" is done and updates appropriate data
 *      -returns false if "move" is NOT valid and makes no changes
 *      -method check if a move is legal by:
 *        - unit cannot move if it has already moved
 *        - unit cannot move on top of itself
 *        - unit cannot move on top of any other unit
 *        - unit must not move off of map
 *        - ground unit cannot move on water
 *        
 *    -3 methods listed make checks for legality of move
 *    note: "helper" classes listed at end of code
*/
package project;

public class Actions {
  Player p1, p2;
  Input input;
  Map mapRef;
  private static final int[][] moveArray = {  {-1, 1, 0,  0},
                                               {0, 0, 1, -1} };
  //Figure out when turn is over by checking player information  ??? done here???
  //Calls information on units in the Player passed in  ???
  //Update everything else ??? What else to update???
  
  public Actions(Player p1, Player p2, Input input, Map mapRef) {
    this.p1 = p1;
    this.p2 = p2;
    this.input = input;
    this.mapRef = mapRef;
  }
  
  //Attack options
  //check if fire is Legal returns true if legal, false if ILLEGAL
  //updates target HP, units has shot boolean, and cleans map of dead units
  //(add friendly fire?)
  public boolean fire(Unit src, Unit tgt){
    if(fireLegal(src, tgt)){
      tgt.setHP(tgt.getHP() - src.getAttack());
      src.setHasUnitShot(true);
      p1.removeDeadUnits();
      return true;
    }
    return false;
  }
  public boolean fire(Unit src, int x, int y){//method overload for convenience
    return fire(src, p1.getUnitAt(x, y));
  }
  private boolean fireLegal(Unit src, Unit tgt){
    //units don't exist
    if(src == null || tgt == null)
      return false;
    //one of the units are dead
    if(src.getHP() <= 0 || tgt.getHP() <= 0)
      return false;
    //src has already shot
    if(src.getHasUnitShot())
      return false;
    //check range, may be changed later if range is changed
    //attack is able to be done in adjacent squares to include diagonal
    double x = (double)Math.abs(src.getLocationX() - tgt.getLocationX());
    double y = (double)Math.abs(src.getLocationY() - tgt.getLocationY());
    int distance = (int)(Math.sqrt(x*x+y*y));
    if(distance > 1)
      return false;
    //all is good, fire away
    return true;
  }
  
   /*  returns true if unit was moved successfully
   *   returns false if unit cannot be moved, no data changed in this case */
  public boolean moveUnit(Unit u, int x, int y){
    if(moveLegal(u,x,y)){
      u.setLocationX(x);
      u.setLocationY(y);
      u.moved();
      return true;
    }
    return false;
  }
  private boolean moveLegal(Unit u, int x, int y){
    //if unit has moved already, it cannot move again
    if(u.hasMoved()) 
      return false;
    //unit cannot move ontop of itself, and cannot move ontop of any other unit
    if( p1.getUnitAt(x,y) != null )               //MAKE p1.get... into a STATIC method
      return false;
    //unit cannot move off of the map
    if( !isXY_onMap(x,y) )
      return false;
    //final destination cannot be on water if ground unit
    if( isGroundOnWater(u,x,y) )
      return false;
    
    //Make a move queue and add moves that are legal
    QueueMove q = new QueueMove();
    q.enqueue(new MoveEl(u.getMoves(),u.getLocationX(),u.getLocationY(),null));
    MoveEl moveEl;
    int xTemp, yTemp;
    while(!q.isEmpty()){
      moveEl = q.dequeue();
      for(int i = 0; i < 4; i++){
        //make a move
        xTemp = moveEl.x + moveArray[0][i];
        yTemp = moveEl.y + moveArray[1][i];
        //check if move is done
        if(xTemp == x && yTemp == y)
           return true;
        if( isXY_onMap(xTemp,yTemp) ) //check if intermediate move is on map
          //add move if ( !(a ground unit and its on water) )
          if(!isGroundOnWater(u, xTemp,yTemp))
            if(moveEl.numMovesLeft > 0)  //added by Dan Apr17 1218am
              q.enqueue(new MoveEl(moveEl.numMovesLeft-1, xTemp, yTemp, null));
      }
    }
    //all possible moves have been tried
    return false;
  }
  private boolean isXY_onMap(int x, int y){
    if( x<0 || y<0 || x>mapRef.getX()-1 || y > mapRef.getY()-1 )
      return false;
    return true;
  }
  private boolean isGroundOnWater(Unit u, int x, int y){
    //if ( a ground unit and its on water )
    if((u instanceof UnitGround) && mapRef.getArr(x, y) == 2) //possible syntax/symmatic error instanceof
      return true;     //2 is water according to Map.java (change to a static final WATER in map
    return false;
  }
}
/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 * Helper classes for Actions.moveLegal(Unit u, int x, int y) 
 * 
 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
class QueueMove{
  private MoveEl head, tail;
  public QueueMove() {
    head = tail = null;
  }
  public void enqueue(MoveEl el){
    if(el == null)
      return;
    if(head == null)
      tail = el;
    el.next = head;
    head = el; 
  }
  public MoveEl dequeue(){  //needs refinement
    if(isEmpty())
      return null;
    MoveEl temp;
    if(head == tail){
      temp = head;
      head = tail = null;
    }
    else{
      temp = tail;
      MoveEl tempT = head;
      while(tempT.next != tail)
        tempT = tempT.next;
      tail = tempT;
      tail.next = null;
    }
    return temp;
  }
  public boolean isEmpty(){
    if(head == null)
      return true;
    return false;
  }
  public String toString(){
    String s = "enqueue here->";
    MoveEl temp = head;
    while(temp != null){
      s += temp.toString() + "--";
      temp = temp.next;
    }
    s += "<-dequeue here";
    return s;
  }
}

class MoveEl {
  public final int numMovesLeft;
  public final int x;
  public final int y;
  public MoveEl next;
  
  private MoveEl(){
    this(0, 0, 0, null);
  }
  
  public MoveEl( int n, int locL_R, int locU_D, MoveEl next) {
    numMovesLeft = n;
    x = locL_R;
    y = locU_D; 
    this.next = next;
  }
  public String toString(){
    return "" + numMovesLeft + "[" + x + "]" + "[" + y + "]";
  }
}










