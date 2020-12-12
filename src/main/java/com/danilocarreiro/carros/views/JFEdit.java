/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.views;

import com.danilocarreiro.carros.controllers.CarController;
import com.danilocarreiro.carros.controllers.ManufacturerController;
import com.danilocarreiro.carros.controllers.ModelController;
import com.danilocarreiro.carros.entities.CarEntity;
import com.danilocarreiro.carros.entities.ManufacturerEntity;
import com.danilocarreiro.carros.entities.ModelEntity;
import com.danilocarreiro.carros.helpers.ResponseStatus;
import com.danilocarreiro.carros.helpers.Validators;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class JFEdit extends javax.swing.JFrame {
    
    final CarController carController = new CarController();
    final ManufacturerController manufacturerController = new ManufacturerController();   
    final ModelController modelController = new ModelController();
    
    List<ManufacturerEntity> manufacturers;
    List<ModelEntity> models;
    CarEntity carEntity;
    
    boolean isValidYear = true;
    boolean isValidChassi = true;
    boolean isValidPlaque = true;
    boolean isValidManufacturer = true;
    boolean isValidModel = true;
    
    private void validFomSubmit(){
        this.jBSubmit.setEnabled(isValidYear && isValidChassi && isValidPlaque && isValidManufacturer && isValidModel);
    }
    
    /**
     * Creates new form JFCreate
     */
    public JFEdit(CarEntity carEntity) {
        initComponents();
        
        this.carEntity = carEntity;
        this.jTFYear.setText(carEntity.carYear);
        this.jTFChassi.setText(carEntity.carChassi);
        this.jTFPlaque.setText(carEntity.carPlaque);

        this.initListeners();
        this.initComboBoxManufacturer();
        this.initComboBoxModel();

        jBSubmit.setEnabled(false);
    }
    
    public void initComboBoxManufacturer(){
        
        jCBManufacturer.removeAllItems();

        ResponseStatus<List<ManufacturerEntity>> response = this.manufacturerController.list();
        
        jCBManufacturer.addItem("Selecione um fabricante");
       
        if(response.status())
        {
            this.manufacturers = response.data;
            int indexSelected = 0;
            int index = 0;
            
            for(ManufacturerEntity manufacturerEntity  : response.data)
            {
                jCBManufacturer.addItem(manufacturerEntity.manufactureName);
                
                if(this.carEntity.carManufacturerCode == manufacturerEntity.manufactureCode)
                {
                    indexSelected = index+1;
                }
                
                index++;
            }
           
            jCBManufacturer.setSelectedIndex(indexSelected);  
        }
        else
        {
            JOptionPane.showMessageDialog(this, response.message,"Ocorreu um erro !", JOptionPane.ERROR);
        }
    }
    
    public void initComboBoxModel(){
        
        jCBModel.removeAllItems();
        
        ResponseStatus<List<ModelEntity>> response = this.modelController.list();
        
        jCBModel.addItem("Selecione um modelo");
        
        if(response.status())
        {
            this.models = response.data;
            int indexSelected = 0;
            int index = 0;
            
            for(ModelEntity modelEntity : response.data)
            {
                jCBModel.addItem(modelEntity.modelName);
                
                if(this.carEntity.carModelCode == modelEntity.modelCode)
                {
                    indexSelected = index +1;
                }
                
                index++;
            }
            jCBModel.setSelectedIndex(indexSelected);
        }
        else
        {
            JOptionPane.showMessageDialog(this, response.message,"Ocorreu um erro !", JOptionPane.ERROR);
        }
    }
    
    public void initListeners(){
        
        jTFYear.getDocument().addDocumentListener(Validators.getDocumentListener(()->{
            
            jLYear.setText("");
            
            this.isValidYear = false;
            
            String msg = Validators.validateYear(jTFYear.getText());
            
            if(msg == null )
            {
                this.isValidYear = true;
            }
            else
            {
                jLYear.setText(msg);
            }
                       
            this.validFomSubmit();
        }));
        
        jTFChassi.getDocument().addDocumentListener(Validators.getDocumentListener(()->{
            
            jLChassi.setText("");
            this.isValidChassi = false;
            
            String msg = Validators.validateChassi(jTFChassi.getText());
            
            if(msg == null )
            {
                ResponseStatus<CarEntity> response = this.carController.select(this.carEntity.carCode,jTFChassi.getText(),null);
                if(response.requestStatus)
                {
                    if( ! response.actionStatus)
                    {
                        this.isValidChassi = true;
                    }
                    else
                    {
                        jLChassi.setText("Chassi já cadastrado !");
                    }
                }
                else
                {
                  JOptionPane.showMessageDialog(this,"Ocorreu um erro ao verificar chassi !");
                }
              
            }
            else
            {
                jLChassi.setText(msg);
            }
                       
            this.validFomSubmit();
        }));
        
        jTFPlaque.getDocument().addDocumentListener(Validators.getDocumentListener(()->{
            
            jLPlaque.setText("");
            this.isValidPlaque = false;
            
            String msg = Validators.validatePlaque(jTFPlaque.getText());
            
            if(msg == null )
            {
                ResponseStatus<CarEntity> response = this.carController.select(this.carEntity.carCode,null,jLPlaque.getText());
 
                if(response.requestStatus)
                {
                    if( ! response.actionStatus)
                    {
                        this.isValidPlaque = true;
                    }
                    else
                    {
                        jLPlaque.setText("Placa já cadastrada");
                    }
                }
            }
            else
            {
                jLPlaque.setText(msg);
            }
                       
            this.validFomSubmit();
        }));
        
        jCBManufacturer.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
               jLManufacturer.setText("");
               isValidManufacturer = false;
               
                if(jCBManufacturer.getSelectedIndex() > 0)
                {
                    isValidManufacturer = true;
                }
                else
                {
                    jLManufacturer.setText("Campo obrigatório !");
                }
               
                validFomSubmit();
            }
        });
        
        jCBModel.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
               jLModel.setText("");
               isValidModel= false;
               
                if(jCBModel.getSelectedIndex() > 0)
                {
                    isValidModel = true;
                }
                else
                {
                    jLModel.setText("Campo obrigatório !");
                }
               
                validFomSubmit();
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFYear = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFChassi = new javax.swing.JTextField();
        jBSubmit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFPlaque = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jCBManufacturer = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jCBModel = new javax.swing.JComboBox<>();
        jLYear = new javax.swing.JLabel();
        jLChassi = new javax.swing.JLabel();
        jLPlaque = new javax.swing.JLabel();
        jLManufacturer = new javax.swing.JLabel();
        jLModel = new javax.swing.JLabel();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Carro");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Cadastrar Carro");

        jLabel2.setText("Ano:");

        jLabel3.setText("Chassi:");

        jBSubmit.setText("Salvar");
        jBSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSubmitActionPerformed(evt);
            }
        });

        jLabel6.setText("Placa:");

        jLabel7.setText("Fabricante:");

        jLabel4.setText("Modelo:");

        jLYear.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLChassi.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLPlaque.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFYear, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLManufacturer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLPlaque))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLChassi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jTFChassi)
                            .addComponent(jBSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jTFPlaque))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jCBManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBModel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLChassi))
                .addGap(4, 4, 4)
                .addComponent(jTFChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLPlaque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFPlaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLManufacturer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLModel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCBModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBSubmit)
                .addGap(24, 24, 24))
        );

        setSize(new java.awt.Dimension(246, 374));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSubmitActionPerformed
        // TODO add your handling code here:
        
        String year = jTFYear.getText();
        String chassi = jTFChassi.getText();
        String plaque  = jTFPlaque.getText();
        int  manufacturerCode = this.manufacturers.get(jCBManufacturer.getSelectedIndex()-1).manufactureCode;
        int  modelCode = this.models.get(jCBModel.getSelectedIndex()-1).modelCode;
        
        CarEntity carEntity = new CarEntity(this.carEntity.carCode,year,chassi,plaque,manufacturerCode,modelCode);
        
        ResponseStatus<CarEntity> response = this.carController.update(carEntity);
        
        if(response.status())
        {
            JOptionPane.showMessageDialog(this, response.message);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, response.message);
        }
        
    }//GEN-LAST:event_jBSubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new JFEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSubmit;
    private javax.swing.JComboBox<String> jCBManufacturer;
    private javax.swing.JComboBox<String> jCBModel;
    private javax.swing.JLabel jLChassi;
    private javax.swing.JLabel jLManufacturer;
    private javax.swing.JLabel jLModel;
    private javax.swing.JLabel jLPlaque;
    private javax.swing.JLabel jLYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTFChassi;
    private javax.swing.JTextField jTFPlaque;
    private javax.swing.JTextField jTFYear;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
