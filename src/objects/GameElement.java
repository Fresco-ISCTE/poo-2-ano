package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

    public static final int LAYER_FLOOR = 5;

    public static final int LAYER_DECOR = 10;

    public static final int LAYER_WALL = 15;

    public static final int LAYER_OBJECT = 20;

    public static final int LAYER_WORD = 22;

    public static final int LAYER_CHARACTER = 25;

    private Point2D position;
    private final int layer;

    /**
     * Cria um elemento numa posicao e camada.
     * @param position posicao inicial na grid
     * @param layer camada de desenho
     */
    protected GameElement(Point2D position, int layer) {
        this.position = position;
        this.layer = layer;
    }

    /**
     * Devolve a posicao atual do elemento na grid.
     * @return posicao atual
     */
    @Override
    public Point2D getPosition() {
        return position;
    }

    /**
     * Altera a posicao do elemento.
     * @param position nova posicao
     */
    protected void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Devolve a camada de desenho do elemento.
     * @return camada de desenho
     */
    @Override
    public int getLayer() {
        return layer;
    }

    /**
     * Devolve o nome da imagem do elemento (sem extensao).
     * @return nome da imagem
     */
    @Override
    public abstract String getName();
}
