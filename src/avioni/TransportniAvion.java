package avioni;
import tereti.*;
import vazdusni_prostor.MapaVazdusnogProstora;
import osobe.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Random;

import interfejsi.*;

public class TransportniAvion extends Avion implements Transporteri,CivilniObjekat
{
	private double maksimalnaTezina;
	private Teret tereti[]=new Teret[0];
	
	
	public TransportniAvion()
	{	
	}
	public TransportniAvion(double maksimalnaTezina,Teret tereti[])
	{
		this.maksimalnaTezina=maksimalnaTezina;
		this.tereti=tereti;
	}
	public TransportniAvion(MapaVazdusnogProstora mapa,HashMap<Integer,String>karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY)
	{
		super(mapa,"Herkules C-130", karakteristike, osobe, pozicijaX, pozicijaY);
	}
	public TransportniAvion(String model,Integer oznaka,Integer visina,Integer brzina,int pozicijaX,int pozicijaY) 
	{
		super(model,oznaka,visina,brzina,pozicijaX,pozicijaY);
	}

	public double getMaksimalnaTezina() {
		return maksimalnaTezina;
	}
	public void setMaksimalnaTezina(double maksimalnaTezina) {
		this.maksimalnaTezina = maksimalnaTezina;
	}
	public Teret[] getTereti() {
		return tereti;
	}
	public void setTereti(Teret[] tereti) {
		this.tereti = tereti;
	}
	@Override
	public String toString() 
	{
		return new StringBuilder().append("Transportni-Avion").append("#").append(super.toString()).toString();
	}
	@Override
	public void iscrtaj(Graphics graphic) {
		graphic.setColor(new Color(187,132,98));
		graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
		graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
				
	}
	
	


}
