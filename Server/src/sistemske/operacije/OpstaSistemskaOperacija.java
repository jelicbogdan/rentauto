/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import broker.BrokerBaze;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Lenovo
 */
public abstract class OpstaSistemskaOperacija {
    
    protected BrokerBaze broker;
    protected OpstiDomenskiObjekat odo;

    public OpstaSistemskaOperacija() {
        broker = new BrokerBaze();
    }

    protected void connect() throws Exception {
        broker.connect();
    }

    protected void disconnect() throws Exception {
        broker.disconnect();
    }

    protected abstract void operation() throws Exception;

    public void execute() throws Exception {
        connect();
        try {
            operation();
            broker.commit();
        } catch (Exception ex) {
            broker.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            disconnect();
        }
    }

    public OpstiDomenskiObjekat vratiOdo(){
        return this.odo;
    }
    
}
