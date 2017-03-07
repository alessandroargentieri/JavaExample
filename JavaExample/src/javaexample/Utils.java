package javaexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * class containing some business logic
 * @author alessandro
 */
public class Utils {
    
    /**
     * Method to read the file
     * @param = FILENAME 
     * @return ArrayList<Fattura>
     **/
    public static ArrayList<Fattura> readFile(String FILENAME){
        ArrayList<Fattura> listaFatture = new ArrayList<Fattura>();
        BufferedReader br = null;
	FileReader fr = null;
	try {
  	   fr = new FileReader(FILENAME);
	   br = new BufferedReader(fr);
	   String sCurrentLine;
	   br = new BufferedReader(new FileReader(FILENAME));
           System.out.println();
           System.out.println("*****LETTURA FILE********");
           System.out.println();
	   while ((sCurrentLine = br.readLine()) != null) {
	       System.out.println(sCurrentLine);
               String[] items = sCurrentLine.split("; ");
               if(items.length != 3){
                   System.out.println("Formato riga non corretto");
                   continue;
               }
               Integer nfattura;
               try {
                   nfattura = Integer.parseInt(items[0]);
               }catch(NumberFormatException nfe){
                   System.out.println("Errore: " + nfe.toString());
                   continue;
               }
               DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
               Date date = null;
               df.setLenient(false);
               try {
                  date = df.parse(items[1]);
               } catch (Exception e) {
                  System.out.println("Invalid date");
                  continue;
               }
               String modalitapagamento = items[2];
               if(!modalitapagamento.equals("DF") && !modalitapagamento.equals("DFFM") && !modalitapagamento.equals("DF60")){
                   System.out.println("Modalità pagamento non riconosciuta");
                   continue;
               }
               listaFatture.add(new Fattura(nfattura, date, modalitapagamento));
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
           System.out.println();
           System.out.println("*****FINE LETTURA FILE********");
           System.out.println();
	}
        return listaFatture;
    }
    
    
    /**
     * Method to update the arraylist (aggiunge la data di scadenza)
     * @param = inputArrayList 
     * @return ArrayList<Fattura>
     **/
    public static ArrayList<Fattura> calcolaScadenza(ArrayList<Fattura> inputArrayList){
    
        for(int i=0; i<inputArrayList.size(); i++){
           String code = inputArrayList.get(i).getModalitaPagamento();
           Date datafattura = inputArrayList.get(i).getDataFattura();
           Calendar cal = Calendar.getInstance();
           cal.setTime(datafattura);
           int month = cal.get(Calendar.MONTH);
           switch(code) {
              case "DF" :
                inputArrayList.get(i).setScadenza(datafattura);
                break; 
              case "DFFM" :
                  int day;
                  if(month==0 || month==2 || month==4 || month==6 || month==7 || month==9 || month==11)
                      day = 31;
                  else if(month==1)
                      day = 28;
                  else
                      day = 30;
                  cal.set(cal.get(Calendar.YEAR), month, day);
                  inputArrayList.get(i).setScadenza(cal.getTime());
                break; 
              case "DF60" :
                month = month + 2;  
                cal.set(cal.get(Calendar.YEAR), month, cal.get(Calendar.DAY_OF_MONTH));
                inputArrayList.get(i).setScadenza(cal.getTime());
           }
        }
        return inputArrayList;
    }
    
    
    /**
     * Method to update the arraylist (ordina le fatture in base alla scadenza)
     * @param = inputArrayList 
     * @return ArrayList<Fattura>
     **/
    public static ArrayList<Fattura> ordinaFatture(ArrayList<Fattura> inputArrayList){
        for(int i=0; i<inputArrayList.size(); i++){
            for(int j=i+1; j<inputArrayList.size(); j++){
                 Fattura prima = inputArrayList.get(i);
                 Fattura seconda = inputArrayList.get(j);
                 if(prima.compareTo(seconda)>0){
                     inputArrayList.set(j, prima);
                     inputArrayList.set(i, seconda);
                 }
            }
        }
        return inputArrayList;
    }
    
    
    
    /**
     * Method to write the output file
     * @param = inputArrayList, FILENAME 
     **/
    public static void writeFile(ArrayList<Fattura> fatture, String FILENAME){
        BufferedWriter bw = null;
	FileWriter fw = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	try {
 	    String content = "";
            for(int i=0; i<fatture.size(); i++){
                Fattura f = fatture.get(i);
                content = content + "" + f.getNFattura() + "; " + df.format(f.getDataFattura()) + "; " + df.format(f.getScadenza()) + "\r\n";
            }
            content.substring(0, content.length()-2);
            fw = new FileWriter(FILENAME);
	    bw = new BufferedWriter(fw);
	    bw.write(content);
            System.out.println("File salvato in " + FILENAME);
            System.out.println();
	} catch (IOException e) {
            System.out.print("Errore nel salvataggio dei dati: ");
            e.printStackTrace();
        } catch (Exception e){
            System.out.print("Errore generico: ");
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
    }
    
    
   
    
    
    
}
