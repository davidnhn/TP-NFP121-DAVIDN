package allumettes;

/**
 * Exception qui indique qu'une opération interdite a été tentée.
 */
public class OperationInterditeException extends RuntimeException {

    /**
     * Initialiser OperationInterditeException avec le message précisé.
     * @param message le message d'erreur
     */
    public OperationInterditeException(String message) {
        super(message);
    }

} 