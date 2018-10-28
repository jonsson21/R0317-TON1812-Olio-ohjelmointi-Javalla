package tehtava1;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tekstieditori2 {
	
	private final String TITLE = "Tekstieditori";
	private JFrame frame;
	private JTextArea textPane;
	private File openFile;
	private JTextField txtEtsi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					Tekstieditori2 window = new Tekstieditori2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tekstieditori2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(TITLE);
		frame.setBounds(100, 100, 847, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textPane = new JTextArea();
		textPane.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		textPane.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(textPane, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmAvaa.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				avaa();
			}
		});
		mnTiedosto.add(mntmAvaa);
		
		JMenuItem mntmTallennaNimell = new JMenuItem("Tallenna nimell\u00E4");
		mntmTallennaNimell.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		mntmTallennaNimell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				luo();
			}
		});
		mnTiedosto.add(mntmTallennaNimell);
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmTallenna.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tallenna();
			}
		});
		mnTiedosto.add(mntmTallenna);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sulje();
			}
		});
		mnTiedosto.add(mntmSulje);
		
		JMenuItem mntmSammuta = new JMenuItem("Sammuta");
		mntmSammuta.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/javax/swing/plaf/metal/icons/ocean/close-pressed.gif")));
		mntmSammuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sammuta();
			}
		});
		mnTiedosto.add(mntmSammuta);
		
		JMenu mnTykalut = new JMenu("Ty\u00F6kalut");
		menuBar.add(mnTykalut);
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmEtsi.setSelectedIcon(new ImageIcon(Tekstieditori2.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		mnTykalut.add(mntmEtsi);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmTietoaMeist = new JMenuItem("Tietoa meist\u00E4");
		mntmTietoaMeist.setIcon(new ImageIcon(Tekstieditori2.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		mntmTietoaMeist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					help();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmTietoaMeist);
		
		txtEtsi = new JTextField();
		txtEtsi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtEtsi.setText("");
				txtEtsi.setEnabled(true);
			}
		});
		txtEtsi.setText("Etsi");
		txtEtsi.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					etsi();
				}
			}
		});
		
		menuBar.add(txtEtsi);
		txtEtsi.setColumns(10);
		
		
	}
		
	public void etsi() {
				try {
					Scanner teksti = new Scanner(openFile);
					String hakusana = txtEtsi.getText();
					hakusana = hakusana.toLowerCase();
					txtEtsi.setText(hakusana);
					String rivi ="";
					while (teksti.hasNextLine()) {
							rivi += teksti.nextLine()+"\n";
					}
			        teksti.close();
					int match = rivi.indexOf(hakusana);
					
					textPane.setSelectionColor(Color.green);
			        textPane.setSelectionStart(match);
			        textPane.setSelectionEnd(match+hakusana.length());
					
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Tapahtui odottamaton virhe", "Virhe", JOptionPane.ERROR_MESSAGE);
				}	      
	 }
	 
	public void korvaa() {
		
	}			

	
	private void help() throws URISyntaxException {
		final URI uri = new URI("https://notepad-plus-plus.org/download/v7.5.9.html");
		open(uri);
	}
	private static void open(URI uri) {
	    if (Desktop.isDesktopSupported()) {
	      try {
	        Desktop.getDesktop().browse(uri);
	      } catch (IOException e) {}
	  }
	}
	
	private void avaa() {
		
		try {
            JFileChooser valitsija = new JFileChooser();
            valitsija.setDialogTitle("Valitse teksti tiedosto");
            valitsija.showOpenDialog(null);

        openFile = valitsija.getSelectedFile();
        if (openFile != null && !openFile.exists()) {
        	JOptionPane.showMessageDialog(null, "Tiedoston avaaminen epäonnistui, tiedostoa ei ole!", "Virhe", JOptionPane.ERROR_MESSAGE);
            openFile = null;
            return;
        }
        Scanner lukija = new Scanner(openFile);
        String tiedosto = "";
        while (lukija.hasNextLine()) {
            tiedosto += lukija.nextLine()+"\n";
        }
        lukija.close();
		textPane.setText(tiedosto);
		
		frame.setTitle(TITLE+" - "+openFile.getName());

        }catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void luo() {
		
		try {
			
			JFileChooser valitsija = new JFileChooser();
			valitsija.setDialogTitle("Tallenna uusi tiedosto");
			valitsija.showSaveDialog(null);
			
			openFile = valitsija.getSelectedFile();
			
			tallenna();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void tallenna() {

        try {
          
        	if(openFile==null) {
        		JOptionPane.showMessageDialog(null, "Tiedoston tallennus epäonnistui, ei valittua tiedostoa!", "Virhe", JOptionPane.ERROR_MESSAGE);
        		return;
        	}
            
			String tiedosto = textPane.getText();
        	Formatter form = new Formatter(openFile);
			form.format("%s", tiedosto);
			form.close();
			
			frame.setTitle(TITLE+" - "+openFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void sulje() {
		
	        if(openFile==null) {
	        		JOptionPane.showMessageDialog(null, "Tiedoston sulkeminen epäonnistui, ei valittua tiedostoa!", "Virhe", JOptionPane.ERROR_MESSAGE);
	        		return;
	        }
		 
	        try {
	        	int valinta = JOptionPane.showConfirmDialog(null,  "Haluatko tallentaa ennen sulkemista?", "Odota!", JOptionPane.YES_NO_OPTION);
	        	
	        	if (valinta == JOptionPane.YES_OPTION) {
	        		tallenna();
	        	}
	        	
	        	textPane.setText("");
	        	openFile = null;
	        	frame.setTitle(TITLE);
	        	
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	} 
	
	private void sammuta() {
		
			if(openFile==null) {
				System.exit(0);	
			}
 
			try {
				int valinta = JOptionPane.showConfirmDialog(null,  "Haluatko tallentaa ennen sammuttamista?", "Odota!", JOptionPane.YES_NO_OPTION);
    	
				if (valinta == JOptionPane.YES_OPTION) {
					tallenna();
				}
    	
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.exit(0);
	}

}
