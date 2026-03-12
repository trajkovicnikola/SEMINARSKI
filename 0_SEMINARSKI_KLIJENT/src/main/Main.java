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
            com.formdev.flatlaf.FlatDarculaLaf.setup();
        } catch (Exception e) { }
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
