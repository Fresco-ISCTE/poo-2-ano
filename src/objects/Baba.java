package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.awt.*;
import java.util.Random;

public class Baba implements ImageTile {

    private Point2D position;
    private String direction = "r";

    public Baba(Point2D position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "baba-" + direction;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public int getLayer() {
        return 25;
    }

    public void move(Direction d) {
        // O Point2D tem o método .plus() onde podemos somar um Vetor2D e é devolvido um novo ponto
        // Calcular qual será a nova posição com base na tecla pressionada
        Point2D newPos = position.plus(d.asVector());

        // Mudar a direção do baba
        direction = d.getName();

        // Verificar se a nova posição é uma parede
//        if (!GameEngine.getInstance().getCurrentRoom().isWall(newPos))
//            position = newPos;
    }
}
