package objects;

import pt.iscte.poo.utils.Point2D;

public class Rock extends GameElement {

    /**
     * Cria uma Rock na posicao indicada.
     * @param position posicao na grid
     */
    public Rock(Point2D position) {
        super(position, LAYER_OBJECT);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "rock";
    }
}
