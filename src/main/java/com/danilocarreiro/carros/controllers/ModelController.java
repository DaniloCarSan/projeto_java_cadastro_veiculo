/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.controllers;

import com.danilocarreiro.carros.entities.ModelEntity;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import com.danilocarreiro.carros.models.IModelModel;
import com.danilocarreiro.carros.models.ModelModel;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class ModelController {
    
    final IModelModel modelModel = new ModelModel();
    
    public ResponseStatus<List<ModelEntity>> list(){
        return this.modelModel.list();
    }
     
}
