package objects;

import pt.iscte.poo.utils.Point2D;

public class WordBox extends GameElement {

    /**
     * Cria uma WordBox na posicao indicada.
     * @param position posicao na grid
     */
    public WordBox(Point2D position) {
        super(position, LAYER_WORD);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "word-box";
    }
}
