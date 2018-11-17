package tehtava1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;


public class Sanakirja {
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		String sana = "";
		String sana2 = "";
		String sana3 = "";
		HashMap<String, String> parit = new HashMap<>();
		
		for (int o = 0; o < suomi.length; o++) {
			parit.put(suomi[o], englanti[o]);
		}
		
		Scanner lukija = new Scanner(System.in);
		Iterator<Entry<String, String>> ite = parit.entrySet().iterator();
		while (ite.hasNext()) {
			HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) ite.next();
		}
		
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("K��nn�s.xml")));
		}catch (FileNotFoundException e) {
			FileOutputStream tiedosto = new FileOutputStream("K��nn�s.xml");
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));
			enc.writeObject(parit);
			enc.close();
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("K��nn�s.xml")));
		}
		
		parit = (HashMap<String, String>) decoder.readObject();
		System.out.print("Sanakirjan sis�lt�: ");
		System.out.println(parit);
		
		while (sana != null) {
		
			System.out.println("Mink� sanan k��nn�ksen haluat tiet��? (kirjoita \"ohje\" n�hd�ksesi ohjeen) ");
			sana = lukija.nextLine();
			
			if (sana.equals("")) {
				System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!");
				System.exit(0);
			}
			
			if (sana.equals("ohje")) {
				System.out.println("Kirjoita \"+\" lis�t�ksesi k��nn�ksi�.");
				System.out.println("Tyhj� komento lopettaa ohjelman.");
				System.out.println("Kirjoita \"s\" tallentaaksesi uudet k��nn�kset.");
				System.out.println("Kirjoita \"p\" tulostaaksesi kaikki k��nn�kset.");
				System.out.println();
			}
			
			if (sana.equals("p")) {
				System.out.println("K��nn�kset:");
				Iterator<Entry<String, String>> it = parit.entrySet().iterator();
				while (it.hasNext()) {
					HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) it.next();
					System.out.println(alkio.getKey() + " = " + alkio.getValue());
				}
				System.out.println();
			}
			
			if (sana.equals("s")){
				FileOutputStream tiedosto = new FileOutputStream("K��nn�s.xml");
				XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));
				enc.writeObject(parit);
				enc.close();

				
				System.out.println("K��nn�kset tallennettu!");
				System.out.println();
			}
			
			if (parit.containsKey(sana)) {
				System.out.println("Sanan \"" + sana + "\" k��nn�s on \"" + parit.get(sana) + "\"" );
			}
			
			if (!parit.containsKey(sana) && !sana.equals("+") && !sana.equals("ohje") && !sana.equals("s") && !sana.equals("p")) {
				System.out.println("Sanan \"" + sana + "\" k��nn�s on tuntematon!");	
			}
			
			if (sana.equals("+")) {
				while (sana2 != null && sana3 != null) {
					if (sana.equals("+")) {
						System.out.println("Sana alkukielell�? (tyhj� lopettaa) "); 
						sana2 = lukija.nextLine();
						if (sana2.equals("")) {
							break;
						}
						System.out.println("Sana k��nnettyn�? (tyhj� lopettaa) "); 
						sana3 = lukija.nextLine();
						if (sana3.equals("")) {
							break;
						}
						parit.put(sana2, sana3);
					}	
				}
			}
		}
	}
}