/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
//The exception is generated if Price has errors
public class PriceException extends Exception {
    public PriceException(String message) {
        super (message);
        System.out.println("Price Exception: " + message);
    }
}
