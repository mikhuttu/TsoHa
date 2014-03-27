package Mallit;

public class Kayttaja {
    private String tunnus;
    private String salasana;
    
    public Kayttaja(String tunnus, String salasana) {
        this.tunnus = tunnus;
        this.salasana = salasana;
    }
    
    public String getTunnus() {
        return this.tunnus;
    }
    
    public String getSalasana() {
        return this.salasana;
    }
    
    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }
    
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
}