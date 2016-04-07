import java.awt.Color;
/**
 * Write a description of class Galleta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galleta
{
    public static int NUM_GALLETAS = 0;
    private int xPos;
    private int yPos;
    private static final int RADIO = 2;

    /**
     * Constructor for objects of class Galleta
     */
    public Galleta(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;        
    }
    
    /**
     * 
     */
    public int getXPos(){return xPos;}
    
    /**
     * 
     */
    public int getYPos(){return yPos;}

    /**
     * 
     */
    public void dibujarGalleta(Canvas lienzo)
    {
        lienzo.setForegroundColor(Color.ORANGE);
        lienzo.fillCircle(xPos -RADIO,yPos -RADIO, (RADIO*2));
    }
}
