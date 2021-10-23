package glavna_aplikacija;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.sun.javafx.scene.control.skin.LabelSkin;

import radar.Upozorenje;
import simulator.Simulacija;
import vazdusni_prostor.MapaVazdusnogProstora;

public class Ekran
{
private JFrame frame;
private String naslov;
private int sirina;
private int visina;
private Canvas canvas;
private boolean drugiKlik=false;
private Simulacija simulacija;
private MapaVazdusnogProstora mapa;
private JLabel labelNeprijateljUProstoru;
JButton buttonZabranaLetenja=new JButton("ZABRANA LETENJA");
private JLabel labelBespilotnaLetjelica;
private JLabel labelPutnickiAvion;
private JLabel labelProtivPozarniAvion;
private JLabel labelProtivPozarniHelikopter;
private JLabel labelPutnickiHelikopter;
private JLabel labelNeprijatelj;
private JLabel labelBombarder;
private JLabel labelLovac;
private JLabel labelTransportniAvion;
private JLabel labelTransportniHelikopter;
private JLabel labelLegenda;
private JLabel labelSlika2;
private JLabel labelSlika3;
private ImageIcon slika2;
private ImageIcon slika3;
private static FileHandler filehandler;
private JLabel labelBojaTransH;
private JLabel labelBojaTransA;
private JLabel labelBojaLovac;
private JLabel labelBojaBombarder;
private JLabel labelBojaNeprijatelj;
private JLabel labelBojaPutnickiH;
private JLabel labelBojaPPH;
private JLabel labelBojaPPA;
private JLabel labelBojaPutnickiA;
private JLabel labelBojaBespilotna;



static
{
	try
	{
		filehandler=new FileHandler(System.getProperty("user.dir")+File.separatorChar+"logger"+File.separatorChar+"Ekran.log",true);
		filehandler.setFormatter(new SimpleFormatter());
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}

public Ekran(String naslov,int sirina,int visina,Simulacija simulacija,MapaVazdusnogProstora mapa)  //konstruktor
{
	this.naslov=naslov;
	this.sirina=sirina;
	this.visina=visina;
	this.simulacija=simulacija;
	this.mapa=mapa;
	
	
	this.slika2=new ImageIcon(System.getProperty("user.dir")+File.separatorChar+"slike"+File.separatorChar+"2.jpg");
	this.labelSlika2=new JLabel(slika2);
	this.labelSlika2.setAlignmentY(JLabel.CENTER_ALIGNMENT);
	this.labelSlika2.setBounds(1005,20,195,180);
	
	this.slika3=new ImageIcon(System.getProperty("user.dir")+File.separatorChar+"slike"+File.separatorChar+"3.jpg");
	this.labelSlika3=new JLabel(slika3);
	this.labelSlika3.setAlignmentY(JLabel.CENTER_ALIGNMENT);
	this.labelSlika3.setBounds(1010,250,190,200);
	this.labelSlika3.setVisible(false);
	
	

	
	this.labelLegenda=new JLabel();
	this.labelLegenda.setBounds(1225,40,10,10);
	this.labelLegenda.setBackground(new Color(10,210,180));
	this.labelLegenda.setText("L E G E N D A");
	this.labelLegenda.setVerticalAlignment(SwingConstants.CENTER);
	this.labelLegenda.setHorizontalAlignment(SwingConstants.CENTER);
	this.labelLegenda.setOpaque(true);
	this.labelLegenda.setSize(500,500);
	this.labelLegenda.setVisible(true);
	
	this.labelNeprijateljUProstoru=new JLabel();
	this.labelNeprijateljUProstoru.setBackground(new Color(51,204,255));
	this.labelNeprijateljUProstoru.setOpaque(true);
	this.labelNeprijateljUProstoru.setText("PROSTOR NIJE NARUSEN");
	this.labelNeprijateljUProstoru.setHorizontalAlignment(SwingConstants.CENTER);
	this.labelNeprijateljUProstoru.setSize(new Dimension(100,50));
	this.labelNeprijateljUProstoru.setBounds(1050,550,300,50);//x,y,sirina,visina
	this.labelNeprijateljUProstoru.setVisible(true);
	
	this.labelBespilotnaLetjelica=new JLabel();
	this.labelBespilotnaLetjelica.setSize(new Dimension(100,50));//sirina,visina
	this.labelBespilotnaLetjelica.setBounds(1235,485,150,50);
	
	this.labelBespilotnaLetjelica.setVisible(true);
	
	this.labelPutnickiAvion=new JLabel();
	this.labelPutnickiAvion.setSize(new Dimension(100,50));
	this.labelPutnickiAvion.setBounds(1235,435,135,50);
	this.labelPutnickiAvion.setVisible(true);
	
	this.labelProtivPozarniAvion=new JLabel();
	this.labelProtivPozarniAvion.setSize(new Dimension(100,50));
	this.labelProtivPozarniAvion.setBounds(1235,385,135,50);
	this.labelProtivPozarniAvion.setVisible(true);
	
	this.labelProtivPozarniHelikopter=new JLabel();
	this.labelProtivPozarniHelikopter.setSize(new Dimension(100,50));
	this.labelProtivPozarniHelikopter.setBounds(1235,335,150,50);
	this.labelProtivPozarniHelikopter.setVisible(true);
	
	this.labelPutnickiHelikopter =new JLabel();
	this.labelPutnickiHelikopter.setSize(new Dimension(100,50));
	this.labelPutnickiHelikopter.setBounds(1235,285,150,50);
	this.labelPutnickiHelikopter.setVisible(true);
	
	this.labelNeprijatelj=new JLabel();
	this.labelNeprijatelj.setSize(new Dimension(100,50));
	this.labelNeprijatelj.setBounds(1235,235,150,50);
	this.labelNeprijatelj.setVisible(true);
	
	this.labelBombarder=new JLabel();
	this.labelBombarder.setSize(new Dimension(100,50));
	this.labelBombarder.setBounds(1235,185,150,50);
	this.labelBombarder.setVisible(true);
	
	this.labelLovac=new JLabel();
	this.labelLovac.setSize(new Dimension(100,50));
	this.labelLovac.setBounds(1235,135,150,50);
	this.labelLovac.setVisible(true);
	
	this.labelTransportniAvion=new JLabel();
	this.labelTransportniAvion.setSize(new Dimension(100,50));
	this.labelTransportniAvion.setBounds(1235,85,150,50);
	this.labelTransportniAvion.setVisible(true);
	
	this.labelTransportniHelikopter=new JLabel();
	this.labelTransportniHelikopter.setSize(new Dimension(100,50));
	this.labelTransportniHelikopter.setBounds(1235,35,150,50);
	this.labelTransportniHelikopter.setVisible(true);
	
	this.labelBespilotnaLetjelica.setText("Bespilotna letjelica");  //postavljanje teksta na labele
	this.labelPutnickiAvion.setText("Putnicki Avion");
	this.labelProtivPozarniAvion.setText("Protivpozarni Avion");
	this.labelProtivPozarniHelikopter.setText("Protivpozarni Helikopter");
	this.labelPutnickiHelikopter.setText("Putnicki Helikopter");
	this.labelNeprijatelj.setText("Neprijatelj");
	this.labelBombarder.setText("Bombarder");
	this.labelLovac.setText("Lovac");
	this.labelTransportniAvion.setText("Transportni Avion");
	this.labelTransportniHelikopter.setText("Transportni Helikopter");
	
	frame=new JFrame(naslov); //postavljamo naslov na naslovnu liniju
	frame.setSize(new Dimension(sirina,visina));
	frame.setResizable(true);
	frame.setLocationRelativeTo(null);  //postavljanje na centar ekrana
	frame.getContentPane().setBackground(Color.BLACK);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //pri zatvaranju prozora d se zatvori a ne minimizuje
	
	buttonZabranaLetenja.setSize(100,50);
	buttonZabranaLetenja.setBounds(1050,620,300,70);
	buttonZabranaLetenja.setVisible(true);
	
	JButton buttonSudari=new JButton();
	buttonSudari.setText("SUDAR");
	buttonSudari.setSize(100,50);
	buttonSudari.setBounds(1050,200,100,50);
	buttonSudari.setVisible(true);
	
	JButton buttonDogadjaji=new JButton();
	buttonDogadjaji.setText("DOGADJAJI");
	buttonDogadjaji.setSize(100,50);
	buttonDogadjaji.setBounds(1050,450,100,50);
	buttonDogadjaji.setVisible(true);
	
	frame.add(buttonZabranaLetenja);
	frame.add(buttonSudari);
	frame.add(buttonDogadjaji);
	frame.add(labelNeprijateljUProstoru);
	frame.add(this.labelBespilotnaLetjelica);
	frame.add(this.labelPutnickiAvion);
	frame.add(this.labelProtivPozarniAvion);
	frame.add(this.labelProtivPozarniHelikopter);
	frame.add(this.labelPutnickiHelikopter);
	frame.add(this.labelNeprijatelj);
	frame.add(this.labelBombarder);
	frame.add(labelLovac);
	frame.add(labelTransportniAvion);
	frame.add(labelTransportniHelikopter);
	frame.add(labelLegenda);
	frame.add(labelSlika2);
	frame.add(labelSlika3);
	
	
	
	buttonZabranaLetenja.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		if(!drugiKlik)
		{
			buttonZabranaLetenja.setText("VAZDUSNI PROSTOR ZATVOREN"); //ako je vec bila zabrana pa zelimo da otvorimo vazdusni prostor
			buttonZabranaLetenja.setBackground(Color.RED);
			mapa.setZabranaLetenja(true);
			drugiKlik=true;
			
		}
		else if(drugiKlik)
		{
			buttonZabranaLetenja.setText("VAZDUSNI PROSTOR OTVOREN"); //ako je vazdusni prosotor bio otvoren pa ga klikom zatvaramo 
			buttonZabranaLetenja.setBackground(new Color(51,204,255));
			synchronized (simulacija) 
			{
			mapa.setZabranaLetenja(false);
			simulacija.notify();
			}
			drugiKlik=false;
		}
		}
	});
	
	
	
	
	
	buttonSudari.addActionListener(new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			File putanja=new File(System.getProperty("user.dir")+File.separatorChar+"alert");
			File [] fajlovi=putanja.listFiles(); //svi fajlovi iz foldera upozorenja
			String tekst="";
			for(File fajl:fajlovi)
			{
				try {
					FileInputStream fis=new FileInputStream(new File("").getAbsolutePath()+File.separatorChar+"alert"+File.separatorChar+fajl.getName());
					ObjectInputStream citac=new ObjectInputStream(fis);
					Upozorenje upozorenje=new Upozorenje();
					upozorenje=(Upozorenje)citac.readObject();
					citac.close();
					fis.close();
					tekst=tekst+upozorenje+"\n";
				}
				catch(Exception ex)
				{
					log(ex);
					
				}
				
			}
			JFrame frameSudari=new JFrame();
			frameSudari.setSize(500,500);
			frameSudari.setLocationRelativeTo(null);
			JTextArea label=new JTextArea(tekst);
			label.setLineWrap(true);
			label.setWrapStyleWord(true);
			label.setEditable(false);
			JScrollPane scroll=new JScrollPane(label,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			frameSudari.add(scroll);
			frameSudari.setVisible(true);
			}
	
		
	});
	
	buttonDogadjaji.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			File putanja=new File(System.getProperty("user.dir")+File.separatorChar+"events");
			File fajlovi[]=putanja.listFiles();
			StringBuilder tekst=new StringBuilder();
			for(File fajl: fajlovi)
			{
				try
				{
					Path fajlPath=Paths.get(new File("").getAbsolutePath()+File.separatorChar+"events"+File.separatorChar+fajl.getName());
					List<String> red=Files.readAllLines(fajlPath);
					red.stream().forEach(s->tekst.append(s).append("\n"));
				}
				catch(Exception ex)
				{
					log(ex);
				}
			}
			JFrame frameDogadjaj=new JFrame();
			frameDogadjaj.setSize(500,500);
			frameDogadjaj.setLocationRelativeTo(null);
			JTextArea label=new JTextArea(tekst.toString().trim());
			label.setLineWrap(true);
			label.setWrapStyleWord(true);
			label.setEditable(false);
			JScrollPane scroll=new JScrollPane(label,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			frameDogadjaj.add(scroll);
			frameDogadjaj.setVisible(true);
		}
	});
	
	
	canvas=new Canvas();
	canvas.setPreferredSize(new Dimension(sirina,visina));
	canvas.setFocusable(false);
	frame.add(canvas);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setResizable(true);//
	frame.pack();
	frame.setVisible(true);
	
}

public void dodajLabelu()
{
	this.labelNeprijateljUProstoru.setText("NEPRIJATELJ U PROSTORU!!!"); //labela ukazuje da je neprijatelj u prostoru
	this.labelNeprijateljUProstoru.setBackground(Color.RED);
	this.labelNeprijateljUProstoru.setHorizontalAlignment(SwingConstants.CENTER);
	this.labelNeprijateljUProstoru.setOpaque(true);
	while(mapa.getNeprijateljUProstoru()) //dok je neprijatelj u prostoru,dugme za zabranu letenja je disable-ovano za klikanje
	{
		//try
		//{
		labelSlika3.setVisible(true);
		//Thread.sleep(2000);
		labelSlika3.setVisible(false);
		System.out.println("UZBUNA");
		buttonZabranaLetenja.setBackground(Color.RED);
		buttonZabranaLetenja.setText("U TOKU BORBA VAZDUH-VAZDUH");
		buttonZabranaLetenja.setEnabled(false);
		//}
		//catch(Exception ex)
		//{
		//	log(ex);
	//	}
		
	}
	this.labelNeprijateljUProstoru.setText(null); // kada neprijatelj nestane iz prostora dugme se ponovo moze stisnuti
	buttonZabranaLetenja.setEnabled(true);
	labelSlika3.setVisible(false);
	buttonZabranaLetenja.setText("VAZDUSNI PROSTOR OTVOREN");
	buttonZabranaLetenja.setBackground(new Color(51,204,255));
	labelNeprijateljUProstoru.setText("VAZDUSNI PROSTOR NIJE NARUSEN");
	labelNeprijateljUProstoru.setBackground(new Color(51,204,255));
}
public Canvas getCanvas() {
	return canvas;
	}

public JFrame getFrame() {
	return frame;
}

public void setFrame(JFrame frame) {
	this.frame = frame;
}
public static void log(Exception ex)
{
	Logger logger=Logger.getLogger(Ekran.class.getName());
	logger.addHandler(filehandler);
	logger.log(Level.WARNING,Ekran.class.getName(),ex);
		
}


}
