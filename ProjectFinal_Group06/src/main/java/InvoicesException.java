/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class InvoicesException extends Exception {
    public InvoicesException(String message) {
        super (message);
        System.out.println("Invoices Exception: " + message);
    }
}
