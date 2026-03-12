/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author trajk
 */
public enum Operacija implements Serializable {
    LOGIN, UCITAJ_KLIJENTE, DODAJ_KLIJENTA, IZMENI_KLIJENTA,
    UCITAJ_PREGLED, OBRISI_PREGLED, UCITAJ_GRADOVE, UCITAJ_TRETMANE,
    DODAJ_TRETMAN, IZMENI_TRETMAN, KREIRAJ_PREGLED, IZMENI_PREGLED,
    LOGOUT
}
