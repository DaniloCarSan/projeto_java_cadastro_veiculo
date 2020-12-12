/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.entities;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class ManufacturerEntity {
    
    public  int manufactureCode;
    public  String manufactureName;

   
    public ManufacturerEntity(int manufactureCode,String manufactureName){
        this.manufactureCode = manufactureCode;
        this.manufactureName = manufactureName;
    }
    
    public ManufacturerEntity(String manufactureName){
        this.manufactureName = manufactureName;
    }

    public void setManufactureCode(int manufactureCode) {
        this.manufactureCode = manufactureCode;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }
}
