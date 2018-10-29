package tehtava1;

import java.util.Scanner;

public class UI {
		
		public static void main(String[] args) {
			int i;
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
