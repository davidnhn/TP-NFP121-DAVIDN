public interface EnsembleOrdonne<T extends Comparable<T>> extends Ensemble<T> {
    /**
     * Trouver le plus petit élément de l'ensemble.
     * @return Le plis petit élément.
     * @throws IllegalStateException si l'ensemble est vide.
     */
    T trouverMin() throws IllegalStateException;

    T justePlusGrandQue(T x);
}
