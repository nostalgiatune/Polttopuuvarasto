package polttopuuvarasto.tiedonsiirto;


/**
 *
 * @author Tuoppi
 */
public class Tuote {
    
    private String nimi;
    private String pituus;
    private String kuivaVarastossa;
    private String tuoreVarastossa;
    private String hinta;
    
    public Tuote() {
    }

    public Tuote(String nimi, String pituus, String kuivaVarastossa,
            String tuoreVarastossa, String hinta) {
        
        this.nimi = nimi;
        this.pituus = pituus;
        this.kuivaVarastossa = kuivaVarastossa;
        this.tuoreVarastossa = tuoreVarastossa;
        this.hinta = hinta;
    }

    public String getNimi() {
        return nimi;
    }

    public String getPituus() {
        return pituus;
    }

    public String isKuivaVarastossa() {
        return kuivaVarastossa;
    }

    public String isTuoreVarastossa() {
        return tuoreVarastossa;
    }

    public String getHinta() {
        return hinta;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setPituus(String pituus) {
        this.pituus = pituus;
    }

    public void setKuivaVarastossa(String kuivaVarastossa) {
        this.kuivaVarastossa = kuivaVarastossa;
    }

    public void setTuoreVarastossa(String tuoreVarastossa) {
        this.tuoreVarastossa = tuoreVarastossa;
    }

    public void setHinta(String hinta) {
        this.hinta = hinta;
    }
    
    
}