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
        pen.turnTo(direccion);        
        pen.setColor(color);
        pen.move(LONGITUD_SEGMENTO);
    }
    
    
    /**
     * 
     */
    public void borrar(Canvas lienzo){
        Pen pen = new Pen(posx, posy, lienzo);
        pen.turnTo(direccion);
        pen.setColor(lienzo.getBackgroundColor());
        pen.move(LONGITUD_SEGMENTO);
    }
    
    /**
     * Devuelve la posicion inicial en el eje x
     */
    public int getPosicionInicialX(){
        return posx;
    }
    
    /**
     * Devuelve la posicion inicial en el eje y
     */
    public int getPosicionInicialY(){
        return posy;
    }
    
    /**
     * Devuelve la posicion final en el eje x
     */
    public int getPosicionFinalX(){
        int posFinalX = posx;
        if(direccion == 0){
            posFinalX+= LONGITUD_SEGMENTO;
        }
        else if(direccion == 180){
            posFinalX-= LONGITUD_SEGMENTO;
        }
        return posFinalX;
    }
    
    /**
     * Devuelve la posicion final en el eje y
     */
    public int getPosicionFinalY(){    
        int posFinalY = posy;
        if(direccion == 90){
            posFinalY+= LONGITUD_SEGMENTO;
        }
        else if(direccion == 270){
            posFinalY-= LONGITUD_SEGMENTO;
        }
        return posFinalY;
    }
    
    /**
     * Devuelve direccion del segmento
     */
    public int getDireccion(){
        return direccion;
    }
    
    /**
     * Devuelve true si el segmento pasado como parametro colisiona con el segmento sobre el
     * que se invoca el metodo, teniendo en cuenta que el pasado como parametro se supone que
     * es un nuevo segmento de la serpiente; false en otro caso 
     */
    public boolean colisiona(Segment segmento){
        return (segmento.getPosicionFinalX() == posx && segmento.getPosicionFinalY() == posy);
    }
}
