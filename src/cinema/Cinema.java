package cinema;

import java.io.File;
import java.util.Scanner;

public class Cinema {

	private Film[] films = new Film[18]; // -variabile: array di film
	private SalaCinematografica[] sale = new SalaCinematografica[5]; // -variabile: array di sale
	private float incasso; // -variabile: incasso in float
	private int indexFilm = 0;
	private int indexSale = 0;

	public Cinema(SalaCinematografica[] sale) { // inizio costruttore di +Cinema
		this.sale = sale; // this applicato su variabile passata
	} // fine costruttore

	public void caricaFilm(Film film) { // inizio metodo +caricaFilm

		if (indexFilm >= films.length) {
			System.out.println("Numero di film massimo raggiunto! Non puoi metterne altri.");
		} else {
			films[indexFilm++] = film;
			System.out.println("Film aggiunto!");
		}

		/*
		 * int index = 0;
		 * 
		 * try { //inizio try File fileFilm = new File("src/Cinema/listaFilm.txt");
		 * //file listaFilm.txt Scanner sc = new Scanner(System.in); //creazione Scanner
		 * sc //Scanner in = new Scanner(System.in); sc = new Scanner(fileFilm);
		 * //Scanner a cui viene fatto passare il file
		 * 
		 * while(sc.hasNextLine()) { //inizio while(condizione: mentre nel file ci sono
		 * righe) String rigaFile = sc.nextLine(); //nuova riga del File letta if
		 * (rigaFile.charAt(0) == '#') //se la riga comincia con "#", saltarla e
		 * continuare continue; //usato per saltare un passaggio String arrParole[] =
		 * rigaFile.split("-"); //array di stringhe creato dalla separazione delle
		 * sezioni con - int k = Integer.parseInt(arrParole[3]); //conversione in int
		 * del valore nell'array all'index 3
		 * 
		 * for (int i = 0; i < Generi.values().length; i++) { if () {
		 * 
		 * } }
		 * 
		 * int j = Integer.parseInt(arrParole[4]); //conversione in int del valore
		 * nell'array all'index 3
		 * 
		 * //Film f = new Film(arrParole[0], arrParole[1], arrParole[2], k, j); errore
		 * //creazione di un oggetto film con costruttore //manca inizializzazione del
		 * genere qui //listaFilm[index++] = f; //aggiunta, tramite un index, del film
		 * nell'array di film System.out.println(rigaFile); //stampo della riga } //fine
		 * while(continuerà a stampare tutte le righe fin quando non finiranno nel file)
		 * sc.close(); //chiusura file } //fine try catch(Exception e) { //catch
		 * dell'exception e.printStackTrace(); //stampa dell'errore }
		 */ // fine catch
	} // fine metodo

	public void aggiungiSala(SalaCinematografica sala) { // inizio metodo +aggiungiSala

		if (indexSale >= sale.length) {
			System.out.println("Numero di sale masssimo raggiunto! Non puoi metterne altre.");
		} else {
			sale[indexSale++] = sala;
			System.out.println("Sala aggiunta!");
		}

	} // fine metodo

	public void incassoTot() { // inizio metodo +incassoTot
		for (int i = 0; i < sale.length; i++) { // inizio FOR
			incasso += sale[i].calcolaIncasso(); // somma gli incassi delle sale cinematografiche ( 5 nel nostro caso )
		} // fine FOR

		System.out.println("Ecco il tuo incasso totale del cinema: " + incasso); // visualizzazione incasso totale
	} // fine metodo

	public float getIncasso() { // inizio metodo +getIncasso
		return incasso; // ritorno dell'incasso
	} // fine metodo

	public SalaCinematografica[] getSale() { // inizio metodo +getSale
		return sale; // ritorno dell'array di sale
	} // fine metodo

	public Film[] getFilm() { // inizio metodo +getFilm
		return films; // ritorno dell'array di film
	} // fine metodo
} // fine classBody