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
import java.util.Date;
import java.util.List;

import org.indilib.i4j.Constants.BLOBEnables;
import org.indilib.i4j.client.INDIDevice;
import org.indilib.i4j.client.INDIDeviceListener;
import org.indilib.i4j.client.INDIProperty;

/**
 * A panel to represent a
 * <code>INDIDevice</code>.
 *
 * @author S. Alonso (Zerjillo) [zerjioi at ugr.es]
 * @version 1.31, April 11, 2012
 * @see INDIDevice
 */
public class INDIDevicePanel extends javax.swing.JPanel implements INDIDeviceListener {

  private INDIDevice device;

  /**
   * Creates new form INDIDevicePanel
   */
  public INDIDevicePanel(INDIDevice device) {
    initComponents();
    blobsPanel.setVisible(false);

    this.device = device;

    List<INDIProperty> props = device.getPropertiesAsList();

    for (int i = 0 ; i < props.size() ; i++) {
      newProperty(device, props.get(i));
    }
  }

  @Override
  public final void newProperty(INDIDevice device, INDIProperty ip) {
    if (this.device == device) {
      String group = ip.getGroup();

      int pos = tabs.indexOfTab(group);

      INDIGroupPanel panel;

      if (pos == -1) { // Create new tab
        panel = new INDIGroupPanel();
        tabs.addTab(group, panel);
        tabs.revalidate();
      } else {
        panel = (INDIGroupPanel) tabs.getComponentAt(pos);
      }

      panel.addProperty(ip);

      if (device.getBLOBCount() > 0) {
        blobsPanel.setVisible(true);
      }
    }
  }

  @Override
  public void removeProperty(INDIDevice device, INDIProperty ip) {
    if (this.device == device) {

      String group = ip.getGroup();

      int pos = tabs.indexOfTab(group);

      if (pos != -1) {
        INDIGroupPanel panel = (INDIGroupPanel) tabs.getComponentAt(pos);

        panel.removeProperty(ip);

        if (panel.getPropertyCount() == 0) {
          tabs.remove(panel);
        }
      }

      if (device.getBLOBCount() > 0) {
        blobsPanel.setVisible(false);
      }
    }
  }

  @Override
  public void messageChanged(INDIDevice device) {
    if (this.device == device) {
      addMessage(device.getLastMessage());
    }
  }

  private void addMessage(String message) {
    message = message.trim();

    String m = messages.getText();
    int count = m.length() - m.replace("\n", "").length();

    count -= 100;  // Maximum number of lines to show
    int pos = 0;

    if (count > 0) {
      for (int i = 0 ; i < count ; i++) {
        pos = m.indexOf("\n", pos + 1);
      }

      m = m.substring(pos + 1);
    }

    if (!(m.length() == 0)) {
      m += "\n";
    }


    Date d = device.getTimestamp();

    messages.setText(m + d + ": " + message);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blobButtonGroup = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messages = new javax.swing.JTextArea();
        blobsPanel = new javax.swing.JPanel();
        blobsNever = new javax.swing.JRadioButton();
        blobsOnly = new javax.swing.JRadioButton();
        blobsAlso = new javax.swing.JRadioButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout(0, 5));
        add(tabs, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel1.setText("Messages:");
        jPanel2.add(jLabel1, java.awt.BorderLayout.WEST);

        messages.setColumns(20);
        messages.setEditable(false);
        messages.setRows(4);
        jScrollPane1.setViewportView(messages);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        blobsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(" BLOBs "));
        blobsPanel.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        blobButtonGroup.add(blobsNever);
        blobsNever.setSelected(true);
        blobsNever.setText("Never");
        blobsNever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blobsNeverActionPerformed(evt);
            }
        });
        blobsPanel.add(blobsNever);

        blobButtonGroup.add(blobsOnly);
        blobsOnly.setText("Only");
        blobsOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blobsOnlyActionPerformed(evt);
            }
        });
        blobsPanel.add(blobsOnly);

        blobButtonGroup.add(blobsAlso);
        blobsAlso.setText("Also");
        blobsAlso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blobsAlsoActionPerformed(evt);
            }
        });
        blobsPanel.add(blobsAlso);

        jPanel2.add(blobsPanel, java.awt.BorderLayout.EAST);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

  private void blobsNeverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blobsNeverActionPerformed
    try {
      addMessage("Setting BLOBs Never");
      device.BLOBsEnable(BLOBEnables.NEVER);
    } catch (IOException e) {
      addMessage("Problem setting BLOBs Never");
    }
  }//GEN-LAST:event_blobsNeverActionPerformed

  private void blobsOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blobsOnlyActionPerformed
    try {
      addMessage("Setting BLOBs Only");
      device.BLOBsEnable(BLOBEnables.ONLY);
    } catch (IOException e) {
      addMessage("Problem setting BLOBs Only");
    }
  }//GEN-LAST:event_blobsOnlyActionPerformed

  private void blobsAlsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blobsAlsoActionPerformed
    try {
      addMessage("Setting BLOBs Also");
      device.BLOBsEnable(BLOBEnables.ALSO);
    } catch (IOException e) {
      addMessage("Problem setting BLOBs Also");
    }
  }//GEN-LAST:event_blobsAlsoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup blobButtonGroup;
    private javax.swing.JRadioButton blobsAlso;
    private javax.swing.JRadioButton blobsNever;
    private javax.swing.JRadioButton blobsOnly;
    private javax.swing.JPanel blobsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messages;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
