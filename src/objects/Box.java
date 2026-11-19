package objects;

import pt.iscte.poo.utils.Point2D;

public class Box extends GameElement {

    /**
     * Cria uma Box na posicao indicada.
     * @param position posicao na grid
     */
    public Box(Point2D position) {
        super(position, LAYER_OBJECT);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "box";
    }
}
