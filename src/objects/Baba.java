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

    public void move(Direction d) {
        // O Point2D tem o método .plus() onde podemos somar um Vetor2D e é devolvido um novo ponto
        // Calcular qual será a nova posição com base na tecla pressionada
        Point2D newPos = getPosition().plus(d.asVector());

        // Mudar a direção do baba
        direction = d.getName();

        // Verificar se a nova posição é uma parede
        if (!GameEngine.getInstance().getCurrentRoom().isWall(newPos))
            setPosition(newPos);
    }
}
