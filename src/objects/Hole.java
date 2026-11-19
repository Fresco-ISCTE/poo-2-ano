package objects;

import pt.iscte.poo.utils.Point2D;

public class Hole extends GameElement {

    /**
     * Cria um Hole na posicao indicada.
     * @param position posicao na grid
     */
    public Hole(Point2D position) {
        super(position, LAYER_DECOR);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "hole";
    }
}
