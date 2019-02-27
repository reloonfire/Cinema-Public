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
		spettatoriGeneriOggi[film.getGenere().ordinal()] += nSpettatori;
		for(int i = 0; i < filmCinema.length; i++)
		{
			if(filmCinema[i].equals(film))
			{
				spettatoriPerFilm[i] += nSpettatori;
			}
		}
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
		
		return "Il film che ha avuto il maggior numero di spettatori oggi è stato " + filmCinema[indice].getTitolo() + " con " + spettatoriPerFilm[indice] + " spettatori";
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
	 
		return "Il film che ha avuto il minor numero di spettatori oggi è stato " + filmCinema[indice].getTitolo() + " con " + spettatoriPerFilm[indice] + " spettatori";
	}

	
	
	public String calcolaStatsGeneri()				//ritorna tutte le stats per tutti i generi
	{
		String ritorno = "";
		
		for(Generi g: Generi.values())
		{
			if(spettatoriGeneriIeri[g.ordinal()] == 0) ritorno = "Non vi sono statistiche da calcolare per il genere " + g + "\n";
			else
			{
				double differenzaSpettOggiIeri = spettatoriGeneriOggi[g.ordinal()] - spettatoriGeneriIeri[g.ordinal()];
				ritorno = (spettatoriGeneriOggi[g.ordinal()] < spettatoriGeneriIeri[g.ordinal()]
						   ? "Il genere " + g + " da ieri a oggi ha avuto un decremento del " + String.format("%.2f", 100 * differenzaSpettOggiIeri / spettatoriGeneriIeri[g.ordinal()]*-1) + "%\n"
						   : "Il genere " + g + " da ieri a oggi ha avuto un incremento del " + String.format("%.2f", 100 * differenzaSpettOggiIeri / spettatoriGeneriIeri[g.ordinal()]) + "%\n");
			}
		}
		
		return ritorno;
	}
	
	//La percentuale dei generi vengono fatte in base al cambiamento di spettatori tra ieri e oggi
	//Invece per il MaxFilm e MinFilm il calcolo viene fatto ogni giorno, quindi si resettano i valori a inizio di una nuova giornata
}
