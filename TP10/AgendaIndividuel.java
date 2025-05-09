/**
 * Définition d'un agenda individuel.
 */
public class AgendaIndividuel extends AgendaAbstrait {

	private String[] rendezVous;	// le texte des rendezVous


	/**
	 * Créer un agenda vide (avec aucun rendez-vous).
	 *
	 * @param nom le nom de l'agenda
	 * @throws IllegalArgumentException si nom nul ou vide
	 */
	public AgendaIndividuel(String nom) {
		super(nom);
		this.rendezVous = new String[Agenda.CRENEAU_MAX + 1];
			// On gaspille une case (la première qui ne sera jamais utilisée)
			// mais on évite de nombreux « creneau - 1 »
	}


	@Override
	public void enregistrer(int creneau, String rdv) throws OccupeException {
		verifierCreneauValide(creneau);

		if(rdv == null || rdv.trim().isEmpty()) {
			throw new IllegalArgumentException("Le rendez-vous ne peut pas être null ou vide.");

		}

		if(rendezVous[creneau] != null) {
			throw new OccupeException("Le créneau " + creneau + " est déjà occupé.");
		}
		this.rendezVous[creneau] = rdv;
	}


	@Override
	public boolean annuler(int creneau) {
		verifierCreneauValide(creneau);
		boolean modifie = this.rendezVous[creneau] != null;
		this.rendezVous[creneau] = null;
		return modifie;
	}


	@Override
	public String getRendezVous(int creneau) throws LibreException {
		verifierCreneauValide(creneau); // Vérifie si le créneau est valide

		if (rendezVous[creneau] == null) {
			throw new LibreException("Aucun rendez-vous n'est enregistré pour le créneau " + creneau + ".");
		}

		return rendezVous[creneau];
	}


}
