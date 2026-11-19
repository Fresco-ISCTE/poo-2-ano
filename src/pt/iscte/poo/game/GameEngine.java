package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;

import objects.Baba;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

public class GameEngine implements Observer {

    private static final GameEngine INSTANCE = new GameEngine();
    private static final String ROOMS_DIRECTORY = "rooms";
    private static final int TILE_SIZE = 50;

    private Room currentRoom;
    private int currentLevel;
    private int lastTickProcessed = 0;

    /**
     * Construtor privado (singleton): carrega o primeiro nivel.
     */
    private GameEngine() {
        loadLevel(0);
    }

    /**
     * Devolve a instancia unica do motor de jogo.
     * @return o motor de jogo
     */
    public static GameEngine getInstance() {
        return INSTANCE;
    }

    /**
     * Carrega o nivel n a partir do ficheiro rooms/roomN.txt e mostra-o na GUI.
     * Em caso de erro mostra uma mensagem com a causa.
     * @param n numero do nivel
     */
    public void loadLevel(int n) {
        File f = new File(ROOMS_DIRECTORY, "room" + n + ".txt");
        try {
            Room room = Room.readRoom(f);
            currentRoom = room;
            currentLevel = n;
            showRoom();
        } catch (FileNotFoundException e) {
            ImageGUI.getInstance().showMessage("Erro", "Ficheiro de nivel nao encontrado: " + f.getPath());
        } catch (InvalidLevelException e) {
            ImageGUI.getInstance().showMessage("Nivel invalido", e.getMessage());
        }
    }

    /**
     * Ajusta o tamanho da janela ao nivel atual e envia todas as imagens para a GUI.
     * So e chamado ao carregar ou reiniciar um nivel.
     */
    private void showRoom() {
        ImageGUI gui = ImageGUI.getInstance();
        gui.setSize(currentRoom.getCols() * TILE_SIZE, currentRoom.getRows() * TILE_SIZE);
        gui.clearImages();
        gui.addImages(currentRoom.getAllElements());
        updateStatus();
        gui.update();
    }

    /**
     * Atualiza a barra de estado com o nivel atual.
     */
    private void updateStatus() {
        ImageGUI.getInstance().setStatusMessage(
                "Nivel " + currentLevel);
    }

    /**
     * Chamado pela GUI quando ha uma tecla pressionada ou um tick.
     * @param source objeto observado que gerou a notificacao
     */
    @Override
    public void update(Observed source) {
        if (currentRoom == null)
            return;

        if (ImageGUI.getInstance().wasKeyPressed()) {
            int k = ImageGUI.getInstance().keyPressed();

            if (Direction.isDirection(k)) {
                Direction d = Direction.directionFor(k);
                getPlayer().move(d);
            }
        }

        int t = ImageGUI.getInstance().getTicks();
        while (lastTickProcessed < t) {
            processTick();
        }
        ImageGUI.getInstance().update();
    }

    /**
     * Processa a passagem de um tick (usado mais tarde, p.e. pelo rastilho da bomba).
     */
    private void processTick() {
        lastTickProcessed++;
    }

    /**
     * Devolve o nivel atual.
     * @return o nivel atual
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Devolve a personagem controlada pelo jogador.
     * @return a Baba do nivel atual
     */
    public Baba getPlayer() {
        return currentRoom.getPlayer();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
