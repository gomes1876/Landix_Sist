/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldxps;

/**
 *
 * @author gabri
 */
public class Vendedor {
    
    
    private String cdven,dsnome, cdtab, dtnasc;
    
    public Vendedor(String cdven, String dsnome, String cdtab, String dtnasc){
    
        this.cdven=cdven;
        this.dsnome=dsnome;
        this.cdtab=cdtab;
        this.dtnasc=dtnasc;     
        
    }
    
    public String toString(){
    
        return dsnome;
    
    }
    
    public String getCdven(){
    
        return cdven;
    }

    /**
     * @return the dsnome
     */
    public String getDsnome() {
        return dsnome;
    }

    /**
     * @return the cdtab
     */
    public String getCdtab() {
        return cdtab;
    }

    /**
     * @return the dtnasc
     */
    public String getDtnasc() {
        return dtnasc;
    }
    
    
    
}
