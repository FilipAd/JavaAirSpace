package avioni;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import interfejsi.Bombardovanje;
import interfejsi.VazduhVazduhBorba;
import interfejsi.VojniObjekat;
import osobe.Osoba;
import vazdusni_prostor.MapaVazdusnogProstora;

public class Lovac extends VojniAvion implements Bombardovanje,VazduhVazduhBorba
{
public Lovac()
{
}
public Lovac(String model,Integer oznaka,Integer visina,int brzina,int pozicijaX,int pozicijaY,boolean neprijateljski) {
	super(model,oznaka,visina,brzina,pozicijaX,pozicijaY,neprijateljski);
}

public Lovac(MapaVazdusnogProstora mapa ,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,boolean neprijateljski) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY,neprijateljski);
}
public Lovac(MapaVazdusnogProstora mapa,String model,HashMap<Integer,String> karakteristike,Osoba osobe[],int pozicijaX,int pozicijaY,int brzina,boolean neprijateljski) {
	
	super(mapa,model,karakteristike,osobe,pozicijaX,pozicijaY,brzina,neprijateljski);
	
}

public void bombarduj()
{
	System.out.println("Cilj na zemlji pogodjen!!!");
}
public void obori()
{
	System.out.println("Cilj u vazduhu pogodjen!!!");
}
@Override
public String toString() {
	return new StringBuilder().append("Lovac").append("#").append(super.toString()).toString().trim();

}
@Override
public void iscrtaj(Graphics graphic) {
	if(!getNeprijateljski()) {
		graphic.setColor(Color.RED);
		graphic.fillOval(5+pozicijaX*10, 5+pozicijaY*10, 10, 10);
		graphic.drawString(this.visina.toString(), 5+pozicijaX*10-4,5+pozicijaY*10-1);
		
	}
	else {
		super.iscrtaj(graphic);
		
	}
			
}
}
