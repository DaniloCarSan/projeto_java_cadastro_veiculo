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
public class CarEntity {

    public int carCode;
    public String carYear;
    public String carChassi;
    public String carPlaque;
    public int carModelCode;
    public int carManufacturerCode;
    public ManufacturerEntity manufacturerEntity;
    public ModelEntity modelEntity;
    
    public CarEntity(int carCode, String carYear, String carChassi, String carPlaque, int carModelCode, int carManufacturerCode) {
        this.carCode = carCode;
        this.carYear = carYear;
        this.carChassi = carChassi;
        this.carPlaque = carPlaque;
        this.carModelCode = carModelCode;
        this.carManufacturerCode = carManufacturerCode;
    }
   
    public CarEntity(String carYear, String carChassi, String carPlaque, int carModelCode, int carManufacturerCode) {
        this.carYear = carYear;
        this.carChassi = carChassi;
        this.carPlaque = carPlaque;
        this.carModelCode = carModelCode;
        this.carManufacturerCode = carManufacturerCode;
    }
  
    public void setCarCode(int carCode) {
        this.carCode = carCode;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public void setCarChassi(String carChassi) {
        this.carChassi = carChassi;
    }

    public void setCarPlaque(String carPlaque) {
        this.carPlaque = carPlaque;
    }

    public void setCarModelCode(int carModelCode) {
        this.carModelCode = carModelCode;
    }

    public void setCarManufacturerCode(int carManufacturerCode) {
        this.carManufacturerCode = carManufacturerCode;
    }
    
    public void setManufacturerEntity(ManufacturerEntity manufacturerEntity) {
        this.manufacturerEntity = manufacturerEntity;
    }

    public void setModelEntity(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }
    
   
}
