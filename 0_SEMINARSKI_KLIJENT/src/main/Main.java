/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author trajk
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // ostaje default L&F
        }
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if ("Server je ugasen".equals(throwable.getMessage())) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    javax.swing.JOptionPane.showMessageDialog(null,
                            "Veza sa serverom je prekinuta. Aplikacija ce se zatvoriti.",
                            "Server ugasen",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                });
            }
        });
        kordinator.Kordinator.getInstance().otvoriLoginFormu();
    }
    
}
