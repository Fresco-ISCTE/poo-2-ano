package objects;

import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement {

    /**
     * Cria uma Wall na posicao indicada.
     * @param position posicao na grid
     */
    public Wall(Point2D position) {
        super(position, LAYER_WALL);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "wall";
    }
}
