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

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.indilib.i4j.Constants.PropertyStates;
import org.indilib.i4j.Constants.SwitchStatus;
import org.indilib.i4j.client.INDIElement;
import org.indilib.i4j.client.INDIElementListener;
import org.indilib.i4j.client.INDIProperty;
import org.indilib.i4j.client.INDISwitchElement;
import org.indilib.i4j.client.INDISwitchProperty;
import org.indilib.i4j.client.INDIValueException;

/**
 * A panel to represent a
 * <code>INDISwitchProperty</code> with One of Many Rule.
 *
 * @author S. Alonso (Zerjillo) [zerjioi at ugr.es]
 * @version 1.36, November 18, 2013
 * @see INDISwitchProperty
 */
public class INDISwitchOneOfManyPropertyPanel extends INDIPropertyPanel implements INDIElementListener {

  private INDIPropertyInformationDialog infoDialog;
  /**
   * To avoid changes of the selected value if the combo box is not fully
   * populated.
   */
  private boolean init = false;

  /**
   * Creates new form INDISwitchOneOfManyPropertyPanel
   */
  public INDISwitchOneOfManyPropertyPanel(INDISwitchProperty property) {
    super(property);

    initComponents();

    updatePropertyData();

    List<INDIElement> elems = property.getElementsAsList();

    for (int i = 0 ; i < elems.size() ; i++) {
      INDISwitchElement se = (INDISwitchElement)elems.get(i);
      desiredValue.addItem(se.getLabel());

      if (se.getValue() == SwitchStatus.ON) {
        currentValue.setText(se.getLabel());
        currentValue.setToolTipText("Current Value: " + se.getName());
        desiredValue.setSelectedIndex(i);
      }

      se.addINDIElementListener(this);
    }

    init = true;
  }

  /**
   * Updates the data of the Property.
   */
  private void updatePropertyData() {
    name.setText(getProperty().getLabel());
    name.setToolTipText(getProperty().getName());

    PropertyStates st = getProperty().getState();

    if (st == PropertyStates.IDLE) {
      state.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/light_idle.png"))); // NOI18N
    } else if (st == PropertyStates.OK) {
      state.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/light_ok.png"))); // NOI18N
    } else if (st == PropertyStates.BUSY) {
      state.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/light_busy.png"))); // NOI18N
    } else if (st == PropertyStates.ALERT) {
      state.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/light_alert.png"))); // NOI18N
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    state = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    name = new javax.swing.JLabel();
    buttons = new javax.swing.JPanel();
    information = new javax.swing.JButton();
    central = new javax.swing.JPanel();
    currentValue = new javax.swing.JTextField();
    desiredValue = new javax.swing.JComboBox();

    setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    setLayout(new java.awt.BorderLayout(10, 0));

    jPanel1.setLayout(new java.awt.BorderLayout(5, 0));

    state.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/light_idle.png"))); // NOI18N
    jPanel1.add(state, java.awt.BorderLayout.WEST);

    jPanel5.setLayout(new java.awt.BorderLayout());
    jPanel5.add(name, java.awt.BorderLayout.CENTER);

    jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

    add(jPanel1, java.awt.BorderLayout.WEST);

    buttons.setPreferredSize(new java.awt.Dimension(55, 16));
    buttons.setLayout(new javax.swing.BoxLayout(buttons, javax.swing.BoxLayout.X_AXIS));

    information.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/information.png"))); // NOI18N
    information.setText("Info");
    information.setToolTipText("Information about the property");
    information.setMargin(new java.awt.Insets(0, 0, 0, 0));
    information.setMaximumSize(new java.awt.Dimension(55, 22));
    information.setMinimumSize(new java.awt.Dimension(55, 22));
    information.setPreferredSize(new java.awt.Dimension(78, 16));
    information.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        informationActionPerformed(evt);
      }
    });
    buttons.add(information);

    add(buttons, java.awt.BorderLayout.EAST);

    central.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

    currentValue.setEditable(false);
    currentValue.setToolTipText("Current Value");
    currentValue.setMinimumSize(new java.awt.Dimension(4, 16));
    central.add(currentValue);

    desiredValue.setToolTipText("Desired Value");
    desiredValue.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        desiredValueActionPerformed(evt);
      }
    });
    central.add(desiredValue);

    add(central, java.awt.BorderLayout.CENTER);
  }// </editor-fold>//GEN-END:initComponents

  private void informationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informationActionPerformed
    if (infoDialog == null) {
      infoDialog = new INDIPropertyInformationDialog((JFrame)SwingUtilities.getWindowAncestor(this), false, getProperty());
    }

    infoDialog.showDialog();
  }//GEN-LAST:event_informationActionPerformed

  private void desiredValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desiredValueActionPerformed
    if (init) {
      int selected = desiredValue.getSelectedIndex();
      List<INDIElement> elems = getProperty().getElementsAsList();

      for (int i = 0 ; i < elems.size() ; i++) {
        INDISwitchElement se = (INDISwitchElement)elems.get(i);

        if (i == selected) {
          try {
            se.setDesiredValue(SwitchStatus.ON);
          } catch (INDIValueException ex) {
          }
        } else {
          try {
            se.setDesiredValue(SwitchStatus.OFF);
          } catch (INDIValueException ex) {
          }
        }
      }

      try {
        getProperty().sendChangesToDriver();
      } catch (INDIValueException e) {
      } catch (IOException e) {
      }
    }
  }//GEN-LAST:event_desiredValueActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttons;
  private javax.swing.JPanel central;
  private javax.swing.JTextField currentValue;
  private javax.swing.JComboBox desiredValue;
  private javax.swing.JButton information;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JLabel name;
  private javax.swing.JLabel state;
  // End of variables declaration//GEN-END:variables

  @Override
  protected void checkSetButton() {
  }

  @Override
  public void propertyChanged(INDIProperty property) {
    if (property == getProperty()) {
      updatePropertyData();
    }
  }

  @Override
  public void elementChanged(INDIElement element) {
    INDISwitchElement se = (INDISwitchElement)element;

    if (se.getValue() == SwitchStatus.ON) {
      currentValue.setText(se.getLabel());
      currentValue.setToolTipText("Current Value: " + se.getName());
    }
  }
}
