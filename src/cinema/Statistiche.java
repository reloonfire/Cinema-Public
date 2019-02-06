package cinema;

public class Statistiche
{
	private int spettatoriGeneriOggi [] = new int[Generi.values().length]; //spettatori per genere di oggi
	private int spettatoriGeneriIeri [] = new int[Generi.values().length]; //spettatori per genere di ieri
	
	private Film [] filmCinema;											   //lista che punta all'array di film dell'ogggetto Cinema
	private int [] spettatoriPerFilm;									   //array che contiente gli spettatori per ogni Film
	
	public Statistiche(Film[] filmCinema)								   //inizializza gli array filmCinema e spettatoriPerFilm
	{
		this.filmCinema = filmCinema;
		spettatoriPerFilm = new int[this.filmCinema.length];
	}
	
	public void aggiornaDati(int nSpettatori, Film film)                   //metodo per aggiornare i dati riguardanti gli spettatori di ogni film e spettatori per Genere
	{
		spettatoriGeneriOggi[film.getGenere().getIndex()] += nSpettatori;
		for(int i = 0; i < filmCinema.length; i++)
		{
			if(filmCinema[i].equals(film))
			{
				spettatoriPerFilm[i] += nSpettatori;
			}
		}
	}
	
	public String calcolaStatsGeneri(Generi genere)                        //metodo per il calcolo della percentuale di incremento/decremento
	{
		if(spettatoriGeneriIeri[genere.getIndex()] == 0)
		{
			return "Non puoi calcolare le statistiche di questo genere perché ieri nessuno ha visto film di questo genere";
		}
		
		double differenzaSpettOggiIeri = spettatoriGeneriOggi[genere.getIndex()] - spettatoriGeneriIeri[genere.getIndex()];
		double percentuale = 100 * differenzaSpettOggiIeri / spettatoriGeneriIeri[genere.getIndex()];
		
		return (spettatoriGeneriOggi[genere.getIndex()] < spettatoriGeneriIeri[genere.getIndex()]
				? "Il genere " + genere + " da ieri a oggi ha avuto un decremento del " + String.format("%.2f", percentuale*-1) + "%"
				: "Il genere " + genere + " da ieri a oggi ha avuto un incremento del " + String.format("%.2f", percentuale) + "%");
				
	}
	
	public void daOggiAIeri()											   //metodo da eseguire a fine giornata per copiare l'array spettatoriGeneriOggi in spettatoriGeneriIeri per poi azzerarlo
	{
		spettatoriGeneriIeri = spettatoriGeneriOggi.clone();
		spettatoriGeneriOggi = new int[spettatoriGeneriIeri.length];
		spettatoriPerFilm = new int[this.filmCinema.length];
	}
	
	public String calcolaMaxSpettatoriFilm() 							   //metodo per individuare il film con più spettatori
	{
		int maxSpett = 0;
		int indice = 0;
		for (int i = 0; i < filmCinema.length; i++) {
			if (spettatoriPerFilm[i] > maxSpett) {
				indice = i;
				maxSpett = spettatoriPerFilm[i];
			}
		}
		
		return "Il film che ha avuto il maggior numero di spettatori oggi è stato " + filmCinema[indice] + " con " + spettatoriPerFilm[indice] + " spettatori";
	}
	
	public String calcolaMinSpettatoriFilm() 							  //metodo per individuare il film con meno spettatori
	{
		int minSpett = 1000;
		int indice = 0;
		for (int i = 0; i < filmCinema.length; i++) {
			if (spettatoriPerFilm[i] < minSpett) {
				minSpett = spettatoriPerFilm[i];
				indice = i;
			}
		}
	 
		return "Il film che ha avuto il minor numero di spettatori oggi è stato " + filmCinema[indice] + " con " + spettatoriPerFilm[indice] + " spettatori";
	}

	//La percentuale dei generi vengono fatte in base al cambiamento di spettatori tra ieri e oggi
	//Invece per il MaxFilm e MinFilm il calcolo viene fatto ogni giorno, quindi si resettano i valori a inizio di una nuova giornata
}
