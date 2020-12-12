/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.models;

import com.danilocarreiro.carros.entities.ManufacturerEntity;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public interface IManufacturerModel {
    public ResponseStatus<List<ManufacturerEntity>> list(); 
}
