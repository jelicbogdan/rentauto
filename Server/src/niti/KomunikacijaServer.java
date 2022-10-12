/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;

/**
 *
 * @author Lenovo
 */
public class KomunikacijaServer {
 
    private static KomunikacijaServer instance;
    private ServerNit serverNit;
    
    private KomunikacijaServer(){}
    
    public static KomunikacijaServer getInstance(){
        if(instance == null){
            instance = new KomunikacijaServer();
        }
        return instance; 
    }
    
    public void PokreniServer() throws IOException{
        if(serverNit == null || serverNit.isAlive() == false){
            serverNit = new ServerNit();
            serverNit.start();
        }
    }

    public void ZaustaviServer() throws IOException {
        if(serverNit!=null && serverNit.VratiSoket().isBound()){
            serverNit.stopServer();
        }
    }
    
}
