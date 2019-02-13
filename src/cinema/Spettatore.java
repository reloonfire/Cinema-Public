package cinema;

import java.time.LocalDate;//pacchetto per poter usere i LocalDate
import java.time.Period;//pacchetto per comparare le date

public class Spettatore {// inizio classe spettatore

	private int idSpettatore = 0;// attributi della classe
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private Biglietto biglietto;

	public Spettatore(String nome, String cognome, LocalDate dataNascita, Biglietto biglietto) {// inizio
																													// costruttore
		this.idSpettatore++;// valori attribuiti agli attributi della classe
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.biglietto = biglietto;
	}

	public int getIdSpettatore() {// inizio getter idSpettatore
		return idSpettatore;
	}// fine getter idSpettatore

	public String getNome() {// inizio getter nome
		return nome;
	}// fine getter nome

	public void setNome(String nome) {// inizio setter nome
		this.nome = nome;
	}// fine setter nome

	public String getCognome() {// inizio getter cognome
		return cognome;
	}// fine getter cognome

	public void setCognome(String cognome) {// inizio setter cognome
		this.cognome = cognome;
	}// fine setter cognome

	public LocalDate getDataNascita() {// inizio getter dataNascita
		return dataNascita;
	}// fine getter dataNascita

	public void setDataNascita(LocalDate dataNascita) {// inizio setter dataNascita
		this.dataNascita = dataNascita;
	}// fine setter dataNascita

	public Biglietto getBiglietto() {// inizio getter biglietto
		return biglietto;
	}// fine getter biglietto

	public void setBiglietto(Biglietto biglietto) {// inizio setter biglietto
		this.biglietto = biglietto;
	}// fine setter biglietto

	public int getEta() {//inizio getter età
		Period differenza = Period.between(LocalDate.now(), this.dataNascita);//Compara le 2 date
		return differenza.getYears();// fa ritornare gli anni di differenza
	}// fine metodo per ottenere età

	public boolean minorenne() {// inizio metodo minorenne
		if (getEta() >= 18) {// controllo se maggiorenne
			return false;
		} else {// se minorenne
			return true;
		} // termine else
	}// fine metodo minorenne

}// fine classe
