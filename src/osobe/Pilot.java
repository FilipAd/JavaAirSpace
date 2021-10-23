package osobe;

public class Pilot extends Osoba 
{
private String letackaLicenca;	


public Pilot()
{
}
public Pilot(String letackaLicenca)
{
	this.letackaLicenca=letackaLicenca;
}


public String getLetackaLicenca() {
	return letackaLicenca;
}


public void setLetackaLicenca(String letackaLicenca) {
	this.letackaLicenca = letackaLicenca;
}

}
