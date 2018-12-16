package tietokantasovellus;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.mysql.jdbc.Statement;

public class UI2 {

	private JFrame frame;
	private static JTextField haku;
	private static JTextArea texteNimi;
	private static JTextArea textsNimi;
	private static JTextArea textIk�;
	private static JTextArea textJotai;	
	public static String eNimi[];
	public static String sNimi[] = new String [2];
	public static String Ik� = "";
	public static String Jotai = "";
	private JButton btnDeleteRow;
	public static Connection con;
	public static Statement stmt;
	private JButton btnAddRow;

	//Ajetaan ohjelman k�ynnistyess�
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI2 window = new UI2(); 			//Luodaan ikkuna
					window.frame.setVisible(true);		//Asetetaan se n�kyville
					//Napataan mahdolliset virheet 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UI2() throws SQLException {
		initialize();
		yhteys();
	}

	//Luodaan ikkunan sis�lt�
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("SQL Data Editor");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Alue johon rivien eNimi -tietue tulostetaan
		texteNimi = new JTextArea();
		texteNimi.setBounds(0, 36, 121, 314);
		frame.getContentPane().add(texteNimi);
		
		//Teksti jonka alle tulostetaan kaikki eNimi -tietueet
		JLabel lblEnimi = new JLabel("eNimi");
		lblEnimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnimi.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblEnimi);
		
		//Teksti jonka alle tulostetaan kaikki sNimi -tietueet
		JLabel lblSnimi = new JLabel("sNimi");
		lblSnimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSnimi.setBounds(121, 11, 46, 14);
		frame.getContentPane().add(lblSnimi);
		
		//Teksti jonka alle tulostetaan kaikki Ik� -tietueet
		JLabel lblIk = new JLabel("Ik\u00E4");
		lblIk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIk.setBounds(242, 11, 46, 14);
		frame.getContentPane().add(lblIk);
		
		//Teksti jonka alle tulostetaan kaikki Jotai -tietueet
		JLabel lblJotai = new JLabel("Jotai");
		lblJotai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblJotai.setBounds(363, 11, 46, 14);
		frame.getContentPane().add(lblJotai);
		
		//Alue johon rivien sNimi -tietue tulostetaan
		textsNimi = new JTextArea();
		textsNimi.setBounds(121, 36, 121, 314);
		frame.getContentPane().add(textsNimi);
		
		//Alue johon rivien Ik� -tietue tulostetaan
		textIk� = new JTextArea();
		textIk�.setBounds(242, 36, 121, 314);
		frame.getContentPane().add(textIk�);
		
		//Alue johon rivien Jotai -tietue tulostetaan
		textJotai = new JTextArea();
		textJotai.setBounds(363, 36, 121, 314);
		frame.getContentPane().add(textJotai);
		
		//Kentt� johon sy�tet��n teksti joka ajetaan SQL tietokannassa
		haku = new JTextField();
		haku.setBounds(10, 430, 365, 20);
		frame.getContentPane().add(haku);
		haku.setColumns(10);
		
		//Nappi jota painamalla ladataan tiedot -taulu tietokannasta. Jos tekstikentt� on tyhj� ladataan kaikki sis�lt� mutta jos kentt��n kirjoittaa esim. eNimi = 'Esimierkki'. Haetaan kaikki rivit joilla eNimi vastaa Esimerkki -hakusanaa.
		JButton btnLoadTable = new JButton("Load Table");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hakua();
			}
		});
		btnLoadTable.setBounds(385, 429, 89, 23);
		frame.getContentPane().add(btnLoadTable);
		
		//Nappi jota painamalla poistetaan tiedot -taulusta kaikki tekstikess� olevan kriteerin t�ytt�m�t rivit. esim. eNimi = 'Esimerkki'. Poistetaan kaikki rivit joilla eNimi vastaa Esimerkki -kriteerisanaa.
		btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poistoa();
			}
		});
		btnDeleteRow.setBounds(385, 395, 89, 23);
		frame.getContentPane().add(btnDeleteRow);
		
		//Nappi jota painamalla lis�t��n tiedot -tauluun uusi rivi tekstikent�ss� olevan tiedon mukaan. esim 'Pekka', 'Puup��', 52, 'P�tk�n paras kaveri'. Sy�tet��n Pekka tietueeseen eNimi, Puup�� tietueeseen sNimi, 52 Ik� tietueeseen ja P�tk�n paras kaveri Jotai tietueeseen.
		btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lisaysta();
			}
		});
		btnAddRow.setBounds(385, 361, 89, 23);
		frame.getContentPane().add(btnAddRow);
		
	}
	//Ajetaan ohjelmaa k�ynnist�ess�. Luodaan yhteys tietokantaan
	public static void yhteys() throws SQLException {
        con = (Connection) DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7270041", "sql7270041", "3BHeuRxanF");
        //Statement-olio, joka l�hett�� kyselyn SQL tietokantaan
        stmt = (Statement) con.createStatement();
	}
	//Ajetaan kun Load Table -nappia on painettu. Hakee tietokannasta rivit jotka vastaavat hakua ja tulostaa ne ikkunaan.
	public static void hakua() {
		String etuNimi = "";
        String sukuNimi = "";
        String vanhuus = "";
        String kuvaus = "";
        String where = "";
		try {
			String kymysys = haku.getText();
			if (kymysys != null && !kymysys.isEmpty()) {
				where = "WHERE ";
			}

            //Luodaan tulosjoukko, joka saa arvokseen kyselyn tulokset
            ResultSet rs = stmt.executeQuery("SELECT * FROM tiedot " + where + kymysys);

            if (con != null) {
            	//Tulostetaan konsoliin tieto ett� yhteys on luotu
                System.out.println("Yhteys muodostettu");
                @SuppressWarnings("unused")
				int i = 0;

                //Sijoitetaan saadut tietueet String -muuttujiin
                while(rs.next()) {

                    etuNimi += rs.getString(1) + "\n";
                    sukuNimi += rs.getString(2) + "\n";
                    vanhuus += rs.getString(3) + "\n";
                    kuvaus += rs.getString(4) + "\n";
                    i++;
                }
                //Tulostetaan saadut tietueet ikkunaan
                texteNimi.setText(etuNimi);
    	        textsNimi.setText(sukuNimi);
    			textIk�.setText(vanhuus);
    			textJotai.setText(kuvaus);
            }
        //Napataan mahdolliset virheet
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Ajetaan kun Delete Row -nappia on painettu. Poistaa tietokannasta rivit jotka vastaavat hakua.
	public static void poistoa() {
		try {
			String kymysys = haku.getText();

            //L�hetet��n p�ivitys tietokantaan
            @SuppressWarnings("unused")
			int rs = stmt.executeUpdate("Delete FROM tiedot WHERE " + kymysys);

          //Tulostetaan konsoliin tieto ett� yhteys on luotu sek� ikkunaan teksti "Rivin poisto onnistui!" -vaikka useampi rivi voidaankin poistaa kerralla.
            if (con != null) {
                System.out.println("Yhteys muodostettu");
                texteNimi.setText("Rivin poisto onnistui!");
    	        textsNimi.setText("");
    			textIk�.setText("");
    			textJotai.setText("");
            }
          //Napataan mahdolliset virheet     
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Ajetaan kun Add Row -nappia on painettu. Lis�t��n sy�tetyt tiedot tiedot -tietokantaan.
	public static void lisaysta() {
		try {
			String kymysys = haku.getText();

            //Luodaan tulosjoukko, johon saa arvokseen kyselyn tulokset
            @SuppressWarnings("unused")
			int rs = stmt.executeUpdate("INSERT INTO tiedot(eNimi, sNimi, Ik�, Jotai) VALUES (" + kymysys + ")");

          //Tulostetaan konsoliin tieto ett� yhteys on luotu sek� ikkunaan teksti "Rivin lis�ys onnistui!"
            if (con != null) {
                System.out.println("Yhteys muodostettu");
                texteNimi.setText("Rivin lis�ys onnistui!");
    	        textsNimi.setText("");
    			textIk�.setText("");
    			textJotai.setText("");
            }
          //Napataan mahdolliset virheet    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}