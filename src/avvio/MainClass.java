package avvio;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import cinema.Biglietto;
import cinema.Cinema;
import cinema.Film;
import cinema.Generi;
import cinema.SalaCinematografica;
import cinema.Spettatore;

public class MainClass 
{
	private static Scanner sc = new Scanner(System.in);
	private static Cinema cinema;
	private static boolean isOpen = false;
	
	public static void main(String[]args)
	{
		creaCinema();
		//menuA();
		//menuB();
		String scelta = "";
		
		while(!scelta.equals("9"))
		{
			while(!isOpen)  //sezione amministrazione del cinema
			{
				
				System.out.println("MENU CINEMA AMMINISTRAZIONE\n\n"
								 + "0) Per aggiungere il film alla lista del cinema\n"
								 + "1) Per selezionare una sala e assegnarle un film\n"
								 + "2) Per visualizzare le statistiche del cinema\n"
								 + "3) Per aprire il cinema\n"
								 + "9) Per chiudere l'applicazione");
				scelta = sc.nextLine();
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
					
					case "9": System.out.println("Chiusura in corso..");
							  break;
					
					default:  System.out.println("Per favore inserisci solo le opzioni disponibili");
							  break;
				}
			}
			
			while(isOpen)
			{
				System.out.println("MENU CINEMA IN SERVIZIO\n\n"
								 + "0) Per selezionare una sala da gestire\n"
								 + "1) Per chiudere il cinema e passare alla sezione amministrativa\n"
								 + "9) Per chiudere l'applicazione");
				scelta = sc.nextLine();
				switch(scelta)
				{
					case "0": menuSala(scegliSala());
							  break;
							
					case "1": isOpen = false;
							  cinema.incassoTot();   	//l'incasso delle singole sale si deve azzerare dopo averlo sommato a quello del cinema.
							  cinema.getStatistiche().daOggiAIeri();
							  break;
					
					case "9": System.out.println("Chiusura in corso..");
							  break;
					
					default:  System.out.println("Per favore inserisci solo le opzioni disponibili");
							  break;
				}
			}
		}
	}
	
	//METODI CINEMA CHIUSO
	
	private static void creaCinema()						//metodo per creare il cinema
	{
		int nSale = 0;
		int nPostiSala = 0;
		String nomeSala = "";
		
		while(nSale <= 0)
		{
			System.out.println("Inserisci il numero delle sale che contiene il cinema");
			try
			{
				nSale = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
			}
			catch(NumberFormatException e)
			{
				System.out.println("Devi inserire dei numeri!!");
				nSale = 0;
			}
		}
		cinema = new Cinema(nSale);
		
		for(int i = 0; i < nSale; i++)
		{
			while(nomeSala.equals(""))						//continua fino a che la stringa equivale al niente
			{
				System.out.println("Inserisci il nome della sala");
				nomeSala = sc.nextLine();
			}
			
			while(nPostiSala <= 0)							//continuo fino a che la sala non contiene piÃ¹ di 0 posti
			{
				System.out.println("Inserisci il numero di posti che la sala contiene");
				try
				{
					nPostiSala = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));	//rimuovo tutti i caratteri dalla input dell'utente che non siano numeri e poi faccio il cast a Integer
				}
				catch(NumberFormatException e)	//se l'utente ha inserito valori errati il valore passato al casting sarÃ  vuoto e lancerÃ  un'eccezione, qui la gestisco con un syso e reimposto la variabile nPostiSala a 0 per evitare problemi
				{
					System.out.println("Devi inserire dei numeri!!");
					nPostiSala = 0;
				}
			}
			
			SalaCinematografica sala = new SalaCinematografica(nomeSala, nPostiSala);	//creo la sala cinematografica
			cinema.aggiungiSala(sala);													//la aggiungo al cinema
			nomeSala = "";
			nPostiSala = 0;
		}
		autoGenerateFilm();
		creaFilm();				//obbligo a creare film
		assegnaFilmSala();		//obbligo almeno alla prima volta ad assegnare dei film alle sale
	}

	private static void autoGenerateFilm() {
		Scanner in = null;
		try {
			in = new Scanner(new File("src/cinema/ListaFilm.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] args = line.split("\\|");
			if (line.charAt(0) == '#')
				continue; //E' un commento, salta.
			cinema.caricaFilm(new Film(args[0], args[1], args[2], Generi.values()[Integer.parseInt(args[3])-1], Integer.parseInt(args[4])));
		}
		
	}
	
	private static void creaFilm()   					//creo i film che verranno aggiunti all'array films di Cinema
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
		
		while(durata <= 0)	//richiedo la durata fino a che non Ã¨ maggiore di 0
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
		
		while(genere == null)	//richiedo il genere fino a che non Ã¨ diverso da null
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

	private static void assegnaFilmSala()				//assegno un film ad una sala
	{
		Film f = null;
		
		while(true)
		{
			
			while(true)
			{
				for(int i = 0; i < cinema.getIndexFilm(); i++)
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
				for(int i = 0; i < cinema.getIndexSala(); i++)
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
	
	private static void mostraStats()
	{
		//System.out.println(cinema.getStats().calcolaMaxSpettatoriFilm() + "\n" + cinema.getStats().calcolaMinSpettatoriFilm() + "\n" + cinema.getStats().calcolaStatsGeneri()); //se si vuole stampare tutto insieme
		//scommentare riga 82 classe statistiche per usare il syso sopra
		
		//ABILITARE SOTTO SE SI VUOLE STAMPARE UNA STATS ALLA VOLTA
		String scelta = "";
		while(!scelta.equalsIgnoreCase("9"))
		{
			System.out.println(
							   "0) Per visualizzare il film con piÃ¹ spettatori\n"
							 + "1) Per visualizzare il film con meno spettatori\n"
							 + "2) Per visualizzare le statistiche di un genere specifico\n"
							 + "3) Per visualizzare l'incasso del cinema"
							 + "9) Per tornare indietro");
			scelta = sc.nextLine();
			switch(scelta)
			{
				case "0": 	System.out.println(cinema.getStatistiche().calcolaMaxSpettatoriFilm());
						  	break;
					
				case "1": 	System.out.println(cinema.getStatistiche().calcolaMinSpettatoriFilm());
						  	break;
				
				case "2": 	Generi genere = null;
							while(genere == null)	//richiedo il genere fino a che non Ã¨ diverso da null
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
							System.out.println(cinema.getStatistiche().calcolaStatsGeneri());	//abilitare insieme al metodo in statistiche riga 29
							break;
				case "3":	System.out.println("L'incasso del cinema Ã¨: " + cinema.getIncasso() + "Â€");
				case "9":	
						  break;
			}
		}
		
	}
	
	//METODI CINEMA APERTO 
	
	private static SalaCinematografica scegliSala()
	{
		SalaCinematografica	[] saleCinema = cinema.getSale();
		int scelta = -1;
		while(scelta < 0 || scelta >= cinema.getIndexSala())
		{
			for(int i = 0; i < cinema.getIndexSala(); i++)
			{
				System.out.println(i+") " + saleCinema[i]);
			}
			
			System.out.println("Scegli la sala tra quelle sopra inserendo il numero corrispondente, se si va oltre si richiederÃ  l'inserimento");
			
			try
			{
				scelta = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
			}
			catch(NumberFormatException e)
			{
				System.out.println("Devi inserire dei numeri!!");
				scelta = -1;
			}
		}
		
		return saleCinema[scelta];
	}
	
	private static void menuSala(SalaCinematografica sala)			//serve a gestire le singole sale
	{
		String scelta = "";
		while(!scelta.equals("2"))
		{
			System.out.println("Menu sala: " + sala.getNome() + "\n"
							+  "0) Per far entrare uno spettatore\n"
							+  "1) Per far iniziare il film\n"
							+  "2) Per tornare al menu principale");
			scelta = sc.nextLine().replaceAll("\\D+", "");
			
			switch(scelta)
			{
				case "0": try
						  {
							  sala.consentiIngresso(creaSpettatore(sala));	//creo spettatore e controllo se puÃ² essere inserito nella sala
						  }
						  catch(Exception e)
						  {
							  System.out.println("Lo spettatore non puÃ² essere inserito perchÃ©: " + e); //faccio cosÃ¬ perchÃ© viene lanciata solo una exception generica, cosÃ¬ posso visualizzare il messaggio relativo all'exception
						  }
//				case "0": try
//						  {
//								if(sala.possoEntrare())					//controllo che vi sia posto
//								{
//									sala.consentiIngresso(creaSpettatore(sala));	//creo spettatore e controllo se puÃ² essere inserito nella sala
//								}
//				  
//						  }
//						  catch(Exception e)
//						  {
//							  System.out.println("Lo spettatore non puÃ² essere inserito perchÃ©: " + e); //faccio cosÃ¬ perchÃ© viene lanciata solo una exception generica, cosÃ¬ posso visualizzare il messaggio relativo all'exception
//						  }
						  break;
						
				case "1": routineInizioFilm(sala);
						  scelta = "2";
						  break;
				
				case "2": 
						  break;
				
				default:  System.out.println("Per favore inserisci solo le opzioni disponibili");
						  break;
			}
			
		}
		
		
		//metodo per spettatore
		//metodo per far partire il film
	}
	
	private static Spettatore creaSpettatore(SalaCinematografica sala)
	{
		Spettatore sp = null;
		String nome = "",
			   cognome = "";
		int idSpettatore = (int) (Math.random()*89999999 + 10000000);		//assolutamente da modificare perchÃ© deve essere univoco e contenuto nel cinema
		LocalDate dataNascita = null;
		int anno = 0;
		int mese = 0;
		int giorno = 0;
		Biglietto biglietto = null;
		
		while(nome.equals("") && cognome.equals(""))		//chiedo nome e cognome
		{
			System.out.println("Inserisci il nome");
			nome = sc.nextLine();
			System.out.println("Inserisci il cognome");
			cognome = sc.nextLine();
		}
		anno = setYear();										//chiedo anno di nascita
		mese = setMonth();										//chiedo mese di nascita
		giorno = setDay(anno, mese);							//chiedo giorno di nascita	
		dataNascita = LocalDate.of(anno, mese, giorno);			//creo la data di nascita
		
		biglietto = creaBiglietto(sala,dataNascita);
		
		sp = new Spettatore(nome, cognome, dataNascita, biglietto);
		return sp;
	}
	
	private static int setYear()
	{
		int year = 0;
		while(year<((LocalDate.now().getYear())-120) || year > LocalDate.now().getYear())
		{
			try
			{
				System.out.println("Inserisci l'anno");
				year = sc.nextInt();
				if(year <= ((LocalDate.now().getYear())-120))
				{
					throw new Exception("Hai inserito una data troppo vecchia");
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Inserire solo numeri grazie.");
				sc.nextLine();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		sc.nextLine();
		return year;
	}
	
	private static int setMonth()
	{
		int month = 0;
		while(month<1 || month>12)
		{
			try
			{
				System.out.println("Inserisci il mese in numero");
				month = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Inserire solo numeri grazie.");
				sc.nextLine();
			}
			
		}
		sc.nextLine();
		return month;
	}
	
	private static int setDay(int year, int month)
	{
		int day = 0;
		while(day == 0)
		{
			try
			{
				switch(month)
				{
					case 4: case 6: case 9: case 11:
												 	System.out.println("Inserisci il giorno tra 1 e 30 compresi");
												 	while(day<1 || day>30)
												 	{
												 		day = sc.nextInt();
												 	}
												 	break;
												 	 
					case 1: case 3: case 5: case 7:
					case 8: case 10: case 12:		System.out.println("Inserisci il giorno tra 1 e 31 compresi");
												 	while(day<1 || day>31)
												 	{
												 		day = sc.nextInt();
												 	}
												 	break;
												 	
					case 2: 						if(LocalDate.of(year, 01, 01).isLeapYear())
													{
														while(day <1 || day >29)
														{
															try
															{
																System.out.println("inserisci giorno tra 1 e 29");
																day = Integer.parseInt(sc.nextLine());
															}catch(Exception e)
															{
																day  = 0;
															} 
														}
													}
													else
													{
														while(day <1 || day >28)
														{
															try
															{
																System.out.println("inserisci giorno tra 1 e 28");
																day = Integer.parseInt(sc.nextLine());
															}catch(Exception e)
															{
																day  = 0;
															} 
														}
													}
				}	
			}
			catch(InputMismatchException e)
			{
				System.out.println("Inserire solo numeri grazie.");
				day = 0;
				sc.nextLine();
			}
		}
		sc.nextLine();
		return day;
	}
	
	private static Biglietto creaBiglietto(SalaCinematografica sala, LocalDate dataNascita)
	{
		Biglietto biglietto = null;
		int posizione = -1;
		float prezzo = 8;	//prezzo fisso
		while(posizione < 0 && posizione >= sala.getSpettatori().length)			//stampare l'array degli spettatori in sala e decidere i posti di conseguenza
		{
			try
			{
				System.out.println("Inserisci la posizione dello spettatore da 0 a " + (sala.getSpettatori().length-1));
				posizione = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
				if(sala.getSpettatori()[posizione] != null)							//prima di questo stampare array spettatori
				{
					System.out.println("Questa posizione Ã¨ giÃ  occupata");
					posizione = -1;
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Inserisci solo numeri grazie");
			}
		}
		
		
		
		biglietto = new Biglietto(posizione, prezzo);
		if(dataNascita.isAfter(LocalDate.now().minusYears(10)))		//controllo se lo spettatore ha MENO di 10 anni e ha diritto allo sconto
		{
			biglietto.calcolaRiduzioneBambini();
		}
		else if(dataNascita.isBefore(LocalDate.now().minusYears(65)))	//controllo se ha PIU' di 65 anni
		{
			biglietto.applicaRiduzioneAnziani();
		}
		return biglietto;
	}
	
	private static void routineInizioFilm(SalaCinematografica sala)
	{
		sala.calcolaIncasso();	//serve per aggiornare il valore dell'incasso della sala
		cinema.getStatistiche().aggiornaDati(sala.getSpettatori().length, sala.getFilmProiettato());	//aggiorno i dati delle statistiche con il numero degli spettatori e il film a cui sono associati
		sala.svuotaSala();		//svuoto la sala
		System.out.println("Film: " + sala.getFilmProiettato() + " finito e sala " + sala.getNome() + " svuotata.");
	}
	
	
}
