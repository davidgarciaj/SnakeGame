import java.util.ArrayList;
import java.util.Random;
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
    private static final int NUM_GALLETAS = 25;
    private ArrayList<Galleta> galletas;
    private Canvas lienzo;
    private static final int ANCHO = 150;
    private static final int ALTO = 125;

    /**
     * Constructor for objects of class SnakeGame
     */
    public SnakeGame()
    {
        galletas = new ArrayList<>();
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

    /**
     * La serpiente se mueve aleatoriamente por toda la pantalla,
     * en caso de que no pueda moverse sale el mensje (Game Over)
     */
    public void animateSnake(){
        while(serpiente.mover(lienzo)){
            for(Galleta galleta : galletas){
                if(serpiente.colisionaCon(galleta.getXPos(),galleta.getYPos())){
                    comeGalleta(galleta);
                } 
            }
            lienzo.wait(100);
        }
        lienzo.drawString("Game Over", (ANCHO/2)-30,(ALTO/2)-5);
    }

    /**
     * Dibuja tantas galletas como indiques
     * 
     * @param num El número de galletas que creemos
     */
    public void introducirGalletas(int num){        
        Random rdn = new Random();
        for(int i = 0; i < num; i++){
            int posx = (rdn.nextInt((ANCHO-(Snake.MARGEN_LIENZO*2))/Segment.LONGITUD_SEGMENTO)
                    *Segment.LONGITUD_SEGMENTO)+Snake.MARGEN_LIENZO;
            int posy = (rdn.nextInt((ALTO-(Snake.MARGEN_LIENZO*2))/Segment.LONGITUD_SEGMENTO)
                    *Segment.LONGITUD_SEGMENTO)+Snake.MARGEN_LIENZO;
            boolean correcto = true;
            if(serpiente.colisionaCon(posx,posy)){
                correcto = false;
            }
            int cont = 0;
            while(cont < galletas.size() && correcto){
                Galleta galleta = galletas.get(cont);
                if(galleta.getXPos() == posx && galleta.getYPos() == posy){
                    correcto = false;
                }
                cont++;
            }
            if(correcto){
                galletas.add(new Galleta(posx,posy));
            }
            else{i--;}
        }
        for(Galleta galleta : galletas){
            galleta.dibujar(lienzo);
        }
    }

    /**
     * Hace que la serpiente coma la galleta al pasar por ella y le añade un segmento
     */
    public void comeGalleta(Galleta galleta){
        galleta.borrar(lienzo);
        boolean come = false;
        while(come){
            come = serpiente.addSegment();
        }
        serpiente.borrar(lienzo);
        serpiente.dibujar(lienzo);
    }

    /**
     * Inicia el juego en el que la serpiente se mueve y come galletas hasta no quedar salida
     */
    public void startGame(){
        drawSnake();
        introducirGalletas(NUM_GALLETAS);
        animateSnake();
    }
}
