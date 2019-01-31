package cinema;

public enum Generi  
{
		ACTION(0),
		HORROR(1);
	
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
