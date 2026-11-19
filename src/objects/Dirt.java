package objects;

import pt.iscte.poo.utils.Point2D;

public class Dirt extends GameElement {

    /**
     * Cria a Dirt na posicao indicada.
     * @param position posicao na grid
     */
    public Dirt(Point2D position) {
        super(position, LAYER_FLOOR);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "dirt";
    }
}
