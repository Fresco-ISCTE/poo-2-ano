package objects;

import pt.iscte.poo.utils.Point2D;

public class WordWin extends GameElement {

    /**
     * Cria uma WordWin na posicao indicada.
     * @param position posicao na grid
     */
    public WordWin(Point2D position) {
        super(position, LAYER_WORD);
    }

    /**
     * Devolve o nome da imagem.
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "word-win";
    }
}
