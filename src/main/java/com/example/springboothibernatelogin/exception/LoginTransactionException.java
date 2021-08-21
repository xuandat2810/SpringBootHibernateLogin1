package com.example.springboothibernatelogin.exception;

public class LoginTransactionException extends Exception{
    private static final long serialVersionUID = -3128681006635769411L;

    public LoginTransactionException(String message){super(message);}
}
