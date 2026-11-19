package pt.iscte.poo.game;

import objects.Baba;
import objects.Bomb;
import objects.Box;
import objects.CrackedWall;
import objects.Flag;
import objects.GameElement;
import objects.Grass;
import objects.Hole;
import objects.Rock;
import objects.Wall;
import objects.WordBox;
import objects.WordIs;
import objects.WordMove;
import objects.WordStop;
import objects.WordWin;
import pt.iscte.poo.utils.Point2D;

/**
 * Cria elementos de jogo a partir do conteudo dos ficheiros de nivel.
 * E o unico sitio a alterar quando se acrescenta um novo tipo de elemento.
 */
public final class ElementFactory {

    /**
     * Construtor privado: classe apenas com metodos estaticos.
     */
    private ElementFactory() {
    }

    /**
     * Cria o elemento correspondente a um caracter do mapa.
     * @param c caracter lido do mapa
     * @param p posicao na grid
     * @return o elemento criado, ou null se for apenas chao (espaco)
     * @throws InvalidLevelException se o caracter for desconhecido
     */
    public static GameElement fromMapChar(char c, Point2D p) throws InvalidLevelException {
        switch (c) {
            case 'X': return new Wall(p);
            case 'x': return new CrackedWall(p);
            case 'H': return new Hole(p);
            case 'g': return new Grass(p);
            case ' ': return null;
            default: throw new InvalidLevelException("caracter de mapa desconhecido '" + c + "' na posicao " + p);
        }
    }

    /**
     * Cria um objeto a partir do seu nome no ficheiro de nivel.
     * @param name nome do objeto
     * @param p posicao inicial
     * @param directionLetter letra da direcao (so para personagens), ou null
     * @return o elemento criado
     * @throws InvalidLevelException se o nome for desconhecido ou a direcao estiver errada
     */
    public static GameElement fromName(String name, Point2D p, String directionLetter) throws InvalidLevelException {
        if (name.equals("baba")) {
            if (directionLetter == null)
                throw new InvalidLevelException("a baba precisa de uma direcao (u, r, d ou l)");
            if (!directionLetter.matches("[urdl]"))
                throw new InvalidLevelException("direcao invalida '" + directionLetter + "' (use u, r, d ou l)");
            return new Baba(p, directionLetter);
        }
        if (directionLetter != null)
            throw new InvalidLevelException("o objeto '" + name + "' nao aceita direcao");

        return switch (name) {
            case "rock" -> new Rock(p);
            case "bomb" -> new Bomb(p);
            case "box" -> new Box(p);
            case "flag" -> new Flag(p);
            case "wordBox" -> new WordBox(p);
            case "wordIs" -> new WordIs(p);
            case "wordWin" -> new WordWin(p);
            case "wordMove" -> new WordMove(p);
            case "wordStop" -> new WordStop(p);
            default -> throw new InvalidLevelException("objeto desconhecido '" + name + "'");
        };
    }
}
