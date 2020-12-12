/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.helpers;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class ResponseStatus<DTD> {
    
    // status da requisição
    public boolean requestStatus;
    // status da acção
    public boolean actionStatus;
    // mensagem
    public String message;
    // dados de retorno
    public DTD data;
    
    public ResponseStatus(boolean rS, boolean aS, String ms) {
       this.requestStatus = rS;
       this.actionStatus = aS;
       this.message = ms;
    }
   
    public ResponseStatus(boolean rS, boolean aS, String ms,DTD d) {
       this.requestStatus = rS;
       this.actionStatus = aS;
       this.message = ms;
       this.data =d;  
    }
    
    public boolean status(){
        return (this.requestStatus && this.actionStatus );  
    }
    
    public void setResquestStatus(boolean rS){
        this.requestStatus = rS;
    }
    
    public void setActionStatus(boolean aS){
        this.actionStatus = aS;
    }
    
    public void setMessage(String m){
        this.message = m;
    }
     
    public void setData(DTD d){
        this.data = d;
    }

}
