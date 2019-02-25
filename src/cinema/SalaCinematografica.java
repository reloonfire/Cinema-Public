package cinema;

public class SalaCinematografica {

	private String nome;
	private float incasso;
	private Spettatore spettatori[];
	private Film filmProiettato;
	private int index = 0; // serve per inserire uno spettatore nella sala in un posto libero

	public SalaCinematografica(String nome, int nPosti) {
		spettatori = new Spettatore[nPosti]; // array che conterrà il numero di posti totali
		this.nome = nome;
	}

	public void svuotaSala() {      // svuota la sala
		for (int i = 0; i < spettatori.length; i++) { 
			spettatori[i] = null;
		}
		index = 0;
	}

	public void consentiIngresso(Spettatore spettatore) throws Exception { // controlla se uno spettatore può accedere
		if(filmProiettato != null) {
			if (index < spettatori.length) {
				if (spettatore.getEta() <= 14 && filmProiettato.getGenere().equals(Generi.HORROR)) { 
					throw new Exception("Film Vietato"); // viene lanciata l' eccezione  se lo spettatore ha 14 anni o meno
				} else {
					spettatori[index] = spettatore;
					index++;
				}
			} else {
				throw new Exception("SalaAlCompleto"); //viene lanciata l' eccezione ala al completo
			}
		} else {
			throw new Exception("FilmNonDisponibile");
		}
	}

	public float calcolaIncasso() {  // calcola l'incasso di una singola sala
		for (int i = 0; i < index; i++) {
			incasso += spettatori[i].getBiglietto().getPrezzo(); // incasso = incasso spettatori[i].getBiglietto().getPrezzo();
		}
		return incasso;
	}

	public void azzeraIncasso() {
		incasso = 0;
	}

	public String getNome() {
		return nome;
	}

	public Spettatore[] getSpettatori() {
		return spettatori;
	}

	public void setFilm(Film film) {
		filmProiettato = film;
	}
	
	public float getIncasso() {
		return incasso;
	}

	public Film getFilmProiettato() {
		return filmProiettato;
	}
	
	
}
