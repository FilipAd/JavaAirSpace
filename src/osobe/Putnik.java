package osobe;

public class Putnik extends Osoba
{
private String brojPasosa;

public Putnik()
{
}
public Putnik(String brojPasosa)
{
	this.brojPasosa=brojPasosa;
}
public String getBrojPasosa() {
	return brojPasosa;
}
public void setBrojPasosa(String brojPasosa) {
	this.brojPasosa = brojPasosa;
}

}
