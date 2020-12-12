/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.models;

import com.danilocarreiro.carros.entities.ManufacturerEntity;
import com.danilocarreiro.carros.entities.ManufacturerEntityFields;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class ManufacturerModel implements  IManufacturerModel{
    
    private final static String SQL_LIST_CAR = "SELECT * FROM "+ManufacturerEntityFields.table;
    
    @Override
    public ResponseStatus<List<ManufacturerEntity>> list() {
        try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            Statement stmt = conn.createStatement(); 
        )
        {
            ArrayList<ManufacturerEntity> manufactures = new ArrayList<ManufacturerEntity>();
            ResultSet resultSet = stmt.executeQuery(SQL_LIST_CAR);
            
            while (resultSet.next())
            {
                int manufactureCode = resultSet.getInt(ManufacturerEntityFields.manufacturerCode);
                String manufactureName = resultSet.getString(ManufacturerEntityFields.manufacturerName);
                ManufacturerEntity manufacturerEntity = new ManufacturerEntity(manufactureCode,manufactureName);  
                manufactures.add(manufacturerEntity);    
            }
            
            return new ResponseStatus(true,true,"Fabricantes listados com sucesso !",manufactures);
        }
        catch(Exception e)
        {
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        }
    }
    
}
