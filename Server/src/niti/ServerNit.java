/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Lenovo
 */
public class ServerNit extends Thread {
    
    private final List<Obrada> lista;
    private final ServerSocket serverSocket;
    private int port;

    public ServerNit() throws IOException {
        vratiPort();
        this.lista = new ArrayList();
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (serverSocket.isClosed() == false) {
            System.out.println("Cekaj nove korisnike");
            try {
                Socket klijentskiSoket = serverSocket.accept();
                Obrada klijentskaNit = new Obrada(klijentskiSoket);
                lista.add(klijentskaNit);
                klijentskaNit.start();
                System.out.println("Klijent se prijavio na sistem.");
            } catch (SocketException ex) {
                System.out.println("Ugasen. ");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        zaustavi();
    }

    public ServerSocket VratiSoket() {
        return this.serverSocket;
    }

    public void stopServer() throws IOException {
        serverSocket.close();
    }

    private void vratiPort() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "konfiguracija/server.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.port = Integer.parseInt(properties.getProperty("socket_port"));
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void zaustavi() {
        for (Obrada klijent : lista) {
            try {
                klijent.vratiSoket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
