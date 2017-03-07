package javaexample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * simple POJO containing the model object Fattura
 * @author alessandro
 */
public class Fattura implements Comparable<Fattura>{
    
    private Integer nfattura;
    private Date datafattura;
    private String modalitapagamento;
    private Date scadenza;
    
    public Fattura(){}
    
    public Fattura(Integer nfattura, Date datafattura, String modalitapagamento){
        this.nfattura = nfattura;
        this.datafattura = datafattura; 
        this.modalitapagamento = modalitapagamento;
    }
    
    public Integer getNFattura(){
        return this.nfattura;
    }
    
    public void setNFattura(Integer nfattura){
        this.nfattura = nfattura;
    }
    
    public Date getDataFattura(){
        return this.datafattura;
    }
    
    public void setDataFattura(Date datafattura){
        this.datafattura = datafattura;
    }
    
    public String getModalitaPagamento(){
        return this.modalitapagamento;
    }
    
    public void setModalitaPagamento(String modalitapagamento){
        this.modalitapagamento = modalitapagamento;
    }
    
    public Date getScadenza(){
        return this.scadenza;
    }
    
    public void setScadenza(Date scadenza){
        this.scadenza = scadenza;
    }
    
    @Override
    public int compareTo(Fattura o) {
       return getScadenza().compareTo(o.getScadenza());
    }
    
}
