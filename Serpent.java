import java.awt.Color;
import java.util.ArrayList;

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
    }
}
