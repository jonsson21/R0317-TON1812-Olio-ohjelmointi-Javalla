package Pelit;

import java.util.Scanner;

public class BlackJack {
/**
* T‰m‰ on Black Jack peli jossa pelaaja saa kortteja, jotka lasketaan yhteen. Pelaajan tavoitteena on saada mahdollissimman l‰helle 21 ja voittaa jakaja ilman ett‰ menee yli. Kun pelaaja on ei ota en‰‰ kortteja jakaja jakaa itselleen kortit ja katsotaan kumpi voitti. 
* @param pakka on taulukko, joka on aluksi tyhj‰.
* @param maat on kaikki maat.
* @param numerot on kaikki korttien arvot.
* @param sekoitus saa arvokseen arvotus kortin, joka sijoitetaan pakkaan ja t‰m‰ toistetaan kunnes kaikki kortit on sijoitettu pakkaan.
* @param mones on se sijainti pakassa ja aina kun otetaan kortti niin mones++, jotta seuraavaksi otetaan seuraava kortti pakasta. Aluksi otetaan paikka 0 kohdassa oleva kortti
* @param yhteensa on pelaajan k‰dess‰ olevien korttien summa.
* @param vali on v‰livaihe jossa string muoto kortinsta muutetaan int, jotta kortti voidaan lis‰t‰ k‰den summaan.
* @param jakaja on jakajan k‰si yhteens‰.
* @param assa k‰ytet‰‰n kun pelaaja saa ‰ss‰n joka on arvoltaan 10 ja t‰h‰n viel‰ lis‰t‰‰n ‰ss‰n normaali arvo 1, jos pelaajan k‰den arvo ylitt‰‰ 21 v‰hennet‰‰n k‰dest‰ 10 ja ‰ss‰n arvo on vain normaali 1.
* @param jakajaAssa on sama kuin assa, mutta jakajan eik‰ pelaajan.
* @param no on while loop joka toistuu niin kauan kunnes pelaaja syˆtt‰‰ arvon 2 eli j‰‰ tai pelaajan k‰den arvo on 21 jonka j‰lkeen jakaja jakaa itselleen kortit.
*/
	public static void main(String[] args) {
		int[] pakka = new int[52];
		String[] maat = { "\u2660", "\u2764", "\u2666", "\u2663" };
		String[] numerot = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		String no = "2";
		int mones = 0;
		int yhteensa = 0;
		int vali = 0;
		int jakaja = 0;
		int assa = 0;
		int jakajaAssa = 0;
		/**
		 * T‰ss‰ luodaan pakka taulukolle paikat 0-51.
		 */
		for (int i = 0; i < pakka.length; i++) {
			pakka[i] = i;
		}
		/**
		 * T‰ss‰ arvotaan kortti ja se sijoitetaan pakkaan, kunnes kaikki kortit on arvottu ja sijoitettu.
		 */
		for (int j = 0; j < pakka.length; j++) {
			int sekotus = (int) (Math.random() * pakka.length);
			pakka[j] = pakka[sekotus];
		}
		/**
		 * T‰ss‰ tulostetaan kortti.
		 */
		String maa = maat[pakka[mones] / 13];
		String numero = numerot[pakka[mones] % 13];
		System.out.println("Sinun korttisi");
		System.out.println(numero + " " + maa);
		/**
		 * T‰ss‰ muutetaan kuvakortit numeroiksi.
		 */
		if (numero == "A") {
			numero = "1";
			if (assa == 0) {
				assa = 10;
				yhteensa = yhteensa + assa;
				assa = 2;
			}
		}
		if (numero == "J" || numero == "Q" || numero == "K") {
			numero = "10";
		}
		/**
		 * T‰ss‰ String kortit muutetaan int jotta ne voidaan laskea k‰den arvo.
		 */
		vali = Integer.parseInt(numero);
		yhteensa = yhteensa + vali;
		/**
		 * Jos pelaajalla on ‰ss‰ ja k‰den arvo ylitt‰‰ 21 muutetaan ‰ss‰n arvo 11 -> 1.
		 */
		if (assa == 2 && yhteensa > 21) {
			yhteensa = yhteensa - 10;
			assa = 3;
		}
		/**
		 * Lis‰t‰‰n monennen arvoa, jotta seuraavaksi otetaan pakasta seuraava kortti.
		 */
		mones++;
		maa = maat[pakka[mones] / 13];
		numero = numerot[pakka[mones] % 13];
		System.out.println(numero + " " + maa);
		
		if (numero == "A") {
			numero = "1";
			if (assa == 0) {
				assa = 10;
				yhteensa = yhteensa + assa;
				assa = 2;
			}
		}
		if (numero == "J" || numero == "Q" || numero == "K") {
			numero = "10";
		}
		vali = Integer.parseInt(numero);
		yhteensa = yhteensa + vali;
		System.out.println();
		System.out.println("Sinulla on yhteens‰ " + yhteensa);

		if (yhteensa == 21) {
			System.out.println("Sait Black Jackin!");
			System.out.println();
			no = "3";
		}
		mones++;
		/**
		 * Kysyt‰‰n mit‰ pelaaja haluaa tehd‰.
		 * Valinnalla 1 pelaaja ottaa kortin.
		 * Valinnalla 2 pelaaja j‰‰ ja jakaja jakaa itselleen kortit.
		 * Valinnalla 3 tulostetaan pelin ohje.
		 * Jos pelaaja saa 21 jakaja jakaa itselleen kortit.
		 * Jos pelaaja saa yli 21 pelaaja h‰vi‰‰ ja peli loppuu.
		 * Jos pelaaja syˆtt‰‰ jotakin muuta kuin 1, 2 tai 3 pyydet‰‰n pelaajaa syˆt‰‰m‰‰n joku kyseisist‰ vaihtoehdoista.
		 */
		while (no == "2") {
			Scanner lukija = new Scanner(System.in);
			System.out.println("1. Ota kortti  -  2. J‰‰ - 3. Ohje");
			String valinta = lukija.nextLine();

			if (valinta.equals("1")) {
					maa = maat[pakka[mones] / 13];
					numero = numerot[pakka[mones] % 13];
					System.out.println();
					System.out.println(numero + " " + maa + " ");
					
					if (numero == "A") {
						numero = "1";
						if (assa == 0) {
							assa = 10;
							yhteensa = yhteensa + assa;
							assa = 2;
						}
					}
					if (numero == "J" || numero == "Q" || numero == "K") {
						numero = "10";
					}
					vali = Integer.parseInt(numero);
					yhteensa = yhteensa + vali;
					
					if (assa == 2 && yhteensa > 21) {
						yhteensa = yhteensa - 10;
						assa = 3;
					}
					mones++;
					System.out.println();
					System.out.println("Sinulla on yhteens‰ " + yhteensa);
					/**
					 * Jos pelaajan k‰den arvo ylitt‰‰ 21 peli loppuu.
					 */
					if (yhteensa > 21) {
						System.out.println("H‰visit pelin");
						System.exit(0);
					}
					if (yhteensa == 21) {
						System.out.println("Sait Black Jackin!");
						System.out.println();
						no = "3";
					}
			}
			/**
			 * Jos pelaaja syˆtt‰‰ arvon 2 muuttuu no arvo ja loop loppuu.
			 */
			if (valinta.equals(no)) {
				System.out.println();
				System.out.println();
				no = "3";
			}
			if (valinta.equals("3")) {
				System.out.println();
				System.out.println("Blackjack on suuresti ventti‰ muistuttava korttipeli, jossa tavoitteena on saada kahdella tai useammalla kortilla pelik‰si, jolla voittaa jakajan k‰den joko siten, \nett‰ pelaajan k‰den pistem‰‰r‰ on l‰hemp‰n‰ kahtakymment‰yht‰ kuin jakajan tai siten ett‰ jakajan k‰den pistem‰‰r‰ menee yli kahdenkymmenenyhden. \nBlackjackiss‰ ‰ss‰ on arvoltaan joko yksi tai yksitoista, kaikki kuvakortit kymmenen ja muut kortit nimellisarvonsa verran. Jakajan on otettava kortteja kunnes h‰nen k‰tens‰ arvo ylitt‰‰ 16.");
				System.out.println();
			}
			/**
			 * Jos pelaaja syˆtt‰‰ jonku muun arvon kuin 1, 2 tai 3 pyydet‰‰n pelaajaa syˆtt‰‰ joku kyseisist‰ arvoista.
			 */
			if (!valinta.equals("1") && !valinta.equals("2") && !valinta.equals("3")) {
				System.out.println("Valitse numero: 1, 2 tai 3");
			}
		}
		System.out.println("Jakajan kortit");
		/**
		 * Jakaja jakaa itselleen kortteja kunnes h‰nen k‰tens‰ arvo on yli 16.
		 * Jos jakan k‰den arvo on yli 17 ja h‰nell‰ on ‰ss‰ ja h‰n on h‰vi‰m‰ss‰ pelaajalle ‰ss‰n arvo muuttu 1 ja jakaja saa uuden mahdollisuuden voittaa pelaajan.
		 */
		for (jakaja = 0; jakaja < 17;) {
			maa = maat[pakka[mones] / 13];
			numero = numerot[pakka[mones] % 13];
			System.out.println(numero + " " + maa);
			
			if (numero == "A") {
				numero = "1";
				if (jakajaAssa == 0) {
					jakaja = jakaja + 10;
					jakajaAssa = 2;
				}
			}
			if (numero == "J" || numero == "Q" || numero == "K") {
				numero = "10";
			}
			vali = Integer.parseInt(numero);
			jakaja = jakaja + vali;
			
			if (jakajaAssa == 2 && jakaja > 17 && jakaja < yhteensa) {
				jakaja = jakaja - 10;
				jakajaAssa = 3;
			}
			mones++;
		}
		System.out.println();
		System.out.println("Jakajalla on yhteens‰ " + jakaja);

		if (jakaja == 21) {
			System.out.println("Jakaja sai Black Jackin!");
		}
		/**
		 * Tulostetaan pelin tulos, kuka voitti.
		 */
		if (yhteensa < jakaja && jakaja < 22) {
			System.out.println("H‰visit pelin");
		}
		if (yhteensa > jakaja || jakaja > 21) {
			System.out.println("Voitit pelin!");
		}
		if (yhteensa == jakaja) {
			System.out.println("Tasapeli");
		}
	}
}
