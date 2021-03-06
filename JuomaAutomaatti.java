package tehtava1;

import java.util.Scanner;

public class JuomaAutomaatti {
	private int teetä;
	private int kahvia;
	private int kaakaota;

	public JuomaAutomaatti(int teetä, int kahvia, int kaakaota) {
		this.teetä = teetä;
		this.kahvia = kahvia;
		this.kaakaota = kaakaota;
	}

	public JuomaAutomaatti() {
		this.teetä = 50;
		this.kahvia = 50;
		this.kaakaota = 50;
	}

	public void valmistaKahvi(){
		if (kahvia >= 10) {
			kahvia -= 10;
			System.out.println(toStringKahvi());
		}
	}

	public void valmistaTee() {
		if (teetä >= 10) {
			teetä -= 10;
			System.out.println(toStringTee());
		}
	}

	public void valmistaKaakao() {
		if (kaakaota >= 10) {
			kaakaota -= 10;
			System.out.println(toStringKaakao());
		} 
	}

	public String toStringKahvi() {
		return "Odota hetki kahvia valmistetaan. Kahvia jäljellä: " + kahvia + " yksikköä.";

	}

	public String toStringTee() {
		return "Odota hetki teetä valmistetaan. Teetä jäljellä: " + teetä + " yksikköä.";

	}

	public String toStringKaakao() {
		return "Odota hetki kaakaota valmistetaan. Kaakaota jäljellä: " + kaakaota + " yksikköä.";

	}
	public boolean onnistuuko() {
		int onnistuu = (int) (Math.random() * 100+1);

		if(onnistuu <= 25) {
			return false;
		}else {
			return true;
		}
	}

	public static void main(String[] args){
		int i;
		@SuppressWarnings("resource")
		Scanner lukija = new Scanner (System.in);
		JuomaAutomaatti omaKone = new JuomaAutomaatti();

		for (i = 0; i != 4;) {
			System.out.println("*******Juoma-Automaatti*******\n\n1. Kahvi\n2. Tee\n3. Kaakao\n4. Lopeta\n\n******************************\n");
			int vastaus = lukija.nextInt();
			i = vastaus;
			if (omaKone.onnistuuko() == true) {
				if(vastaus == 1) {
				omaKone.valmistaKahvi();
				System.out.println();
				}
				if (vastaus == 2) {
				omaKone.valmistaTee();
				System.out.println();
				}
				if (vastaus == 3) {
				omaKone.valmistaKaakao();
				System.out.println();
				}
				if (vastaus == 4) {
				System.out.println("Lopetetaan ohjelma.");
				}
				if (vastaus < 1 || vastaus > 4) {
				System.out.println("Anna numero 1-4");
				System.out.println();
				}
			}else {
				System.out.println("Ei onnistu, kiitos kuitenkin maksustasi");
			}
		}
	}
}