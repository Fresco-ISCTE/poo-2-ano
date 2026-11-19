package objects;

import pt.iscte.poo.utils.Point2D;

public class Bomb extends GameElement {

    /**
     * Cria uma Bomb na posicao indicada.
     * @param position posicao na grid
     */
    public Bomb(Point2D position) {
        super(position, LAYER_OBJECT);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "bomb";
    }
}
