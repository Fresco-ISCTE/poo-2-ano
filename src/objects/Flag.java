package objects;

import pt.iscte.poo.utils.Point2D;

public class Flag extends GameElement {

    /**
     * Cria uma Flag na posicao indicada.
     * @param position posicao na grid
     */
    public Flag(Point2D position) {
        super(position, LAYER_OBJECT);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "flag";
    }
}
