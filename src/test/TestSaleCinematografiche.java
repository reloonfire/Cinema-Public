package test;

import java.time.LocalDate;
import java.util.Arrays;

import cinema.SalaCinematografica;
import cinema.Spettatore;
import cinema.Biglietto;
import cinema.Film;
import cinema.Generi;

public class TestSaleCinematografiche {

	public static SalaCinematografica c = new SalaCinematografica("SalaTest", 100);
	public static Spettatore sp = new Spettatore (33, "Test", "Cognome", LocalDate.of(1998, 4, 28),new Biglietto(2,5) );
    public static Film f = new Film("Titolo", "Autore", "Produttore",Generi.DRAMMATICO, 120);
	public static void main(String[] args) {
		c.setFilm(f);
		try {
			c.consentiIngresso(sp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.calcolaIncasso());
		c.azzeraIncasso();
		System.out.println();
		System.out.println(Arrays.toString(c.getSpettatori()));
		System.out.println(c);
		c.svuotaSala();
		System.out.println(c);
		
	}
	
}
