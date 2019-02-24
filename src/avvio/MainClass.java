package avvio;
import java.util.InputMismatchException;
import java.util.Scanner;

import cinema.*;

public class MainClass 
{
	private static Scanner sc = new Scanner(System.in);
	private static Cinema cinema =  new Cinema();
	private static boolean isOpen = false;
	
	public static void main(String[]args)
	{
		creaCinema();
		
		while(true)
		{
			while(!isOpen)  //sezione amministrazione del cinema
			{
				String scelta = "";
				System.out.println("MENU CINEMA\n\n"
								 + "0) Per aggiungere il film alla lista del cinema\n"
								 + "1) Per selezionare una sala e assegnarle un film\n"
								 + "2) Per visualizzare le statistiche del cinema\n"
								 + "3) Per aprire il cinema"
								 + "4) Per chiudere l'applicazione");
				switch(scelta)
				{
					case "0": creaFilm(); 
							  break;
							
					case "1": assegnaFilmSala();
							  break;
					
					case "2": mostraStats();
							  break;
					
					case "3": isOpen = true; //METODO PER CONTROLLARE CHE CI SIA UN FILM PER SALA ALMENO
							  break;
					
					case "4": 
							  break;
					
					default:
							  break;
				}
			}
		}
	}
	
	public static void creaCinema()						//metodo per creare il cinema
	{
		//int nSale = 0;								//abilitare se il numero viene scelto dall'admin e quindi modificare classe cinema.
		int nPostiSala = 0;
		String nomeSala = "";
		
//		while(nSale <= 0)
//		{
//			System.out.println("Inserisci il numero delle sale che contiene il cinema");
//			try
//			{
//				nSale = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
//			}
//			catch(NumberFormatException e)
//			{
//				System.out.println("Devi inserire dei numeri!!");
//				nSale = 0;
//			}
//		}
		
		for(int i = 0; i < cinema.getSale().length; i++)	//sostituire con nSale in caso di scelta dall'admin
		{
			while(nomeSala.equals(""))						//continua fino a che la stringa equivale al niente
			{
				System.out.println("Inserisci il nome della sala");
				nomeSala = sc.nextLine();
			}
			
			while(nPostiSala <= 0)							//continuo fino a che la sala non contiene più di 0 posti
			{
				System.out.println("Inserisci il numero di posti che la sala contiene");
				try
				{
					nPostiSala = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));	//rimuovo tutti i caratteri dalla input dell'utente che non siano numeri e poi faccio il cast a Integer
				}
				catch(NumberFormatException e)	//se l'utente ha inserito valori errati il valore passato al casting sarà vuoto e lancerà un'eccezione, qui la gestisco con un syso e reimposto la variabile nPostiSala a 0 per evitare problemi
				{
					System.out.println("Devi inserire dei numeri!!");
					nPostiSala = 0;
				}
			}
			
			SalaCinematografica sala = new SalaCinematografica(nomeSala, nPostiSala);	//creo la sala cinematografica
			cinema.aggiungiSala(sala);													//la aggiungo al cinema
		}
		creaFilm();				//obbligo a creare film
		assegnaFilmSala();		//obbligo almeno alla prima volta ad assegnare dei film alle sale
	}

	public static void creaFilm()   					//creo i film che verranno aggiunti all'array films di Cinema
	{
		Film film = null;
		String titolo = "", 
			   autore = "",
			   produttore = "";
		int durata = 0;
		Generi genere = null;
		
		while(titolo.equals("") || autore.equals("") || produttore.equals("")) //chiedo all'utente  titolo, autore e produttore fino a che tutte le stringhe non sono divere da il vuoto
		{
			System.out.println("Inserisci il titolo, l'autore e il produttore separati da un invio");
			titolo = sc.nextLine();
			autore = sc.nextLine();
			produttore = sc.nextLine();
		}
		
		while(durata <= 0)	//richiedo la durata fino a che non è maggiore di 0
		{
			System.out.println("Inserisci la durata in minuti");
			try
			{
				durata = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));	//stesso discorso sopra per nPostiSala riga 85
			}
			catch(NumberFormatException e)											//stesso discorso sopra per nPostiSala riga 85
			{
				System.out.println("Inserisci dei numeri!");
				durata = 0;
			}
		}
		
		while(genere.equals(null))	//richiedo il genere fino a che non è diverso da null
		{
			Generi.stampaGeneri();	//stampo tutti i generi con indice per l'utente
			System.out.println("\nInserisci il numero relativo al genere da selezionare");
			try
			{
				genere = Generi.values()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))]; //provo ad assegnare un genere tramite tramite l'indice
			}
			catch(NumberFormatException e)															//stesso discorso sopra per nPostiSala riga 85
			{
				System.out.println("Inserisci un numero!");
			}
			catch(IndexOutOfBoundsException e)												//richiedere un elemento di un array con un indice inferiore a 0 o maggiore dell'ultimo elemento lancia l'eccezione qui gestita
			{
				System.out.println("Inserisci un numero compreso tra quelli che vedi!");
			}
		}
		
		film = new Film(titolo, autore, produttore, genere, durata);	//creo il film
		cinema.caricaFilm(film);										//lo aggiungo al cinema
	}

	public static void assegnaFilmSala()				//assegno un film ad una sala
	{
		Film f = null;
		
		while(true)
		{
			
			while(true)
			{
				for(int i = 0; i < cinema.getFilm().length; i++)
				{
					System.out.println(i + ") " + cinema.getFilm()[i]);
				}
				
				System.out.println("Seleziona un film");
				try
				{
					f = cinema.getFilm()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))];
					break;
				}
				catch(NumberFormatException | InputMismatchException e)
				{
					System.out.println("Devi inserire solo numeri!");
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Inserisci solo i numeri della lista grazie");
				}
			}
			
			while(true)
			{
				for(int i = 0; i < cinema.getSale().length; i++)
				{
					System.out.println(i + ") " + cinema.getSale()[i]);
				}
				
				System.out.println("Seleziona una sala a cui assegnare il film");
				try
				{
					cinema.getSale()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))].setFilm(f);
					break;
				}
				catch(NumberFormatException | InputMismatchException e)
				{
					System.out.println("Devi inserire solo numeri!");
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Inserisci solo i numeri della lista grazie");
				}
			}
			System.out.println("Inserisci '0' se hai finito di assegnare i film alle sale");
			if(sc.nextLine().equals("0")) break;
		}
		
	}
	
	public static void mostraStats()
	{
		//System.out.println(cinema.getStats().calcolaMaxSpettatoriFilm() + "\n" + cinema.getStats().calcolaMinSpettatoriFilm() + "\n" + cinema.getStats().calcolaStatsGeneri()); //se si vuole stampare tutto insieme
		
		
		//ABILITARE SOTTO SE SI VUOLE STAMPARE UNA STATS ALLA VOLTA
		String scelta = "";
		while(!scelta.equalsIgnoreCase("9"))
		{
			System.out.println(
							   "0) Per visualizzare il film con più spettatori\n"
							 + "1) Per visualizzare il film con meno spettatori\n"
							 + "2) Per visualizzare le statistiche di un genere specifico\n"
							 + "9) Per tornare indietro");
			switch(scelta)
			{
				case "0": System.out.println(cinema.getStats().calcolaMaxSpettatoriFilm());
					  	  break;
					
				case "1": System.out.println(cinema.getStats().calcolaMinSpettatoriFilm());
						  break;
				
				case "2": 	Generi genere = null;
							while(genere.equals(null))	//richiedo il genere fino a che non è diverso da null
							{
								Generi.stampaGeneri();	//stampo tutti i generi con indice per l'utente
								System.out.println("\nInserisci il numero relativo al genere da selezionare");
								try
								{
									genere = Generi.values()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))]; //provo ad assegnare un genere tramite tramite l'indice
								}
								catch(NumberFormatException e)															//stesso discorso sopra per nPostiSala riga 85
								{
									System.out.println("Inserisci un numero!");
								}
								catch(IndexOutOfBoundsException e)												//richiedere un elemento di un array con un indice inferiore a 0 o maggiore dell'ultimo elemento lancia l'eccezione qui gestita
								{
									System.out.println("Inserisci un numero compreso tra quelli che vedi!");
								}
							}
							//System.out.println(cinema.getStats().calcolaStatsGeneri(genere));	//abilitare insieme al metodo in statistiche riga 29
						  break;
				
				case "9":	
						  break;
			}
		}
		
	}
	
	
}