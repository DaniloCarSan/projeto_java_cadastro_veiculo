/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.models;

import com.danilocarreiro.carros.entities.CarEntity;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public interface ICarModel {
    public ResponseStatus<CarEntity> select(int carcode,String carChassi, String carPlaque); 
    public ResponseStatus<CarEntity> create(CarEntity carEntity); 
    public ResponseStatus<List<CarEntity>> list(); 
    public ResponseStatus<CarEntity> update(CarEntity carEntity);
    public ResponseStatus<CarEntity> delete(CarEntity carEntity);
}
