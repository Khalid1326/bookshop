package com.example.demo.exeptions;

/**
 * Created by asus-pc on 4/29/2021.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}