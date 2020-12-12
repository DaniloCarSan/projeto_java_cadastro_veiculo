/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.helpers;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class Validators {
    
    public static DocumentListener getDocumentListener(Runnable call){
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               validate(); 
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }
            public void validate() {
                new Thread(call).start();
            }
        };
    }
    
    public static String validateText(String nickname){
        if(nickname.isEmpty())
        {
            return "Campo obrigatório !";
        }
        else if( ! nickname.matches("[a-z0-9A-Z]{4,}"))
        {
            return "Campo deve conter apenas letras e numeros !";
        }
        
       return null;
    }
    
    public static String validateYear(String year){
        
        if(year.isEmpty())
        {
            return "Campo obrigatório !";
        }
        else if( ! year.matches("[0-9]{4}"))
        {
            return "Campo inválido (YYYY)";
        }
        
        return null;
    }
    
    public static String validateChassi(String chassi){
        
        if(chassi.isEmpty())
        {
            return "Campo obrigatório !";
        }
        else if( ! chassi.matches("[a-z0-9A-Z]{1,}"))
        {
            return "Campo inválido (a-z0-9A-Z)";
        }
        
        return null;
    }
    
    public static String validatePlaque(String plaque){
        
        if(plaque.isEmpty())
        {
            return "Campo obrigatório !";
        }
        else if( ! plaque.matches("[a-zA-Z]{3}-[0-9]{4}"))
        {
            return "Campo inválido ( (a-zA-Z){3}-(0-9){4}";
        }
        
        return null;
    }

}
