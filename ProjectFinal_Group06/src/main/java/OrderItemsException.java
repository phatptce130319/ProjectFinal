/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ha Van Ngoan
 */
//The exception is generated if Order Items has errors
public class OrderItemsException extends Exception {
    public OrderItemsException(String message) {
        super (message);
        System.out.println("Order Items Exception: " + message);
    }
}
