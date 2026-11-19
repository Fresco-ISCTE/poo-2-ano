package objects;

import pt.iscte.poo.utils.Point2D;

public class Grass extends GameElement {

    /**
     * Cria uma Grass na posicao indicada.
     * @param position posicao na grid
     */
    public Grass(Point2D position) {
        super(position, LAYER_DECOR);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "grass";
    }
}
