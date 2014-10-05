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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.indilib.i4j.Constants.PropertyPermissions;
import org.indilib.i4j.INDIBLOBValue;
import org.indilib.i4j.client.INDIBLOBElement;
import org.indilib.i4j.client.INDIElement;

/**
 * A panel to represent a
 * <code>INDIBLOBElement</code>.
 *
 * @author S. Alonso (Zerjillo) [zerjioi at ugr.es]
 * @version 1.36, November 18, 2013
 * @see INDIBLOBElement
 */
public class INDIBLOBElementPanel extends INDIElementPanel {

  private INDIBLOBElement be;
  private INDIBLOBValue desiredValue;

  /**
   * Creates new form INDITextElementPanel
   */
  public INDIBLOBElementPanel(INDIBLOBElement be, PropertyPermissions perm) {
    super(perm);

    initComponents();

    if (!isWritable()) {
      sendPanel.setVisible(false);
      mainPanel.remove(sendPanel);
    }

    this.be = be;

    desiredValue = null;

    updateElementData();
  }

  private void updateElementData() {
    name.setText(be.getLabel());
    name.setToolTipText(be.getName());
  }

  private String getExtension(File f) {
    String ext = null;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if (i > 0 && i < s.length() - 1) {
      ext = s.substring(i + 1);
    }

    if (ext == null) {
      return "";
    }

    return ext;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    fileChooser = new javax.swing.JFileChooser();
    mainPanel = new javax.swing.JPanel();
    name = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    save = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    format = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    length = new javax.swing.JTextField();
    sendPanel = new javax.swing.JPanel();
    loadBLOB = new javax.swing.JButton();
    messagePanel = new javax.swing.JPanel();
    message = new javax.swing.JTextField();

    setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
    setLayout(new java.awt.BorderLayout());

    name.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    name.setMinimumSize(new java.awt.Dimension(100, 0));
    mainPanel.add(name);

    save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/disk.png"))); // NOI18N
    save.setText("Save");
    save.setEnabled(false);
    save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveActionPerformed(evt);
      }
    });
    jPanel1.add(save);

    jPanel2.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel1.setText("Format:");
    jPanel2.add(jLabel1);

    format.setEditable(false);
    jPanel2.add(format);

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText("Length:");
    jPanel2.add(jLabel2);

    length.setColumns(6);
    length.setEditable(false);
    length.setToolTipText("bytes");
    jPanel2.add(length);

    jPanel1.add(jPanel2);

    mainPanel.add(jPanel1);

    loadBLOB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/attach.png"))); // NOI18N
    loadBLOB.setText("Load...");
    loadBLOB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadBLOBActionPerformed(evt);
      }
    });
    sendPanel.add(loadBLOB);

    mainPanel.add(sendPanel);

    message.setColumns(6);
    message.setEditable(false);
    messagePanel.add(message);

    mainPanel.add(messagePanel);

    add(mainPanel, java.awt.BorderLayout.CENTER);
  }// </editor-fold>//GEN-END:initComponents

  private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    ExampleFileFilter filter = new ExampleFileFilter();

    String format = be.getValue().getFormat();

    if (format.startsWith(".")) {
      format = format.substring(1);
    }

    filter.addExtension(format);

    filter.setDescription(format + " format");

    fileChooser.setFileFilter(filter);

    int res = fileChooser.showSaveDialog(this);
    fileChooser.removeChoosableFileFilter(filter);

    if (res == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();

      try {
        be.getValue().saveBLOBData(f);
      } catch (IOException e) {
        JOptionPane.showMessageDialog((JFrame)SwingUtilities.getWindowAncestor(this), "Problem saving BLOB data in " + f.getAbsolutePath(), "Saving problem", JOptionPane.ERROR_MESSAGE);
      }
    }

    message.setText("Saved");
  }//GEN-LAST:event_saveActionPerformed

  private void loadBLOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBLOBActionPerformed
    byte[] bytes = null;

    int res = fileChooser.showSaveDialog(this);

    if (res == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();

      int size = (int)f.length();

      bytes = new byte[size];
      int pos = 0;

      try {
        FileInputStream fis = new FileInputStream(f);

        int breaded = fis.read(bytes);
        pos = breaded;

        while ((breaded != -1) && (size < pos)) {
          breaded = fis.read(bytes, pos, size - pos);

          if (breaded != -1) {
            pos += breaded;
          }
        }

        fis.close();

        desiredValue = new INDIBLOBValue(bytes, getExtension(f));

        setChanged(true);

        message.setText("Loaded");
      } catch (IOException e) {
        JOptionPane.showMessageDialog((JFrame)SwingUtilities.getWindowAncestor(this), "Problem loading BLOB data from " + f.getAbsolutePath(), "Loading problem", JOptionPane.ERROR_MESSAGE);
      }
    }

    checkSetButton();
  }//GEN-LAST:event_loadBLOBActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JFileChooser fileChooser;
  private javax.swing.JTextField format;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JTextField length;
  private javax.swing.JButton loadBLOB;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JTextField message;
  private javax.swing.JPanel messagePanel;
  private javax.swing.JLabel name;
  private javax.swing.JButton save;
  private javax.swing.JPanel sendPanel;
  // End of variables declaration//GEN-END:variables

  @Override
  protected Object getDesiredValue() {
    return desiredValue;
  }

  @Override
  protected INDIBLOBElement getElement() {
    return be;
  }

  @Override
  protected void setError(boolean erroneous, String errorMessage) {
  }

  @Override
  protected boolean isDesiredValueErroneous() {
    return false;
  }

  @Override
  protected void cleanDesiredValue() {
    desiredValue = null;
    message.setText("");
  }

  @Override
  public void elementChanged(INDIElement element) {
    if (element == be) {
      save.setEnabled(true);

      format.setText(be.getValue().getFormat());
      length.setText(be.getValue().getSize() + "");
    }
  }

  @Override
  protected int getNameSize() {
    return name.getWidth();
  }

  @Override
  protected void setNameSize(int size) {
    name.setPreferredSize(new Dimension(size, (int)(name.getPreferredSize().getHeight())));
  }
}
