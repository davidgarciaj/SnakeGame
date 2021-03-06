import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    private int anchoLienzo;
    private int altoLienzo;
    private static final int NUMERO_SEGMENTOS_INICIALES = 4;
    private static final Color color = Color.BLACK;
    private ArrayList<Segment> segmentos;   
    private static final int DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES = 90;
    public static final int MARGEN_LIENZO = 10;
    private static final int TAMANO_CABEZA = 8;

    /*
     * Constructor de la clase Snake
     */
    public Snake(int anchoLienzo, int altoLienzo)
    {
        this.anchoLienzo = anchoLienzo;
        this.altoLienzo = altoLienzo;
        segmentos = new ArrayList<>();
        Random rdn = new Random();
        int posx = (rdn.nextInt((anchoLienzo-(Snake.MARGEN_LIENZO*2))/Segment.LONGITUD_SEGMENTO)
                    *Segment.LONGITUD_SEGMENTO)+Snake.MARGEN_LIENZO;
        int posy = (rdn.nextInt((altoLienzo-(Snake.MARGEN_LIENZO*2))/Segment.LONGITUD_SEGMENTO)
                    *Segment.LONGITUD_SEGMENTO)+Snake.MARGEN_LIENZO;
        segmentos.add(new Segment(posx,posy,rdn.nextInt(4),color));
        for (int i = 1; i < NUMERO_SEGMENTOS_INICIALES; i++) {
            addSegment();
        }
    }

    /*
     * Dibuja la serpiente en el lienzo dado
     */
    public void dibujar(Canvas lienzo)
    {
        for (Segment segmento : segmentos) {
            segmento.dibujar(lienzo);
        }
        lienzo.setForegroundColor(color);
        Segment ultimoSegmento = segmentos.get(segmentos.size()-1);
        lienzo.fillCircle(ultimoSegmento.getPosicionFinalX()-(TAMANO_CABEZA/2),ultimoSegmento.getPosicionFinalY()-(TAMANO_CABEZA/2), TAMANO_CABEZA);
    }

    /*
     * Borra la serpiente del lienzo dado
     */
    public void borrar(Canvas lienzo)
    {  
        lienzo.setForegroundColor(lienzo.getBackgroundColor());
        Segment ultimoSegmento = segmentos.get(segmentos.size()-1);
        lienzo.fillCircle(ultimoSegmento.getPosicionFinalX()-(TAMANO_CABEZA/2),ultimoSegmento.getPosicionFinalY()-(TAMANO_CABEZA/2), TAMANO_CABEZA); 
        for (Segment segmento : segmentos) {
            segmento.borrar(lienzo);
        }          
    }

    /*
     * Adiciona un segmento aleatorio a la serpiente. Devuelve true en caso de que
     * haya sido capaz de añadir un nuevo segmento y false en otro caso.
     */
    public boolean addSegment() 
    {
        boolean segmentoAdicionado = false;

        Random aleatorio = new Random();
        ArrayList<Integer> direcciones = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            direcciones.add(i * DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES);
        }

        //Calculamos las coordenadas de inicio del segmento: si no había
        //segmentos, lo ubicamos en una posicion aleatoria; si los había, al final del ultimo
        //segmento
        int posicionOrigenX = aleatorio.nextInt(anchoLienzo - (2 * MARGEN_LIENZO)) 
            + MARGEN_LIENZO + Segment.LONGITUD_SEGMENTO; 
        int posicionOrigenY = aleatorio.nextInt(altoLienzo - (2 * MARGEN_LIENZO)) 
            + MARGEN_LIENZO + Segment.LONGITUD_SEGMENTO; 
        if (segmentos.size() != 0) {
            posicionOrigenX = segmentos.get(segmentos.size() - 1).getPosicionFinalX();
            posicionOrigenY = segmentos.get(segmentos.size() - 1).getPosicionFinalY();
        }

        //Probamos todos los segmentos posibles hasta que demos con uno valido
        //o hayamos probado los posibles 4 nuevos segmentos
        Segment posibleNuevoSegmento = null;
        boolean encontradoNuevoSegmentoValido = false;
        while (!direcciones.isEmpty() && !encontradoNuevoSegmentoValido) {
            int direccion = direcciones.remove(aleatorio.nextInt(direcciones.size()));
            posibleNuevoSegmento = new Segment(posicionOrigenX, posicionOrigenY, direccion, color);
            encontradoNuevoSegmentoValido = esSegmentoValido(posibleNuevoSegmento);                             
        }

        //Si hemos encontrado un segmento valido lo añadimos a la
        //serpiente; si no, informamos por pantalla
        if (encontradoNuevoSegmentoValido) {
            segmentos.add(posibleNuevoSegmento);
            segmentoAdicionado = true;
        }

        return segmentoAdicionado;
    }

    /*
     * Indica si un segmento es valido, es decir, si se puede adicionar
     * a la serpiente sin que colisione con otros segmentos existentes de la serpiente
     * o con los bordes del lienzo
     */
    private boolean esSegmentoValido(Segment segmento)
    {
        return (!colisionaConOtrosSegmentos(segmento) && !colisionaConBordes(segmento));        
    }

    /*
     * Indica si el segmento dado colisiona con los bordes del lienzo
     */
    public boolean colisionaConBordes(Segment segmento)
    {
        boolean colisiona = false;
        if ((segmento.getPosicionFinalX() >= anchoLienzo - MARGEN_LIENZO) ||
        (segmento.getPosicionFinalY() >= altoLienzo - MARGEN_LIENZO) ||
        (segmento.getPosicionFinalX() <= MARGEN_LIENZO) ||
        (segmento.getPosicionFinalY() <= MARGEN_LIENZO)) {
            colisiona = true;
        }

        return colisiona;
    }

    /*
     * Indica si el segmento colisiona con otros segmentos de la serpiente 
     */
    public boolean colisionaConOtrosSegmentos(Segment segmento)
    {
        boolean colisiona = false;
        for (Segment segmentoSerpiente : segmentos) {
            if (segmentoSerpiente.colisiona(segmento)) {
                colisiona = true;
            }
        }
        return colisiona;
    }

    /*
     * Indica si la galleta colisiona con los segmentos de la serpiente 
     */
    public boolean colisionaCon(int xPos, int yPos)
    {
        boolean colisiona = false;
        for (Segment segmentoSerpiente : segmentos) {
            if ((segmentoSerpiente.getPosicionInicialX() == xPos && 
                segmentoSerpiente.getPosicionInicialY() == yPos) || 
                (segmentoSerpiente.getPosicionFinalX() == xPos && 
                segmentoSerpiente.getPosicionFinalY() == yPos)) {
                colisiona = true;
            }
        }
        return colisiona;
    }

    /**
     * Genera un movimiento en la serpiente
     */
    public boolean mover(Canvas lienzo){        
        borrar(lienzo);
        boolean noColision = addSegment();
        if(noColision){
            segmentos.get(0).borrar(lienzo); 
            segmentos.remove(0);
        }
        dibujar(lienzo);
        return noColision;
    }
}
