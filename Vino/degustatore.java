package Vino;

//NOTA: per controllare che ogni degustatore (solitamente) effettivamentr raggiungesse la botte ho stampato i nomi di tutti e quante volte hanno bevuto
//era carina come funzione e l'ho tenuta, contestualizzandola


class degustatore extends Thread {
	private botte rubinetto; //l'oggetto conteso dai thread
	public boolean haBevuto; //flag per far comunicare degustatore.java e botte.java
	public int bevute; //contatore delle bevute di ogni singolo thread (opzionale, v. nota)
	public degustatore(String nome, botte rubinetto) {
		super(nome); //ereditata dalla classe Thread
		this.rubinetto = rubinetto;
		haBevuto = false;
		bevute = 0;
		start();
	}

	public int getBevute() { //ci serve per stampare il numero di bevute
		return this.bevute;
	}

	public synchronized void run() {
		while (rubinetto.litri > 0) {//finch� nella botte c'� vino viene fatto questo ciclo
			synchronized(rubinetto) {
				try { sleep((int)(Math.random() * 200)); System.out.println(this.getName() + " si avvicina ai rubinetti.");
				} catch (InterruptedException e) {e.printStackTrace();}
				rubinetto.entra();
				//se il flag del thread � stato settato a true (= se ha bevuto), si controlla se c'� ancora vino
				//se c'�, il thread esce, altrimenti il ciclo viene interrotto
				if (haBevuto == true) {
					if (rubinetto.litri > 0) {						
						rubinetto.esce();
						try { sleep((int)(Math.random() * 200));
						} catch (InterruptedException e) {e.printStackTrace();}
					} else {
						rubinetto.litri = 0;
						break;
						}
				}
			}
		}
		// se il ciclo � stato interrotto (= se il vino � finito), facciamo entrare i thread nel metodo stampaBevute 
		//per sapere quante volte ciascuno ha bevuto (opzionale, v. nota)
		if (rubinetto.litri <= 0) {
			rubinetto.stampaBevute();
		}
	}
}