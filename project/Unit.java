package project;

public interface Unit {
	//Basic information for the units
	
	public int getMoves();
	public int getHP();
	public void setHP(int hp);
	public boolean moved();
	public int getAttack();
	public void setLocationX(int x);
	public void setLocationY(int y);
	public int getLocationX();
	public int getLocationY();
}
