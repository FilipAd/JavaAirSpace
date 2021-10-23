package vazdusni_prostor;
import leteciObjekti.*;
import avioni.*;

import java.util.HashMap;
import java.util.Map;

public class Polje 
{
private HashMap<Integer,LeteciObjekat> objekti;
private volatile boolean neprijateljPrisutan=false;
private volatile boolean sudar=false;
private VojniAvion neprijatelj;



public Polje(){
	
objekti=new HashMap<>();	
}

public synchronized void dodajObjekatNaPolje(LeteciObjekat objekat){
	
	if(objekat instanceof VojniAvion && !objekat.getKrajObjekta() && ((VojniAvion) objekat).getNeprijateljski()) //ako se postavlja neprijateljski avion
	{
		this.neprijateljPrisutan=true;
		neprijatelj=(VojniAvion)objekat;
	}

	if(objekti.containsKey(objekat.getVisina()))// ako je objekat na istoj visini kao vec neki postojeci i na istom polju 
	{
		LeteciObjekat l=objekti.get(objekat.getVisina());
		l.setKrajObjekta(true);
		objekat.setKrajObjekta(true);
		//objekti.put(objekat.getVisina(),objekat);
		this.sudar=true;
	}
	else
	{
		objekti.put(objekat.getVisina(),objekat);
	}
}

public synchronized void obrisiObjekatSaPolja(LeteciObjekat objekat){
	
	if(objekat instanceof VojniAvion && ((VojniAvion) objekat).getNeprijateljski())
	{
		this.neprijateljPrisutan=false;
	}
	objekti.remove(objekat.getVisina());
}

public HashMap<Integer, LeteciObjekat> getObjekti() {
	return objekti;
}

public void setObjekti(HashMap<Integer, LeteciObjekat> objekti) {
	this.objekti = objekti;
}

public boolean isNeprijateljPrisutan() {
	return neprijateljPrisutan;
}

public void setNeprijateljPrisutan(boolean neprijateljPrisutan) {
	this.neprijateljPrisutan = neprijateljPrisutan;
}

public boolean isSudar() {
	return sudar;
}

public void setSudar(boolean sudar) {
	this.sudar = sudar;
}

public VojniAvion getNeprijatelj() {
	return neprijatelj;
}

public void setNeprijatelj(VojniAvion neprijatelj) {
	this.neprijatelj = neprijatelj;
}
@Override
public String toString()
{
	StringBuilder stringbuilder=new StringBuilder();
	for(Map.Entry<Integer,LeteciObjekat> jedanPar : objekti.entrySet())
	{
		if(jedanPar.getValue()!=null && jedanPar.getValue().getKrajObjekta())
		{
			objekti.remove(jedanPar.getValue().getVisina()); //ako postoji na polju letjelica koja je zavrsila svoje letenje obrisi je
		}
		else
		{
			stringbuilder.append(jedanPar.getValue()).append(System.lineSeparator());
		}
	}
	return stringbuilder.toString().trim();
}

}
