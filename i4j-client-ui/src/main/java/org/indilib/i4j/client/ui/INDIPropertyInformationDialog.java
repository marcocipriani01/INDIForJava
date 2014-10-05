/*
 *  This file is part of INDI for Java Client UI.
 * 
 *  INDI for Java Client UI is free software: you can redistribute it
 *  and/or modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation, either version 3 of 
 *  the License, or (at your option) any later version.
 * 
 *  INDI for Java Client UI is distributed in the hope that it will be
 *  useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 *  of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with INDI for Java Client UI.  If not, see 
 *  <http://www.gnu.org/licenses/>.
 */
package org.indilib.i4j.client.ui;

/*
 * #%L
 * INDI for Java Client UI Library
 * %%
 * Copyright (C) 2013 - 2014 indiforjava
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.awt.Dimension;
import java.awt.Toolkit;

import org.indilib.i4j.Constants.PropertyPermissions;
import org.indilib.i4j.client.INDIProperty;

/**
 * A dialog to show information about a INDIProperty.
 *
 * @author S. Alonso (Zerjillo) [zerjioi at ugr.es]
 * @version 1.10, March 19, 2012
 * @see INDIProperty
 */
public class INDIPropertyInformationDialog extends javax.swing.JDialog {

  INDIProperty property;
  
  /**
   * Creates new form INDIPropertyInformationDialog
   */
  public INDIPropertyInformationDialog(java.awt.Frame parent, boolean modal, INDIProperty property) {
    super(parent, modal);
    initComponents();
    
    this.property = property;
    
    pack();
    
    int width = 400;
    int height = getHeight();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 
    setBounds((dim.width - width) / 2, (dim.height - height) / 2, width, height);
  }

  public void showDialog() {
    name.setText(property.getName());
    group.setText(property.getGroup());
    timeout.setText("" + property.getTimeout());

    setTitle("\"" + property.getLabel() + "\" property information");
    
    PropertyPermissions p = property.getPermission();
    
    if (p == PropertyPermissions.RO) {
      permission.setText("Read Only");
    } else if (p == PropertyPermissions.WO) {
      permission.setText("Write Only");
    } else if (p == PropertyPermissions.RW) {
      permission.setText("Read / Write");
    }

    setVisible(true);
  }
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        permission = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        group = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        timeout = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5));
        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

        jPanel1.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel1.setText("Name:");
        jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);

        name.setEditable(false);
        jPanel1.add(name, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel1);

        jPanel3.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel2.setText("Permission:");
        jPanel3.add(jLabel2, java.awt.BorderLayout.WEST);

        permission.setEditable(false);
        jPanel3.add(permission, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel3.setText("Group:");
        jPanel4.add(jLabel3, java.awt.BorderLayout.WEST);

        group.setEditable(false);
        jPanel4.add(group, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel4.setText("Timeout:");
        jPanel5.add(jLabel4, java.awt.BorderLayout.WEST);

        timeout.setEditable(false);
        jPanel5.add(timeout, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        close.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/cross.png"))); // NOI18N
        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        jPanel8.add(close);

        getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
    this.setVisible(false);
  }//GEN-LAST:event_closeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JTextField group;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField name;
    private javax.swing.JTextField permission;
    private javax.swing.JTextField timeout;
    // End of variables declaration//GEN-END:variables
}
