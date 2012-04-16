package project;

import project.Map;
import project.MoveEl;
import project.QueueMove;
import project.Unit;
import project.UnitGround;

public class Actions {
  
  Player p1, p2;
  Input input;
  Map mapRef;
  private static final int[][] moveArray = {  {-1, 1, 0,  0},
                                               {0, 0, 1, -1} };
  //java.util.Queue<MoveEl> myQ = new java.util.Queue<MoveEl>();
  //Check Input, do any actions involved, update Player class
  //Figure out when turn is over by checking player information
  //Calls information on units in the Player passed in
  //Update player locations
  //Update player health
  //Update everything else
  
  public Actions(Player p1, Player p2, Input input, Map mapRef) {
    this.p1 = p1;
    this.p2 = p2;
    this.input = input;
    this.mapRef = mapRef;
  }
  
  //Attack options
    //check if there is a target there (add friendly fire?)
    //fire on target
      //update targets hp
      //hasUnitShot = true
  

  //Move options
    //make sure move is not atop another unit
    //make out paths
    //remove paths that cannot be made from map comparison
    //if any paths remain
      //move unit there and update their data
      //unit that moved, moved = true
  public boolean moveLegal(Unit u, int x, int y){
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
 * Help classes for Actions.moveLegal(Unit u, int x, int y) 
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










