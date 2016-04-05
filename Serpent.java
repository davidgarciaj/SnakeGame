import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Serpent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Serpent
{
    private static final int DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES = 90;
    private int anchoLienzo;
    private int altoLienzo;
    private static final Color COLOR_SERPIENTE = Color.BLACK;
    private static final int NUM_SEGMENTOS_INICIALES = 3;
    private ArrayList<Segment> segmentos;

    /**
     * Constructor for objects of class Serpent
     */
    public Serpent(int altoLienzo, int anchoLienzo)
    {
        this.altoLienzo = altoLienzo;
        this.anchoLienzo = anchoLienzo;
        segmentos = new ArrayList<>();
        Random rdn = new Random();
        for(int i = 0; i < segmentos.size(); i++){
            int posx, posy;
            if(segmentos.isEmpty()){
                posx = anchoLienzo/2;
                posy = altoLienzo/2;
            }
            else{
                posx = segmentos.get(i-1).getPosicionFinalX();
                posy = segmentos.get(i-1).getPosicionFinalY();            
            }
            segmentos.add(new Segment(posx,posy,
                rdn.nextInt(4)*DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES,COLOR_SERPIENTE));
        }
    }

    /**
     * 
     */
    public void dibujar(Canvas lienzo){
        for(int i = 0; i < segmentos.size(); i++){
            segmentos.get(i).dibujar(lienzo);
        }
    }
}
