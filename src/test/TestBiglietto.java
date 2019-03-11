package test;

import cinema.Biglietto;

public class TestBiglietto 
{
	private static Biglietto biglietto = new Biglietto(20, 7);
	
	public static void main(String[] args) 
	{
		
		biglietto.applicaRiduzioneAnziani();
		biglietto.calcolaRiduzioneBambini();
		biglietto.getPrezzo();
		System.out.println(biglietto);
		
	}
}