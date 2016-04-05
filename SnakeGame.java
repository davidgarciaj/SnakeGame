
/**
 * Write a description of class SnakeGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeGame
{
    // instance variables - replace the example below with your own
    private Snake serpiente;
    private Canvas lienzo;
    private static final int ANCHO = 600;
    private static final int ALTO = 500;

    /**
     * Constructor for objects of class SnakeGame
     */
    public SnakeGame()
    {
        serpiente = new Snake(ANCHO, ALTO);
        lienzo = new Canvas("Juego de la Serpiente", ANCHO, ALTO);
    }

    /**
     * Dibuja la serpiente en nuestro lienzo
     */
    public void drawSnake()
    {
        lienzo.erase();
        serpiente.dibujar(lienzo);
    }
}
