 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.controllers;

import com.danilocarreiro.carros.entities.CarEntity;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import com.danilocarreiro.carros.models.CarModel;
import com.danilocarreiro.carros.models.ICarModel;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class CarController {
    
    final ICarModel carModel = new CarModel();
    
    public ResponseStatus<CarEntity> select(int carcode,String carChassi, String carPlaque){
        return this.carModel.select(carcode,carChassi,carPlaque);
    }
    
    public ResponseStatus<CarEntity> create(CarEntity carEntity){
        return this.carModel.create(carEntity);
    }
    
    public ResponseStatus<List<CarEntity>> list(){
        return this.carModel.list();
    }
    
    public ResponseStatus<CarEntity> update(CarEntity carEntity){
        return this.carModel.update(carEntity);
    }
    
    public ResponseStatus<CarEntity> delete(CarEntity carEntity){
        return this.carModel.delete(carEntity);
    }
    
}
