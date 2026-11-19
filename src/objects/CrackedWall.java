package objects;

import pt.iscte.poo.utils.Point2D;

public class CrackedWall extends GameElement {

    /**
     * Cria uma CrackedWall na posicao indicada.
     * @param position posicao na grid
     */
    public CrackedWall(Point2D position) {
        super(position, LAYER_WALL);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "cracked-wall";
    }
}
