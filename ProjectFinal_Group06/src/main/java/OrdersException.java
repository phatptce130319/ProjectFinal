/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
public class OrdersException extends Exception {
    //throw exception
    public OrdersException(String message) {
        super (message);
        System.out.println("Orders Exception: " + message);
    }
}
