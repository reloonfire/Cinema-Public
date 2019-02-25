package cinema;

public class Biglietto {

	private int posizione;//variabile integer che indica la posizione
	private float prezzo;//variabile float che indica il prezzo

	public Biglietto(int posizione, float prezzo) {//costruttore
		this.posizione = posizione;
		this.prezzo = prezzo;
	}

	public void applicaRiduzioneAnziani() {
		prezzo = (float) (prezzo - (prezzo * 0.1));// operazione sconto per anziani
	}

	public void calcolaRiduzioneBambini() {
		prezzo = (float) (prezzo - (prezzo * 0.5));// operazione sconto per bambini
	}

	public int getPosizione() {
		return posizione;
	}

	//public void setPosizione(int posizione, salaCinematografica[]  sala) {
		
		
	//	this.posizione = posizione;
	//}

	public float getPrezzo() {
		return prezzo;
		
	}
	@Override
	public String toString() {
		return "Biglietto [posizione=" + posizione + ", prezzo=" + prezzo + "]";
	}

}