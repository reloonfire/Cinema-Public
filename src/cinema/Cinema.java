package cinema; //prima era esCinema; Cambiato da Guerine

public class Cinema {

	private Film[] films = new Film[18];															//-variabile: array di film
	private SalaCinematografica[] sale;																//-creazione variabile: array di sale
	private float incasso;																			//-variabile: incasso in float
	private int indexFilm = 0;																		//-variabile: index per array di film
	private int indexSale = 0;																		//-variabile: index per array di sale
	private Statistiche stats = new Statistiche(films);												//creazione e istanziazione oggetto "stats" di tipo Statistiche
	
	public Cinema(int numeroSale) {																	//inizio costruttore di +Cinema
		this.sale = new SalaCinematografica[numeroSale];											//istanziazione array di sale con numeroSale passato al costruttore
	}																								//fine costruttore
	
	
	public void caricaFilm(Film film) {																//inizio metodo +caricaFilm
		
		if (indexFilm >= films.length) {															//inizio IF
			System.out.println("Numero di film massimo raggiunto! Non puoi metterne altri.");		//stampa
		}																							//fine IF
		else {																						//inizio ELSE
			films[indexFilm++] = film;																//incrementa index e aggiunge film nell'array di film
			//System.out.println("Film aggiunto!");													//stampa
		}																							//fine IF
	}																								//fine metodo
	
	
	public void aggiungiSala(SalaCinematografica sala) {											//inizio metodo +aggiungiSala
		
		if (indexSale >= sale.length) {																//inizio IF
			System.out.println("Numero di sale masssimo raggiunto! Non puoi metterne altre.");		//stampa
		}																							//fine IF
		else {																						//inizio ELSE
			sale[indexSale++] = sala;																//incrementa index e aggiunge sala nell'array di sale
			System.out.println("Sala aggiunta!");													//stampa
		}																							//fine ELSE
	}																								//fine metodo
	
	
	public void incassoTot() {																		//inizio metodo +incassoTot
		for (int i = 0; i < sale.length; i++) {														//inizio FOR
			incasso += sale[i].calcolaIncasso();													//somma gli incassi delle sale cinematografiche ( 5 nel nostro caso )
		}																							//fine FOR
		
		System.out.println("Ecco il tuo incasso totale del cinema: " + incasso + "€");					//visualizzazione incasso totale
	}																								//fine metodo
	
	
	public float getIncasso() {																		//inizio metodo +getIncasso
		return incasso;																				//ritorno dell'incasso
	}																								//fine metodo
	
	
	public SalaCinematografica[] getSale() {														//inizio metodo +getSale
		return sale;																				//ritorno dell'array di sale
	}																								//fine metodo
	
	
	public Film[] getFilm() {																		//inizio metodo +getFilm
		return films;																				//ritorno dell'array di film
	}																								//fine metodo

	
	public int getIndexFilm() {																		//inizio metodo +getIndexFilm
		return indexFilm;																			//ritorno dell'indice per l'array di film
	}																								//fine metodo

	
	public int getIndexSala() {																		//inizio metodo +GetIndexSala
		return indexSale;																			//ritorno dell'indice per l'array di sale
	}																								//fine metodo

	
	public Statistiche getStatistiche() {															//inizio metodo +getStatistiche
		return stats;																				//ritorno dell'oggetto stats
	}																								//fine metodo

}																									//fine classBody
