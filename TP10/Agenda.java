/**
 * Interface représentant un agenda avec des créneaux de rendez-vous.
 */
public interface Agenda {

	/** Le plus petit créneau possible. */
	int CRENEAU_MIN = 1;

	/** Le plus grand créneau possible. */
	int CRENEAU_MAX = 366;

	/**
	 * Obtenir le nom de l'agenda.
	 *
	 * @return le nom de l'agenda
	 */
	String getNom();

	/**
	 * Enregistrer un rendez-vous dans cet agenda.
	 *
	 * @param creneau le créneau du rendez-vous
	 * @param rdv le rendez-vous (doit être une chaîne non nulle et non vide)
	 * @throws CreneauInvalideException si le créneau est invalide
	 * @throws IllegalArgumentException si le rendez-vous est null ou vide
	 * @throws OccupeException si le créneau est déjà occupé
	 */
	void enregistrer(int creneau, String rdv) throws OccupeException;

	/**
	 * Annuler le rendez-vous pris à un créneau donné.
	 * Rien ne se passe si le créneau est libre.
	 * Retourne vrai si l'agenda est modifié (un rendez-vous est annulé),
	 * faux sinon.
	 *
	 * @param creneau le créneau du rendez-vous à annuler
	 * @return vrai si l'agenda est modifié, faux si le créneau était déjà libre
	 * @throws CreneauInvalideException si le créneau est invalide
	 */
	boolean annuler(int creneau);

	/**
	 * Obtenir le rendez-vous pris à un créneau donné.
	 *
	 * @param creneau le créneau du rendez-vous
	 * @return le rendez-vous enregistré pour ce créneau
	 * @throws LibreException si aucun rendez-vous n'est pris à ce créneau
	 * @throws CreneauInvalideException si le créneau est invalide
	 */
	String getRendezVous(int creneau) throws LibreException;
}
