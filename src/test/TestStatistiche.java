package test;

import cinema.Film;
import cinema.Generi;
import cinema.Statistiche;

public class TestStatistiche {

	public static void main(String[] args) {
		
		int indice=0;
		Film[] films = new Film[5];
		
		Film f1 = new Film("ciao","aut","pro",Generi.COMMEDIA,2000);
		films[indice++]=f1;
		Film f2 = new Film("ciao","aut","pro",Generi.EROTICO,545435);
		films[indice++]=f2;
		Film f3 = new Film("ciao","aut","pro",Generi.COMMEDIA,2447474);
		films[indice++]=f3;
		Film f4 = new Film("ciao","aut","pro",Generi.COMMEDIA,20666);
		films[indice++]=f4;
		Film f5 = new Film("ciao","aut","pro",Generi.COMMEDIA,202222);
		films[indice++]=f5;
		
		Statistiche stats = new Statistiche(films);
		
		stats.aggiornaDati(5877, f1);
		stats.aggiornaDati(54, f2);
		stats.aggiornaDati(6789, f3);
		stats.aggiornaDati(432, f4);
		stats.aggiornaDati(57689, f5);
		
		
		System.out.println(stats.calcolaMaxSpettatoriFilm());
		
		System.out.println("");
		
		System.out.println(stats.calcolaMinSpettatoriFilm());
		
		System.out.println("");
		
		System.out.println(stats.calcolaStatsGeneri());
		
		System.out.println("");
		
		stats.daOggiAIeri();
	}

}