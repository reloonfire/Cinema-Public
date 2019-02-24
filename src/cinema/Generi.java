package cinema;

public enum Generi  
{	
		ANIMAZIONE,
		AVVENTURA,
		BIOGRAFICO,
		COMMEDIA,
		DOCUMENTARIO,
		DRAMMATICO,
		EROTICO,
		FANTASCIENZA,
		FANTASY,
		GUERRA,
		HORROR,
		MUSICAL,
		STORICO,
		THRILLER,
		WESTERN;
	
	public static void stampaGeneri()
	{
		for(int i = 0; i < Generi.values().length; i++)
		{
			System.out.println(i+") " + Generi.values()[i]);
		}
	}
	
}