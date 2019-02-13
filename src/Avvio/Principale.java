package Avvio;

import java.util.Scanner;

//BOZZA    13/02/19
public class Principale 
{
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		creaCinema();
	}
	
	public static void creaCinema()
	{
		int numeroSale = 0;
		
		while(numeroSale == 0)
		{
			System.out.println("Quante sale vuoi inserire?");
			try
			{
				numeroSale = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));   //inserisci una stringa che viene poi trasformata in un int
				
			}catch(NumberFormatException e)
			{
				System.out.println("Devi inserire un numero");
			}
			
		}
		
		for(int i = 0; i < numeroSale; i++)
		{
			
		}
		
	}
}
