package dev.codejar.payload.request;


import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
public class PostRequest {

    private String content;

}
