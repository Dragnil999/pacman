package dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
    private String message;
    private Object value;
    public MessageDTO(String message) {
        this.message = message;
    }
    public MessageDTO(String message, Object value) {
        this.message = message;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
