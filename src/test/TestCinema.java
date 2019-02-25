package test;

import cinema.Cinema;
import cinema.Film;
import cinema.Generi;
import cinema.SalaCinematografica;

public class TestCinema 
{
	private static SalaCinematografica sala = new SalaCinematografica("Sala 15", 50);
	private static Cinema cinema =  new Cinema(1);
	private static Film film = new Film("Bambi", "Bulloni", "Feltrinelli", Generi.DRAMMATICO, 125);
	
	public static void main(String[] args)
	{
		cinema.aggiungiSala(sala);
		cinema.caricaFilm(film);
		cinema.incassoTot();
	}
}
