import java.awt.Color;
/**
 * Write a description of class Galleta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Galleta
{
    public static int numeroDeGalletas = 0;
    private int xPos;
    private int yPos;
    private static final int RADIO = 4;

    /**
     * Constructor for objects of class Galleta
     */
    public Galleta(int xPos, int yPos)
    {
        numeroDeGalletas++;
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
    public void dibujar(Canvas lienzo)
    {
        lienzo.setForegroundColor(Color.ORANGE);
        lienzo.fillCircle(xPos -RADIO,yPos -RADIO, (RADIO*2));
    }

    /**
     * 
     */
    public void borrar(Canvas lienzo)
    {
        lienzo.setForegroundColor(lienzo.getBackgroundColor());
        lienzo.fillCircle(xPos -RADIO,yPos -RADIO, (RADIO*2));
    }
}
