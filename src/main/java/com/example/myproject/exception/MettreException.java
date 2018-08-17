package com.example.myproject.exception;

import lombok.Data;

/**
 * @author Exrickx
 */
@Data
public class MettreException extends RuntimeException {

    private String msg;

    public MettreException(String msg){
        super(msg);
        this.msg = msg;
    }
}
