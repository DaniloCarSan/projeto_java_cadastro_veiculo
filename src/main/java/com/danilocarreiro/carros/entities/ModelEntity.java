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
public class ModelEntity {
    public  int modelCode;
    public  String modelName;
    
    public ModelEntity(int modelCode, String modelName) {
        this.modelCode = modelCode;
        this.modelName = modelName;
    }
    
    public ModelEntity( String modelName) {
        this.modelName = modelName;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
