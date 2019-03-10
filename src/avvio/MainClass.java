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
//VERSIONE FINALE
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
					
					case "3": isOpen = true;
							  cinema.getStatistiche().daOggiAIeri();
							  break;
					
					case "9": close();
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
							  cinema.incassoTot();
							  break;
					
					case "9": close();
							  break;
					
					default:  System.out.println("Per favore inserisci solo le opzioni disponibili");
							  break;
				}
			}
		}
	}
	
	public static void close() 								//chiude il programma
	{
		System.out.println("Chiusura in corso..");
		System.exit(0);
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
				nSale = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));			//accetto solo numeri
			}
			catch(NumberFormatException e)
			{
				System.out.println("Devi inserire dei numeri!!");
				nSale = 0;
			}
		}
		cinema = new Cinema(nSale);							//creo il cinema
		
		for(int i = 0; i < nSale; i++)
		{
			while(nomeSala.equals(""))						//continua fino a che la stringa equivale al niente
			{
				System.out.println("Inserisci il nome della sala");
				nomeSala = sc.nextLine().replaceAll("[^a-zA-Z]", "");		//accetto solo lettere maiuscole e minuscole
			}
			
			while(nPostiSala <= 0)							//continuo fino a che la sala non contiene più di 0 posti
			{
				System.out.println("Inserisci il numero di posti che la sala contiene");
				try
				{
					nPostiSala = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
				}
				catch(NumberFormatException e)	//se l'utente ha inserito valori errati il valore passato al casting sarÃ  vuoto e lancerÃ  un'eccezione, qui la gestisco con un syso e reimposto la variabile nPostiSala a 0 per evitare problemi
				{
					System.out.println("Devi inserire dei numeri!!");
					nPostiSala = 0;
				}
			}
			
			SalaCinematografica sala = new SalaCinematografica(nomeSala, nPostiSala);	//creo la sala cinematografica
			cinema.aggiungiSala(sala);													//la aggiungo al cinema
			nomeSala = "";				//riporto a default i valori per creare altre sale
			nPostiSala = 0;				//riporto a default i valori per creare altre sale
		}
		autoGenerateFilm();			//crea una lista di film presi dal file "ListaFilm.txt"
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
			autore = sc.nextLine().replaceAll("[^a-zA-Z]", "");
			produttore = sc.nextLine().replaceAll("[^a-zA-Z]", "");
		}
		
		while(durata <= 0)	//richiedo la durata fino a che non è maggiore di 0
		{
			System.out.println("Inserisci la durata in minuti");
			try
			{
				durata = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
			}
			catch(NumberFormatException e)											
			{
				System.out.println("Inserisci dei numeri!");
				durata = 0;
			}
		}
		
		while(genere == null)	//richiedo il genere fino a che non è diverso da null
		{
			Generi.stampaGeneri();	//stampo tutti i generi con indice per l'utente
			System.out.println("\nInserisci il numero relativo al genere da selezionare");
			try
			{
				genere = Generi.values()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))]; //provo ad assegnare un genere tramite tramite l'indice
			}
			catch(NumberFormatException e)
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
			
			while(f == null)
			{
				for(int i = 0; i < cinema.getIndexFilm(); i++)			//stampo i film con un indice numerico 
				{
					System.out.println(i + ") " + cinema.getFilm()[i].getTitolo());
				}
				
				System.out.println("Seleziona un film");
				try
				{
					f = cinema.getFilm()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))];		//accetto solo numeri
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
				for(int i = 0; i < cinema.getIndexSala(); i++)			//stampo le sale con un indice numerico
				{
					System.out.println(i + ") " + cinema.getSale()[i].getNome());
				}
				
				System.out.println("Seleziona una sala a cui assegnare il film");
				try
				{
					cinema.getSale()[Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""))].setFilm(f);	//accetto solo numeri
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
			System.out.println("Inserisci '0' se hai finito di assegnare i film alle sale");		//per concludere il metodo inserire '0'
			if(sc.nextLine().equals("0")) break;
		}
		
	}
	
	private static void mostraStats()		//permette di visualizzare le statistiche del cinema
	{
		System.out.println(cinema.getStatistiche().calcolaMaxSpettatoriFilm() + "\n" + cinema.getStatistiche().calcolaMinSpettatoriFilm() + "\n" + cinema.getStatistiche().calcolaStatsGeneri()); //se si vuole stampare tutto insieme
		
	}
	
	//METODI CINEMA APERTO 
	
	private static SalaCinematografica scegliSala()		//permetto di selezionare una sala da gestire
	{
		SalaCinematografica	[] saleCinema = cinema.getSale();
		int scelta = -1;
		int contaSaleConFilm = 0;
		while(scelta < 0 || scelta >= contaSaleConFilm)
		{
			for(int i = 0; i < cinema.getIndexSala(); i++)
			{
				if(saleCinema[i].getFilmProiettato() != null)
				{
					System.out.println(i+") " + saleCinema[i].getNome());		//stampo solo le sale che contengono un film
					contaSaleConFilm++;				//questa variabile serve per contare quante sale vengono stampate, altrimenti sarebbe possibile selezionare anche quelle non stampate
				}
			}
			
			System.out.println("Scegli la sala tra quelle sopra inserendo il numero corrispondente, se si va oltre si richiederÃ  l'inserimento");
			
			try
			{
				scelta = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));		//accetto solo numeri
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
							  System.out.println("Spettatore registrato e inserito nella sala correttamente");
						  }
						  catch(Exception e)
						  {
							  System.out.println("Lo spettatore non può essere inserito perché: " + e.getMessage()); //faccio cosÃ¬ perchÃ© viene lanciata solo una exception generica, cosÃ¬ posso visualizzare il messaggio relativo all'exception
						  }
						  break;
						
				case "1": routineInizioFilm(sala);		//serie di operazioni da fare per ogni film che inizia
						  scelta = "2";					//torno in automatico al menu principale
						  break;
				
				case "2": 
						  break;
				
				default:  System.out.println("Per favore inserisci solo le opzioni disponibili");
						  break;
			}
			
		}
		
	}
	
	private static Spettatore creaSpettatore(SalaCinematografica sala)		//creo uno spettatore
	{
		Spettatore sp = null;
		String nome = "",
			   cognome = "";
		LocalDate dataNascita = null;
		int anno = 0;
		int mese = 0;
		int giorno = 0;
		Biglietto biglietto = null;
		
		while(nome.equals(""))		//chiedo nome accettando solo lettere
		{
			System.out.println("Inserisci il nome");
			nome = sc.nextLine().replaceAll("[^a-zA-Z]", "");
		}
		
		while(cognome.equals(""))	//chiedo cognome accettando solo lettere
		{
			System.out.println("Inserisci il cognome");
			cognome = sc.nextLine().replaceAll("[^a-zA-Z]", "");
		}
		
		anno = setYear();												//chiedo anno di nascita
		mese = setMonth(anno);											//chiedo mese di nascita
		giorno = setDay(anno, mese);									//chiedo giorno di nascita	
		dataNascita = LocalDate.of(anno, mese, giorno);					//creo la data di nascita
		biglietto = creaBiglietto(sala,dataNascita);					//creo il biglietto per lo spettatore
		sp = new Spettatore(nome, cognome, dataNascita, biglietto);		//creo lo spettatore
		return sp;
	}
	
	private static int setYear()		//metodo per inserire anno di nascita con i vari controlli
	{
		int year = 0;
		while(year<((LocalDate.now().getYear())-120) || year > LocalDate.now().getYear())
		{
			try
			{
				System.out.println("Inserisci l'anno");
				year = Integer.parseInt(sc.nextLine());
				if(year <= ((LocalDate.now().getYear())-120))		//controllo se l'anno inserito è oltre i 120 anni fa
				{
					throw new Exception("Hai inserito una data troppo vecchia");
				}
			}
			catch(NumberFormatException | InputMismatchException e)
			{
				System.out.println("Inserire solo numeri grazie.");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		return year;
	}
	
	private static int setMonth(int year)		//metodo per inserire mese di nascita con i vari controlli
	{
		int month = 0;
		while((month<1 || month>12) || (year == LocalDate.now().getYear() && month > LocalDate.now().getMonthValue()))		//controllo che il mese sia tra 1 e 12 e che non venga inserito un mese non ancora passato nell'anno corrente
		{
			try
			{
				System.out.println("Inserisci il mese in numero");
				month = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException | InputMismatchException e)
			{
				System.out.println("Inserire solo numeri grazie.");
			}
			
		}
		return month;
	}
	
	private static int setDay(int year, int month)
	{
		int day = 0;
		while(day == 0 || (year == LocalDate.now().getYear() && month == LocalDate.now().getMonthValue() && day > LocalDate.now().getDayOfMonth())) //controllo che il giorno non sia 0 e che non venga inserito un giorno del futuro
		{
			try
			{
				day = 0;
				switch(month)
				{
					case 4: case 6: case 9: case 11:
												 	
												 	while(day<1 || day>30)
												 	{
												 			System.out.println("Inserisci il giorno tra 1 e 30 compresi");
															day = Integer.parseInt(sc.nextLine());
												 	}
												 	break;
												 	 
					case 1: case 3: case 5: case 7:
					case 8: case 10: case 12:		
												 	while(day<1 || day>31)
												 	{
												 		System.out.println("Inserisci il giorno tra 1 e 31 compresi");
														day = Integer.parseInt(sc.nextLine());
												 	}
												 	break;
												 	
					case 2: 						if(LocalDate.of(year, 01, 01).isLeapYear())
													{
														while(day <1 || day >29)
														{
															System.out.println("inserisci giorno tra 1 e 29");
															day = Integer.parseInt(sc.nextLine());
														}
													}
													else
													{
														while(day <1 || day >28)
														{
															System.out.println("inserisci giorno tra 1 e 28");
															day = Integer.parseInt(sc.nextLine());
														}
													}
				}	
			}
			catch(NumberFormatException | InputMismatchException e)
			{
				System.out.println("Inserire solo numeri grazie.");
			}
		}
		return day;
	}
	
	private static Biglietto creaBiglietto(SalaCinematografica sala, LocalDate dataNascita)		//metodo per creare il biglietto
	{
		Biglietto biglietto = null;
		int posizione = -1;
		float prezzo = 8;	//prezzo fisso
		while(posizione < 0 || posizione >= sala.getSpettatori().length)
		{
			try
			{
				System.out.println("Inserisci la posizione dello spettatore da 0 a " + (sala.getSpettatori().length-1) + " (compresi)");
				System.out.print("Visualizzo i posti in sala: \n[");		//stampo la sala con le sue posizioni libere e occupate
				for(int i = 0; i < sala.getSpettatori().length; i++)
				{
					if(sala.getSpettatori()[i] == null)
					{
						System.out.print("(Vuoto)");
					}
					else
					{
						System.out.print("(Occupato)");
					}
				}
				System.out.print("]");
				posizione = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
				if(sala.getSpettatori()[posizione] != null)							//controllo che la posizione non sia occupata
				{
					System.out.println("Questa posizione è già  occupata");
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
		else if(dataNascita.isBefore(LocalDate.now().minusYears(65)))	//controllo se lo spettatore ha PIU' di 65 anni e ha diritto allo sconto
		{
			biglietto.applicaRiduzioneAnziani();
		}
		return biglietto;
	}
	
	private static void routineInizioFilm(SalaCinematografica sala)		//routine di operazioni per ogni film avviato
	{
		sala.calcolaIncasso();																		//serve per aggiornare il valore dell'incasso della sala
		cinema.getStatistiche().aggiornaDati(sala.getNumeroSpettatori(), sala.getFilmProiettato());	//aggiorno i dati delle statistiche con il numero degli spettatori e il film a cui sono associati
		sala.svuotaSala();																			//svuoto la sala
		System.out.println("Il film '" + sala.getFilmProiettato().getTitolo() + "' è finito e la sala '" + sala.getNome() + "' è stata svuotata.");
	}
	
	
}
