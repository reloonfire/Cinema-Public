package cinema;

public class SalaCinematografica {

	private String nome;
	private float incasso;
	private Spettatore spettatori[];
	private Film filmProiettato;
	private int index = 0;

	public SalaCinematografica(String nome, int nPosti) {
		spettatori = new Spettatore[nPosti];
		this.nome = nome;
	}

	public void svuotaSala() {
		for (int i = 0; i < spettatori.length; i++) {
			spettatori[i] = null;
		}
	}

	public void consentiIngresso(Spettatore spettatore) throws Exception {
		if (index < spettatori.length) {
			if (spettatore.getEta() <= 14 && filmProiettato.getGenere().equals(Generi.HORROR)) {
				throw new Exception("Film Vietato");
			} else {
				spettatori[index] = spettatore;
				index++;
			}
		} else {
			throw new Exception("SalaAlCompleto");
		}

	}
	
	public float calcolaIncasso() {
		for(int i = 0; i < index; i++) {
			incasso += spettatori[i].getBiglietto().getPrezzo(); // incasso = incasso + spettatori[i].getBiglietto().getPrezzo();
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
}
