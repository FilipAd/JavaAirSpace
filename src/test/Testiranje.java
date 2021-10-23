package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import vazdusni_prostor.MapaVazdusnogProstora;

public class Testiranje {
	public static void main(String args[])
	{
	
			
		
				File putanja=new File(System.getProperty("user.dir")+File.separatorChar+"events");
				File fajlovi[]=putanja.listFiles();
				StringBuilder tekst=new StringBuilder();
				for(File fajl: fajlovi)
				{
					try
					{
					System.out.println(fajl.toString());
						Path filePath=Paths.get(new File("").getAbsolutePath()+File.separatorChar+"events"+File.separatorChar+fajl.getName());
						List<String> red=Files.readAllLines(filePath);
						red.stream().forEach(s->tekst.append(s).append("\n"));
						System.out.println(filePath.toString());
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				
				
			}
		}
		
	


