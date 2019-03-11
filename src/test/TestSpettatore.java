package test;

import java.time.LocalDate;

import cinema.Biglietto;
import cinema.Spettatore;

public class TestSpettatore {

	public static void main(String[] args) {

		Biglietto bill = new Biglietto(10, (float) 4.5);

		Spettatore spett = new Spettatore("Mario", "Rossi", LocalDate.of(2030, 5, 5), bill);

		Biglietto bill2 = new Biglietto(11, (float) 4.5);

		System.out.println(spett.getBiglietto());
		System.out.println(spett.getIdSpettatore());
		System.out.println(spett.getNome());
		System.out.println(spett.getCognome());
		System.out.println(spett.getEta());
		System.out.println(spett.minorenne());
		spett.setBiglietto(bill2);
		System.out.println(spett.getBiglietto());
	}
}
