/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
        
        
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        for(int i=0; i<fatture.size(); i++){
            Fattura f = fatture.get(i);
            System.out.println("Fattura n. " + f.getNFattura());
            System.out.println("Data fatt. " + df.format(f.getDataFattura()));
            System.out.println("Mod. pag . " + f.getModalitaPagamento());
            System.out.println("Scad fatt. " + df.format(f.getScadenza()));
            System.out.println("---------------------------------------");
        }
        
        
        
        
        /*
        
        
        
        	BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
        			if (br != null)
                			br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
                
                // Salvare un nuovo file!
                BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = "This is the content to write into file\n";

			fw = new FileWriter(FILENAMEWRITE);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

                
          */      
                
    }
}
