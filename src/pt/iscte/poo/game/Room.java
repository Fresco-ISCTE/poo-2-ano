package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import objects.Baba;
import objects.Dirt;
import objects.GameElement;
import objects.Wall;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {

    private final String roomName;
    private final int cols;
    private final int rows;
    private final List<GameElement> elements = new ArrayList<>();
    private Baba player;

    /**
     * Cria um nivel vazio. Os niveis sao criados atraves de readRoom.
     * @param roomName nome do ficheiro de origem
     * @param cols numero de colunas
     * @param rows numero de linhas
     */
    private Room(String roomName, int cols, int rows) {
        this.roomName = roomName;
        this.cols = cols;
        this.rows = rows;
    }

    /**
     * Le um nivel a partir de um ficheiro de configuracao.
     * @param f ficheiro do nivel (roomN.txt)
     * @return o nivel lido
     * @throws FileNotFoundException se o ficheiro nao existir
     * @throws InvalidLevelException se o conteudo do ficheiro for invalido
     */
    public static Room readRoom(File f) throws FileNotFoundException, InvalidLevelException {
        String file = f.getName();
        try (Scanner sc = new Scanner(f)) {
            int lineNo = 1;
            if (!sc.hasNextLine())
                throw new InvalidLevelException(file, lineNo, "ficheiro vazio");
            Room room = parseHeader(sc.nextLine(), file, lineNo);

            for (int y = 0; y < room.rows; y++) {
                lineNo++;
                if (!sc.hasNextLine())
                    throw new InvalidLevelException(file, lineNo, "mapa incompleto: esperadas " + room.rows + " linhas");
                room.readMapLine(sc.nextLine(), y, file, lineNo);
            }

            while (sc.hasNextLine()) {
                lineNo++;
                String line = sc.nextLine().trim();
                if (!line.isEmpty())
                    room.readObjectLine(line, file, lineNo);
            }

            if (room.player == null)
                throw new InvalidLevelException(file + ": o nivel nao tem nenhuma baba");
            return room;
        }
    }

    /**
     * Interpreta a primeira linha do ficheiro, no formato cols,rows.
     * @param line linha lida
     * @param file nome do ficheiro (para mensagens de erro)
     * @param lineNo numero da linha
     * @return um nivel vazio com essas dimensoes
     * @throws InvalidLevelException se o formato ou os valores forem invalidos
     */
    private static Room parseHeader(String line, String file, int lineNo) throws InvalidLevelException {
        String[] parts = line.trim().split(",");
        if (parts.length != 2)
            throw new InvalidLevelException(file, lineNo, "esperado 'cols,rows' mas foi lido '" + line + "'");
        try {
            int cols = Integer.parseInt(parts[0].trim());
            int rows = Integer.parseInt(parts[1].trim());
            if (cols <= 0 || rows <= 0)
                throw new InvalidLevelException(file, lineNo, "dimensoes tem de ser positivas");
            return new Room(file, cols, rows);
        } catch (NumberFormatException e) {
            throw new InvalidLevelException(file, lineNo, "dimensoes nao numericas em '" + line + "'");
        }
    }

    /**
     * Le uma linha do mapa: coloca chao em todas as celulas e os elementos fixos por cima.
     * Linhas mais curtas que cols sao completadas com chao (espacos finais cortados pelo editor).
     * @param line linha do mapa (sem trim, os espacos sao celulas)
     * @param y indice da linha no mapa
     * @param file nome do ficheiro (para mensagens de erro)
     * @param lineNo numero da linha no ficheiro
     * @throws InvalidLevelException se a linha for longa demais ou tiver caracteres invalidos
     */
    private void readMapLine(String line, int y, String file, int lineNo) throws InvalidLevelException {
        if (line.length() > cols)
            throw new InvalidLevelException(file, lineNo, "linha do mapa com " + line.length() + " caracteres, esperados " + cols);
        for (int x = 0; x < cols; x++) {
            Point2D p = new Point2D(x, y);
            addElement(new Dirt(p));
            char c = x < line.length() ? line.charAt(x) : ' ';
            try {
                GameElement e = ElementFactory.fromMapChar(c, p);
                if (e != null)
                    addElement(e);
            } catch (InvalidLevelException ex) {
                throw new InvalidLevelException(file, lineNo, ex.getMessage());
            }
        }
    }

    /**
     * Le uma linha de objeto, no formato nome,x,y ou nome,x,y,direcao.
     * @param line linha ja sem espacos nas pontas
     * @param file nome do ficheiro (para mensagens de erro)
     * @param lineNo numero da linha no ficheiro
     * @throws InvalidLevelException se a linha for invalida
     */
    private void readObjectLine(String line, String file, int lineNo) throws InvalidLevelException {
        String[] fields = line.split(",");
        if (fields.length < 3 || fields.length > 4)
            throw new InvalidLevelException(file, lineNo, "esperado 'nome,x,y[,direcao]' mas foi lido '" + line + "'");

        String name = fields[0].trim();
        Point2D p;
        try {
            p = new Point2D(Integer.parseInt(fields[1].trim()), Integer.parseInt(fields[2].trim()));
        } catch (NumberFormatException e) {
            throw new InvalidLevelException(file, lineNo, "coordenadas nao numericas em '" + line + "'");
        }
        if (!isInside(p))
            throw new InvalidLevelException(file, lineNo, "posicao " + p + " fora do mapa " + cols + "x" + rows);

        String direction = fields.length == 4 ? fields[3].trim() : null;
        GameElement e;
        try {
            e = ElementFactory.fromName(name, p, direction);
        } catch (InvalidLevelException ex) {
            throw new InvalidLevelException(file, lineNo, ex.getMessage());
        }

        if (e instanceof Baba) {
            if (player != null)
                throw new InvalidLevelException(file, lineNo, "o nivel so pode ter uma baba");
            player = (Baba) e;
        }
        addElement(e);
    }

    /**
     * Acrescenta um elemento ao nivel, na posicao que ele indica.
     * @param e elemento a acrescentar
     */
    private void addElement(GameElement e) {
        elements.add(e);
    }

    /**
     * Indica se uma posicao esta dentro dos limites do mapa.
     * @param p posicao a verificar
     * @return true se estiver dentro do mapa
     */
    public boolean isInside(Point2D p) {
        return p.getX() >= 0 && p.getX() < cols && p.getY() >= 0 && p.getY() < rows;
    }

    /**
     * Devolve todos os elementos do nivel (lista so de leitura).
     * @return todos os elementos
     */
    public List<GameElement> getAllElements() {
        return Collections.unmodifiableList(elements);
    }

    /**
     * Devolve o nome do ficheiro de origem do nivel.
     * @return nome do nivel
     */
    public String getName() {
        return roomName;
    }

    /**
     * Devolve o numero de colunas do mapa.
     * @return numero de colunas
     */
    public int getCols() {
        return cols;
    }

    /**
     * Devolve o numero de linhas do mapa.
     * @return numero de linhas
     */
    public int getRows() {
        return rows;
    }

    /**
     * Devolve a personagem controlada pelo jogador.
     * @return a Baba deste nivel
     */
    public Baba getPlayer() {
        return player;
    }

    public boolean isWall(Point2D position) {
        for (ImageTile t : elements)
            if (t instanceof Wall && t.getPosition().equals(position))
                return true;
        return false;
    }
}