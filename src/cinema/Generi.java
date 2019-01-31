package cinema;

public enum Generi  
{
		ANIMAZIONE(0),
		AVVENTURA(1),
		BIOGRAFICO(2),
		COMMEDIA(3),
		DOCUMENTARIO(4),
		DRAMMATICO(5),
		EROTICO(6),
		FANTASCIENZA(7),
		FANTASY(8),
		GUERRA(9),
		HORROR(10),
		MUSICAL(11),
		STORICO(12),
		THRILLER(13),
		WESTERN(14);
	
	private int index;
	
	Generi(int n)
	{
		index = n;
	}
	
	public int getIndex()
	{
		return index;
	}
	
}
