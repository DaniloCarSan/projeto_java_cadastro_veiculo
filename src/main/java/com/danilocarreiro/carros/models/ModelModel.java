/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.models;

import com.danilocarreiro.carros.entities.ModelEntity;
import com.danilocarreiro.carros.entities.ModelEntityFields;
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
public class ModelModel implements IModelModel {
    
    private final static String SQL_LIST_CAR = "SELECT * FROM "+ModelEntityFields.table;

    @Override
    public ResponseStatus<List<ModelEntity>> list() {
       try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            Statement stmt = conn.createStatement(); 
        )
        {
            ArrayList<ModelEntity> models = new ArrayList<ModelEntity>();
            ResultSet resultSet = stmt.executeQuery(SQL_LIST_CAR);
            
            while (resultSet.next())
            {
                int modelCode = resultSet.getInt(ModelEntityFields.modelCode);
                String modelName = resultSet.getString(ModelEntityFields.modelName);
                ModelEntity modelEntity = new ModelEntity(modelCode,modelName);
                    
                models.add(modelEntity);    
            }
            
            return new ResponseStatus(true,true,"Modelos listados com sucesso !",models);
        }
        catch(Exception e)
        {
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        } 
    }
    
}
