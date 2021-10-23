package avioni;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.*;
import osobe.Osoba;
import vazdusni_prostor.MapaVazdusnogProstora;

public class Bombarder extends VojniAvion implements Bombardovanje
{
public Bombarder()
{
}
public Bombarder(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY,boolean neprijateljski) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY,neprijateljski);
}

public Bombarder(MapaVazdusnogProstora mapa ,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,boolean neprijateljski) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY,neprijateljski);
}
public Bombarder(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina,boolean neprijateljski) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY,brzina,neprijateljski);
	
}
public void bombarduj()
{
	System.out.println("Cilj na zemlji pogodjen");
}
public String toString()
{
	return new StringBuilder().append("Bombarder").append("#").append(super.toString()).toString().trim();
}
@Override
public void iscrtaj(Graphics graphic) {
	if(!getNeprijateljski()) {
		graphic.setColor(new Color(100,10,40));
		graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
		graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
	}
	else {
		super.iscrtaj(graphic);
		}
			
}
}
