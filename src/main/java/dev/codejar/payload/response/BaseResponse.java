package dev.codejar.payload.response;

import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
public class BaseResponse <T>{

    private boolean status;

    private String message;

    private T data;

}
