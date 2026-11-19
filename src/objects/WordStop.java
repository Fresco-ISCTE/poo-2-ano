package objects;

import pt.iscte.poo.utils.Point2D;

public class WordStop extends GameElement {

    /**
     * Cria uma WordStop na posicao indicada.
     * @param position posicao na grid
     */
    public WordStop(Point2D position) {
        super(position, LAYER_WORD);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "word-stop";
    }
}
