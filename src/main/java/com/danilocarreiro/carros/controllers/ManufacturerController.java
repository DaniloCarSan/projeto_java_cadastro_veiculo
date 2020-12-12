/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.controllers;

import com.danilocarreiro.carros.entities.ManufacturerEntity;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import com.danilocarreiro.carros.models.IManufacturerModel;
import com.danilocarreiro.carros.models.ManufacturerModel;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class ManufacturerController {
    
    final IManufacturerModel manufacturerModel = new ManufacturerModel();
    
    public ResponseStatus<List<ManufacturerEntity>> list(){
        return this.manufacturerModel.list();
    }
    
}
