package cinema;

public class Film {

	private String titolo;
	private String autore;
	private String produttore;
	private Generi genere;
	private int durata;
	
	public Film(String titolo, String autore, String produttore, Generi genere, int durata) {
		this.titolo = titolo;
		this.autore = autore;
		this.produttore = produttore;
		this.genere = genere;
		this.durata = durata;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getAutore() {
		return autore;
	}

	public String getProduttore() {
		return produttore;
	}

	public Generi getGenere() {
		return genere;
	}

	public int getDurata() {
		return durata;
	}
	
	@Override
	public String toString() {
		return "Film [titolo=" + titolo + ", autore=" + autore + ", produttore=" + produttore + ", genere=" + genere
				+ ", durata=" + durata + "]";
	}
}
