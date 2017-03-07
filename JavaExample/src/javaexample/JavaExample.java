package javaexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author alessandro
 */
public class JavaExample {

    private static final String FILENAMEINPUT = "C:\\fatture.txt";
    private static final String FILENAMEOUTPUT = "C:\\fatture_nuovo.txt";
   
    
    public static void main(String[] args) {
        
        // 1. lettura del file di input
        ArrayList<Fattura> fatture = Utils.readFile(FILENAMEINPUT);
        // 2. calcolo delle scadenze e aggiornamento dell'ArrayList fatture
        fatture = Utils.calcolaScadenza(fatture);
        // 3. ordinamento dell'ArrayList fatture in base alla data di scadenza
        fatture = Utils.ordinaFatture(fatture);
        // 4. scrittura del file di output in C:\\fatture_nuovo.txt
        Utils.writeFile(fatture, FILENAMEOUTPUT);
        
        
        
        //visualizziamo il log delle fatture
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        for(int i=0; i<fatture.size(); i++){
            try {
	    	Fattura f = fatture.get(i);
            	System.out.println("Fattura n. " + f.getNFattura());
            	System.out.println("Data fatt. " + df.format(f.getDataFattura()));
            	System.out.println("Mod. pag . " + f.getModalitaPagamento());
            	System.out.println("Scad fatt. " + df.format(f.getScadenza()));
            	System.out.println("---------------------------------------");
	    } catch (NullPointerException npe){
	        System.out.println("Dati mancanti nella fattura");
	    }
        }
    }
}
