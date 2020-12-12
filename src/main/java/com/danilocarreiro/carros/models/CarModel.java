/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.models;

import com.danilocarreiro.carros.entities.CarEntity;
import com.danilocarreiro.carros.entities.CarEntityFields;
import com.danilocarreiro.carros.entities.ManufacturerEntity;
import com.danilocarreiro.carros.entities.ManufacturerEntityFields;
import com.danilocarreiro.carros.entities.ModelEntity;
import com.danilocarreiro.carros.entities.ModelEntityFields;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class CarModel implements  ICarModel{
    
    private final static String SQL_CREATE_CAR = "INSERT INTO "+CarEntityFields.table+"("+CarEntityFields.carYear+","+CarEntityFields.carChassi+","+CarEntityFields.carPlaque+","+CarEntityFields.carManufacturerCode+","+CarEntityFields.carModelCode+")VALUES(?,?,?,?,?)";
    private final static String SQL_UPDATE_CAR = "UPDATE "+CarEntityFields.table+" SET "+CarEntityFields.carYear+ " = ?,"+CarEntityFields.carChassi+ " = ?,"+CarEntityFields.carPlaque+ " = ?,"+CarEntityFields.carManufacturerCode+ " = ?,"+ CarEntityFields.carModelCode+ " = ? WHERE "+CarEntityFields.carCode + "= ? LIMIT 1";
    private final static String SQL_DELETE_CAR = "DELETE FROM "+CarEntityFields.table+" WHERE "+CarEntityFields.carCode + " = ? LIMIT 1";
    private final static String SQL_LIST_CAR = "SELECT * FROM "+CarEntityFields.table+" INNER JOIN "+ManufacturerEntityFields.table+" ON "+CarEntityFields.carManufacturerCode+" = "+ManufacturerEntityFields.manufacturerCode +" INNER JOIN "+ModelEntityFields.table+" ON "+CarEntityFields.carModelCode+" = "+ModelEntityFields.modelCode;

    @Override
    public ResponseStatus<CarEntity> select(int carcode, String carChassi, String carPlaque) {
        
        String sql = SQL_LIST_CAR;
        
        if(carcode != 0 && carChassi == null  && carPlaque == null && carPlaque == null)
        {
            sql += " WHERE "+CarEntityFields.carCode + " = ? LIMIT 1";
        }
        
        if(carChassi != null)
        {
            if(carcode != 0)
            {
                sql += " WHERE "+CarEntityFields.carChassi +" = ? AND "+CarEntityFields.carCode + " not in (?) LIMIT 1";
            }
            else
            {
                sql += " WHERE "+CarEntityFields.carChassi +" = ? LIMIT 1";
            }
          
        }
        
        if(carPlaque != null)  
        {
            if(carcode != 0)
            {
                 sql += " WHERE "+CarEntityFields.carPlaque +" = ? AND "+CarEntityFields.carCode + " not in (?) LIMIT 1";
            }
            else
            {
                sql += " WHERE "+CarEntityFields.carPlaque +" = ? LIMIT 1";
            }
      
        }
        
        try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            if(carcode != 0 && carChassi == null  && carPlaque == null && carPlaque == null)
            {
                stmt.setInt(1, carcode);
            }
            
            if(carChassi != null)
            {
                if(carcode != 0)
                {
                    stmt.setString(1, carChassi); 
                    stmt.setInt(2, carcode);
                }
                else
                {
                    stmt.setString(1, carChassi);
                }
                
            }
            
            if(carPlaque != null)
            {
               if(carcode != 0)
               {
                   stmt.setString(1, carPlaque);
                   stmt.setInt(2, carcode);
               }
               else
               {
                    stmt.setString(1, carPlaque);
               }
            }
                        
            try
            (
                ResultSet result = stmt.executeQuery();
            )
            {
                
                if(result.next())
                {
                    int carCode = result.getInt(CarEntityFields.carCode);
                    String carYear = result.getString(CarEntityFields.carYear);
                    carChassi = result.getString(CarEntityFields.carChassi);
                    carPlaque = result.getString(CarEntityFields.carPlaque);
                    int carManufacturerCode = result.getInt(CarEntityFields.carManufacturerCode); 
                    int carModelCode = result.getInt(CarEntityFields.carModelCode);
                    
                    CarEntity carEntity = new CarEntity(carCode,carYear,carChassi,carPlaque,carManufacturerCode,carModelCode);
                    
                    int manufactureCode = result.getInt(ManufacturerEntityFields.manufacturerCode);
                    String manufactureName = result.getString(ManufacturerEntityFields.manufacturerName);
                    ManufacturerEntity manufacturerEntity = new ManufacturerEntity(manufactureCode,manufactureName);
                    
                    carEntity.setManufacturerEntity(manufacturerEntity);
                    
                    int modelCode = result.getInt(ModelEntityFields.modelCode);
                    String modelName = result.getString(ModelEntityFields.modelName);
                    ModelEntity modelEntity = new ModelEntity(modelCode,modelName);
                    
                    carEntity.setModelEntity(modelEntity);
                    
                    return new ResponseStatus(true,true,"Veiculo encontrado !",carEntity);
                }
            
                return new ResponseStatus(true,false,"Veiculo não encontrado !");
            }
            catch(Exception e)
            {
                return new ResponseStatus(false,false,"Ocorreu um erro ao verificar veiculo !");
            }         
        }
        catch(SQLException e)
        {
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        }
    }

    @Override
    public ResponseStatus<CarEntity> create(CarEntity carEntity) {
        try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            PreparedStatement stmt = conn.prepareStatement(SQL_CREATE_CAR,Statement.RETURN_GENERATED_KEYS);
        )
        {
            ResponseStatus response = new ResponseStatus(true,true,"Veiculo cadastrado com sucesso !");
            stmt.setString(1, carEntity.carYear);
            stmt.setString(2, carEntity.carChassi);
            stmt.setString(3, carEntity.carPlaque);
            stmt.setInt(4, carEntity.carManufacturerCode);
            stmt.setInt(5, carEntity.carModelCode);
            
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
            {
                response.setActionStatus(false);
                response.setMessage("Ocorreu um erro ao cadastrar veiculo !");
                return response;
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            
            if (generatedKeys.next())
            {
                carEntity.setCarCode(generatedKeys.getInt(1));
            }
            else 
            {
                response.setActionStatus(false);
                response.setMessage("Ocorreu um erro ao cadastrar veiculo !");
                return response;
            }
            
            return new ResponseStatus(true,true,"Veiculo cadastrado com sucesso !",carEntity);
        }
        catch(Exception e)
        {
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        }
    }

    @Override
    public ResponseStatus<List<CarEntity>> list() {
       try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            Statement stmt = conn.createStatement(); 
        )
        {
            ArrayList<CarEntity> cars = new ArrayList<CarEntity>();
            ResultSet resultSet = stmt.executeQuery(SQL_LIST_CAR);
            
            while (resultSet.next())
            {
                int carCode = resultSet.getInt(CarEntityFields.carCode);
                String carYear = resultSet.getString(CarEntityFields.carYear);
                String carChassi = resultSet.getString(CarEntityFields.carChassi);
                String carPlaque = resultSet.getString(CarEntityFields.carPlaque);
                int carManufacturerCode = resultSet.getInt(CarEntityFields.carManufacturerCode); 
                int carModelCode = resultSet.getInt(CarEntityFields.carModelCode);
                    
                CarEntity carEntity = new CarEntity(carCode,carYear,carChassi,carPlaque,carManufacturerCode,carModelCode);
                    
                int manufactureCode = resultSet.getInt(ManufacturerEntityFields.manufacturerCode);
                String manufactureName = resultSet.getString(ManufacturerEntityFields.manufacturerName);
                ManufacturerEntity manufacturerEntity = new ManufacturerEntity(manufactureCode,manufactureName);
                    
                carEntity.setManufacturerEntity(manufacturerEntity);
                    
                int modelCode = resultSet.getInt(ModelEntityFields.modelCode);
                String modelName = resultSet.getString(ModelEntityFields.modelName);
                ModelEntity modelEntity = new ModelEntity(modelCode,modelName);
                    
                carEntity.setModelEntity(modelEntity);
                    
                cars.add(carEntity);    
            }
            
            return new ResponseStatus(true,true,"Veiculos listados com sucesso !",cars);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());  
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        }
    }

    @Override
    public ResponseStatus<CarEntity> update(CarEntity carEntity) {
        try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_CAR);
        )
        {
            stmt.setString(1,carEntity.carYear);        
            stmt.setString(2,carEntity.carChassi);  
            stmt.setString(3,carEntity.carPlaque); 
            stmt.setInt(4, carEntity.carManufacturerCode); 
            stmt.setInt(5,carEntity.carModelCode); 
            stmt.setInt(6, carEntity.carCode);

            int rows = stmt.executeUpdate();

            return new ResponseStatus(
                true,
                rows==1,
                rows == 1 ? "Dados atualizados com sucesso !" :"Ocorreu um erroao atulizar aos dados !"
            );
           
        }
         catch(Exception e)
        {
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        }
    }

    @Override
    public ResponseStatus<CarEntity> delete(CarEntity carEntity) {
       try
        (
            Connection conn = DataBaseConnection.getInstance().getConexaoVW();
            PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_CAR);      
        )
        {
            stmt.setInt(1, carEntity.carCode);
            
           
            int row = stmt.executeUpdate();
           
            if(row == 1)
            {
               return new ResponseStatus(true,true,"Veiculo excluido com sucesso !",carEntity);
            }
            
            return new ResponseStatus(true,false,"Não foi possível excluir o veiculo !");
        }
        catch(Exception e)
        {
            return new ResponseStatus(false,false,"Ocorreu um erro ao se conectar com o banco de dados !",e);
        }
    }
   
    
}
