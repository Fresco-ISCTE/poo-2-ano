package pt.iscte.poo.game;

public class InvalidLevelException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com uma mensagem simples.
     * @param message descricao do problema
     */
    public InvalidLevelException(String message) {
        super(message);
    }

    /**
     * Cria a excecao indicando o ficheiro e a linha onde ocorreu o problema.
     * @param fileName nome do ficheiro de nivel
     * @param lineNumber numero da linha (a contar de 1)
     * @param detail descricao do problema
     */
    public InvalidLevelException(String fileName, int lineNumber, String detail) {
        super(fileName + ", linha " + lineNumber + ": " + detail);
    }
}
