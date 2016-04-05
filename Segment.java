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
    private Color color;
    public static final int DIFERENCIA_GRADOS = 90;
    public static final int LONGITUD_SEGMENTO = 4;

    /**
     * Constructor for objects of class Segment
     */
    public Segment(int posx, int posy, int direccion, Color color)
    {
        this.posx = posx;
        this.posy = posy;
        this.direccion = direccion;
        this.color = color;
    }
    
    /**
     * 
     */
    public void dibujar(Canvas lienzo){
        Pen pen = new Pen(posx, posy, lienzo);
        pen.turnTo(direccion * DIFERENCIA_GRADOS);        
        pen.setColor(color);
        pen.move(LONGITUD_SEGMENTO);
    }
    
    
    /**
     * 
     */
    public void borrar(Canvas lienzo){
        Pen pen = new Pen(posx, posy, lienzo);
        pen.turnTo(direccion * DIFERENCIA_GRADOS);
        pen.setColor(Color.WHITE);
        pen.move(LONGITUD_SEGMENTO);
    }
    
    /**
     * 
     */
    public int getPosicionInicialX(){
        return posx;
    }
    
    /**
     * 
     */
    public int getPosicionInicialY(){
        return posy;
    }
    
    /**
     * 
     */
    public int getPosicionFinalX(){
        int posFinalX = posx;
        if(direccion == 0){
            posFinalX+= LONGITUD_SEGMENTO;
        }
        else if(direccion == 2){
            posFinalX-= LONGITUD_SEGMENTO;
        }
        return posFinalX;
    }
    
    /**
     * 
     */
    public int getPosicionFinalY(){    
        int posFinalY = posy;
        if(direccion == 1){
            posFinalY+= LONGITUD_SEGMENTO;
        }
        else if(direccion == 3){
            posFinalY-= LONGITUD_SEGMENTO;
        }
        return posFinalY;
    }
    
    /**
     * 
     */
    public int getDireccion(){
        return direccion;
    }
    
    /**
     * 
     */
    public boolean colisionaCon(Segment segmento){
        return (segmento.getPosicionFinalX() == posx && segmento.getPosicionFinalY() == posy);
    }
}
