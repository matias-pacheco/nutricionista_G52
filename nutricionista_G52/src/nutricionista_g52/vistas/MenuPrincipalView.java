/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import nutricionista_g52.accesoADatos.Conexion;

/**
 *
 * @author Matías Pacheco
 */
public class MenuPrincipalView extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipalView
     */
    public MenuPrincipalView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesPaEscritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenItPacientes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenItComidas = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenItDietasNueva = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenItConsultasPacientes = new javax.swing.JMenuItem();
        jMenItConsultasComidas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenItSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nutricional G52");

        javax.swing.GroupLayout jDesPaEscritorioLayout = new javax.swing.GroupLayout(jDesPaEscritorio);
        jDesPaEscritorio.setLayout(jDesPaEscritorioLayout);
        jDesPaEscritorioLayout.setHorizontalGroup(
            jDesPaEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        jDesPaEscritorioLayout.setVerticalGroup(
            jDesPaEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );

        jMenuBar1.setToolTipText("");

        jMenu1.setText("Pacientes");

        jMenItPacientes.setText("Pacientes");
        jMenItPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenItPacientesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenItPacientes);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Comidas");

        jMenItComidas.setText("Comidas");
        jMenItComidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenItComidasActionPerformed(evt);
            }
        });
        jMenu2.add(jMenItComidas);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Dietas");

        jMenItDietasNueva.setText("Nueva");
        jMenItDietasNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenItDietasNuevaActionPerformed(evt);
            }
        });
        jMenu6.add(jMenItDietasNueva);

        jMenuBar1.add(jMenu6);

        jMenu5.setText("Consultas");

        jMenItConsultasPacientes.setText("Pacientes");
        jMenItConsultasPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenItConsultasPacientesActionPerformed(evt);
            }
        });
        jMenu5.add(jMenItConsultasPacientes);

        jMenItConsultasComidas.setText("Comidas");
        jMenItConsultasComidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenItConsultasComidasActionPerformed(evt);
            }
        });
        jMenu5.add(jMenItConsultasComidas);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Salir");

        jMenItSalir.setText("Salir");
        jMenItSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenItSalirActionPerformed(evt);
            }
        });
        jMenu4.add(jMenItSalir);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesPaEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesPaEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenItPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenItPacientesActionPerformed
        // TODO add your handling code here:

        jDesPaEscritorio.removeAll();
        jDesPaEscritorio.repaint();

        PacientesView pacV = new PacientesView();
        pacV.setVisible(true);

        int x = (jDesPaEscritorio.getWidth() - pacV.getWidth()) / 2;
        int y = (jDesPaEscritorio.getHeight() - pacV.getHeight()) / 2;

        pacV.setLocation(x, y);

        jDesPaEscritorio.add(pacV);
        jDesPaEscritorio.moveToFront(pacV);
        
//        System.out.println(jDesPaEscritorio.getComponentCount());
    }//GEN-LAST:event_jMenItPacientesActionPerformed

    private void jMenItConsultasPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenItConsultasPacientesActionPerformed
        // TODO add your handling code here:
        
        jDesPaEscritorio.removeAll();
        jDesPaEscritorio.repaint();
        
        ConsultasPacientesView conPacV = new ConsultasPacientesView();
        conPacV.setVisible(true);
        
        int x = (jDesPaEscritorio.getWidth() - conPacV.getWidth()) / 2;
        int y = (jDesPaEscritorio.getHeight() - conPacV.getHeight()) / 2;
        
        conPacV.setLocation(x, y);
        
        jDesPaEscritorio.add(conPacV);
        jDesPaEscritorio.moveToFront(conPacV);
    }//GEN-LAST:event_jMenItConsultasPacientesActionPerformed

    private void jMenItComidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenItComidasActionPerformed
        // TODO add your handling code here:
        
        jDesPaEscritorio.removeAll();
        jDesPaEscritorio.repaint();
        
        ComidasView comiV = new ComidasView();
        comiV.setVisible(true);
        
        int x = (jDesPaEscritorio.getWidth() - comiV.getWidth()) / 2;
        int y = (jDesPaEscritorio.getHeight() - comiV.getHeight()) / 2;
        
        comiV.setLocation(x, y);
        
        jDesPaEscritorio.add(comiV);
        jDesPaEscritorio.moveToBack(comiV);
    }//GEN-LAST:event_jMenItComidasActionPerformed

    private void jMenItConsultasComidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenItConsultasComidasActionPerformed
        // TODO add your handling code here:
        
        jDesPaEscritorio.removeAll();
        jDesPaEscritorio.repaint();
        
        ConsultasComidasView conComiV = new ConsultasComidasView();
        conComiV.setVisible(true);
        
        int x = (jDesPaEscritorio.getWidth() - conComiV.getWidth()) / 2;
        int y = (jDesPaEscritorio.getHeight() - conComiV.getHeight()) / 2;
        
        conComiV.setLocation(x, y);
        
        jDesPaEscritorio.add(conComiV);
        jDesPaEscritorio.moveToFront(conComiV);
    }//GEN-LAST:event_jMenItConsultasComidasActionPerformed

    private void jMenItDietasNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenItDietasNuevaActionPerformed
        // TODO add your handling code here:
        
        jDesPaEscritorio.removeAll();
        jDesPaEscritorio.repaint();
        
        NuevaDietaView newDieV = new NuevaDietaView();
        newDieV.setVisible(true);
        
        int x = (jDesPaEscritorio.getWidth() - newDieV.getWidth()) / 2;
        int y = (jDesPaEscritorio.getHeight() - newDieV.getHeight()) / 2;
        
        newDieV.setLocation(x, y);
        
        jDesPaEscritorio.add(newDieV);
        jDesPaEscritorio.moveToFront(newDieV);
    }//GEN-LAST:event_jMenItDietasNuevaActionPerformed

    private void jMenItSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenItSalirActionPerformed
        // TODO add your handling code here:
        Conexion.desconectar();
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenItSalirActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesPaEscritorio;
    private javax.swing.JMenuItem jMenItComidas;
    private javax.swing.JMenuItem jMenItConsultasComidas;
    private javax.swing.JMenuItem jMenItConsultasPacientes;
    private javax.swing.JMenuItem jMenItDietasNueva;
    private javax.swing.JMenuItem jMenItPacientes;
    private javax.swing.JMenuItem jMenItSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
