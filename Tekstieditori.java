package tehtava1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.Color;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Tekstieditori extends JFrame {

	private JPanel contentPane;
	protected JTextComponent editorPane;
	private File openFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tekstieditori frame = new Tekstieditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	
	private void avaa() {
		
		try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Valitse teksti tiedosto");
            chooser.showOpenDialog(null);

        openFile = chooser.getSelectedFile();
        if (!openFile.exists()) {
        	JOptionPane.showMessageDialog(null, "Tiedoston avaaminen epäonnistui, tiedostoa ei ole!", "Error", JOptionPane.ERROR_MESSAGE);
            openFile = null;
            return;
        }
        Scanner lukija = new Scanner(openFile);
        String tiedosto = "";
        while (lukija.hasNextLine()) {
            tiedosto += lukija.nextLine()+"\n";
        }
        lukija.close();
		JTextComponent textArea = null;
		textArea.setText(tiedosto);
		
		openFile = openFile;

        }catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void luo() {
		
		try {
			
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Tallenna uusi tiedosto");
			chooser.showSaveDialog(null);
			
			openFile = chooser.getSelectedFile();
			
			tallenna();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void tallenna() {

        try {
          
        	if(openFile==null) {
        		JOptionPane.showMessageDialog(null, "Tiedoston tallennus epäonnistui, ei valittuia tiedostoa!", "Error", JOptionPane.ERROR_MESSAGE);
        		return;
        	}
            
        	JTextComponent textArea = null;
			String tiedosto = textArea.getText();
        	Formatter form = new Formatter(openFile);
			form.format("%$", tiedosto);
			form.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public Tekstieditori() {
		Title("Tekstieditori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaa();
			}
		});
		mntmAvaa.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luo();
			}
		});
		mntmTallenna.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmLopeta.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
		mnTiedosto.add(mntmLopeta);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnTiedosto.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sisalto = editorPane.getText();
				sisalto = sisalto.toLowerCase();
				
				String haettava = "auto";
				int indeksi = sisalto.indexOf(haettava);
				System.out.println("Indeksi: "+indeksi);
				
				editorPane.setSelectionColor(Color.YELLOW);
				
				editorPane.setSelectionStart(indeksi);
				editorPane.setSelectionEnd(indeksi + haettava.length());
			}
		});
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmEtsi.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				Scanner lukija = null;
			}
		});
		mntmKorvaa.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");		
		btnNewButton_2.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Cut-Black.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnNewButton_2);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		
	}
}