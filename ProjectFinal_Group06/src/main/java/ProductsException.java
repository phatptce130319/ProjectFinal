/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
//The exception is generated if Products has errors
 class ProductsException extends Exception {
     ProductsException(String message) {
        super (message);
        System.out.println("Products Exception: " + message);
    }
}
