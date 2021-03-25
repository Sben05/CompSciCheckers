/**
 * 
 * @author mellis
 * represents a single CompSciChecker piece
 * 
 */
public class CompSciChecker 
{
	private String color;
	private int x;
	private int y;
	
	/**
	 * 
	 * @param color color of the piece
	 * @param x  starting x index of gameboard
	 * @param y	 starting y index of gameboard
	 */
	public CompSciChecker(String color, int x, int y)
	{
		this.color = color;
		this.x = x;
		this.y = y;	
	}
	
	/**
	 * 
	 * @return current x index
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * 
	 * @return current y index
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * 
	 * @return color of piece
	 */
	public String getColor()
	{
		return color;
	}
	
	/**
	 * 
	 * @param newX desired new x index
	 * sets the x index to newX
	 */
	public void setX(int newX)
	{
		x = newX;
	}
	
	/**
	 * 
	 * @param newY desired new y index
	 * sets the y index to newY
	 */
	public void setY(int newY)
	{
		y = newY;
	}

}
