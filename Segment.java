import java.awt.Color;
/**
 * Write a description of class Segment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Segment
{
    private int posx;
    private int posy;
    private int direccion;
    public static final int DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES = 90;
    public static final int LONGITUD_SEGMENTO = 4;
    private static final Color color = Color.BLACK;

    /**
     * Constructor for objects of class Segment
     */
    public Segment(int posx, int posy, int direccion)
    {
        this.posx = posx;
        this.posy = posy;
        this.direccion = direccion;
    }
    
    /**
     * 
     */
    public void draw(Canvas lienzo){
        
    }
}
