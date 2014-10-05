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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.indilib.i4j.Constants.PropertyPermissions;
import org.indilib.i4j.client.INDIElement;
import org.indilib.i4j.client.INDINumberElement;
import org.indilib.i4j.client.INDIValueException;

/**
 * A panel to represent a
 * <code>INDINumberElement</code>.
 *
 * @author S. Alonso (Zerjillo) [zerjioi at ugr.es]
 * @version 1.36, November 18, 2013
 * @see INDINumberElement
 */
public class INDINumberElementPanel extends INDIElementPanel {

  private INDINumberElement ne;
  private INDINumberElementInformationDialog infoDialog;
  private boolean desiredValueErroneous;

  /**
   * Creates new form INDINumberElementPanel
   */
  public INDINumberElementPanel(INDINumberElement ne, PropertyPermissions perm) {
    super(perm);

    initComponents();

    if (!isWritable()) {
      desiredValue.setVisible(false);
      ((GridLayout)mainPanel.getLayout()).setColumns(2);
      mainPanel.remove(desiredValue);
    }

    this.ne = ne;

    desiredValue.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        desiredValueChanged();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        desiredValueChanged();
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        desiredValueChanged();
      }
    });


    updateElementData();

    desiredValueErroneous = false;
  }

  private void desiredValueChanged() {
    if (desiredValue.getText().trim().length() > 0) {
      setChanged(true);

      try {
        boolean correct = ne.checkCorrectValue(desiredValue.getText());

        if (!correct) {
          setError(true, "Unknown error parsing value");
        } else {
          setError(false, "");
        }
      } catch (INDIValueException e) {
        setError(true, e.getMessage());
      }
    } else {
      setChanged(false);
      setError(false, "");
    }

    checkSetButton();
  }

  private void updateElementData() {
    name.setText(ne.getLabel());
    name.setToolTipText(ne.getName());

    currentValue.setText(ne.getValueAsString());
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    name = new javax.swing.JLabel();
    mainPanel = new javax.swing.JPanel();
    currentValue = new javax.swing.JTextField();
    desiredValue = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    info = new javax.swing.JButton();

    setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
    setLayout(new java.awt.BorderLayout(5, 0));

    name.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    name.setMinimumSize(new java.awt.Dimension(100, 0));
    add(name, java.awt.BorderLayout.WEST);

    mainPanel.setLayout(new java.awt.GridLayout(1, 3, 5, 0));

    currentValue.setEditable(false);
    currentValue.setToolTipText("Current Value");
    currentValue.setMinimumSize(new java.awt.Dimension(4, 16));
    mainPanel.add(currentValue);

    desiredValue.setMinimumSize(new java.awt.Dimension(4, 16));
    mainPanel.add(desiredValue);

    add(mainPanel, java.awt.BorderLayout.CENTER);

    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

    info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/indilib/i4j/client/ui/images/information.png"))); // NOI18N
    info.setToolTipText("Info about the numeric element");
    info.setMargin(new java.awt.Insets(0, 0, 0, 0));
    info.setMinimumSize(new java.awt.Dimension(16, 16));
    info.setPreferredSize(new java.awt.Dimension(16, 16));
    info.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        infoActionPerformed(evt);
      }
    });
    jPanel2.add(info);

    add(jPanel2, java.awt.BorderLayout.EAST);
  }// </editor-fold>//GEN-END:initComponents

  private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
    if (infoDialog == null) {
      infoDialog = new INDINumberElementInformationDialog((JFrame)SwingUtilities.getWindowAncestor(this), false, ne);
    }

    infoDialog.showDialog();
  }//GEN-LAST:event_infoActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField currentValue;
  private javax.swing.JTextField desiredValue;
  private javax.swing.JButton info;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JLabel name;
  // End of variables declaration//GEN-END:variables

  @Override
  public void elementChanged(INDIElement element) {
    if (element == ne) {
      updateElementData();
    }
  }

  @Override
  protected Object getDesiredValue() {
    return desiredValue.getText();
  }

  @Override
  protected INDINumberElement getElement() {
    return ne;
  }

  @Override
  protected void setError(boolean erroneous, String errorMessage) {
    this.desiredValueErroneous = erroneous;

    if (erroneous) {
      desiredValue.setBackground(Color.PINK);
      desiredValue.setToolTipText(errorMessage);
      desiredValue.requestFocus();
    } else {
      desiredValue.setBackground(UIManager.getColor("TextField.background"));
      desiredValue.setToolTipText(null);
    }
  }

  @Override
  protected boolean isDesiredValueErroneous() {
    return desiredValueErroneous;
  }

  @Override
  protected void cleanDesiredValue() {
    desiredValue.setText("");
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