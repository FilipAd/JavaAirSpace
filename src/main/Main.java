package main;

import filewatcher.AlertWatcher;
import filewatcher.ConfigWatcher;
import glavna_aplikacija.GlavnaAplikacija;
import informacije.PodaciIzVazdusnogProstora;
import radar.Radar;
import simulator.Simulacija;
import vazdusni_prostor.MapaVazdusnogProstora;
import zip_arhive.Bekapovanje;

public class Main {
	
	public static final int SIRINA_GLAVNOG_PROZORA=2000;
	public static final int VISINA_GLAVNOG_PROZORA=2000;
	
	public static void main(String[] args) {
		
		AlertWatcher alert=new AlertWatcher();
		PodaciIzVazdusnogProstora podaci=new PodaciIzVazdusnogProstora();
		MapaVazdusnogProstora mapa=new MapaVazdusnogProstora();
		Simulacija simulacija=new Simulacija(mapa);
		ConfigWatcher confWatcher=new ConfigWatcher(simulacija);
		Radar radar=new Radar(mapa,podaci,simulacija);
		GlavnaAplikacija aplikacija=new GlavnaAplikacija("Air.exe",SIRINA_GLAVNOG_PROZORA,VISINA_GLAVNOG_PROZORA,mapa,podaci,simulacija);
		Bekapovanje backup=new Bekapovanje();
		
		simulacija.start();
		radar.start();
		aplikacija.start();
		alert.start();
		confWatcher.start();
		backup.start();
	
	}

}

