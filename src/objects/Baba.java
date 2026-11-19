package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

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

    /**
     * Altera a posicao do baba
     * @param d Direcao onde vai o baba
     */
    public void move(Direction d) {
        Point2D newPos = getPosition().plus(d.asVector());

        direction = d.getName();

        if (!GameEngine.getInstance().getCurrentRoom().isWall(newPos) && GameEngine.getInstance().getCurrentRoom().isInside(newPos))
            setPosition(newPos);
    }
}
