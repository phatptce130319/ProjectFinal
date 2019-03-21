package entity;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
//The exception is generated if entity.Orders has errors
class OrdersException extends Exception {
     OrdersException(String message) {
        super (message);
         System.out.println("Orders Exception: " + message);
    }
}
