/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ha Van Ngoan
 */
//The exception is generated if Invoidces has errors
class InvoicesException extends Exception {
    InvoicesException(String message) {
        super (message);
        System.out.println("Invoices Exception: " + message);
    }
}
