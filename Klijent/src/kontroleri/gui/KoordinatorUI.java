/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

/**
 *
 * @author Lenovo
 */
public class KoordinatorUI {

    private static KoordinatorUI instance;
    private KontrolerGlavnaForma glavna;
    
    private KoordinatorUI() {
    }

    public static KoordinatorUI getInstance() {
        if (instance == null) {
            instance = new KoordinatorUI();
        }
        return instance;
    }

    public void frmGlavna() {
        if (glavna == null) {
            glavna = new KontrolerGlavnaForma();
        }
        glavna.prikaziGlavnu();
    }
    public void frmPrijava(){
        if (glavna == null) {
            glavna = new KontrolerGlavnaForma();
        }
        glavna.frmPrijava();
    }

    

    
    
    
}

