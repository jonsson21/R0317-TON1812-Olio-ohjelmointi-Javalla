package tehtava1;

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
		JuomaAutomaatti omaKone = new JuomaAutomaatti();
		omaKone.valmistaKahvi();
		omaKone.valmistaKahvi();

		omaKone.valmistaTee();
		omaKone.valmistaTee();
		omaKone.valmistaTee();
		omaKone.valmistaTee();
		omaKone.valmistaTee();
		omaKone.valmistaTee();
		}
}