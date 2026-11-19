package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.util.Random;

public class Baba extends GameElement {

    private String direction;

    /**
     * Cria a Baba numa posicao, virada para uma direcao.
     * @param position posicao inicial
     * @param direction letra da direcao inicial (u, r, d ou l)
     */
    public Baba(Point2D position, String direction) {
        super(position, LAYER_CHARACTER);
        this.direction = direction;
    }

    /**
     * Devolve o nome da imagem, que depende da direcao (baba-u, baba-r, ...).
     * @return nome da imagem
     */
    @Override
    public String getName() {
        return "baba-" + direction;
    }

    public void move(Direction d) {
        Random rand = new Random();
        setPosition(new Point2D(rand.nextInt(10), rand.nextInt(10)));
        //TODO: Meti o setPosition() por coerencia, Luis deixei como random ainda, pronto, esta vai ser a tua parte no fundo :)
    }
}
