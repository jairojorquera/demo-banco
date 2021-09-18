package jairojorquera.demo.banco.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jjorquerar
 */
public class Resultado<T> {

    Status status;
    T data;
    List<String> messages;

    public Resultado() {
        this.status = Status.SUCCESS;
        messages = new ArrayList<>();
    }

    public Resultado(T data) {
        this.status = Status.SUCCESS;
        this.data = data;
        messages = new ArrayList<>();
    }

    public Resultado(Status status, T data, List<String> messages) {
        this.status = status;
        this.data = data;
        this.messages = messages;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Resultado addMensaje(Status newStatus, String mensaje) {
        messages.add(mensaje);
        if (Status.ERROR.equals(newStatus)) {
            this.status = Status.ERROR;
            return this;
        }
        if (Status.FAIL.equals(newStatus)) {
            this.status = Status.FAIL;
        }
        return this;
    }

    public Resultado addResultado(Resultado newRtdo) {
        if (Status.ERROR.equals(newRtdo.getStatus())) {
            this.status = Status.ERROR;
        }
        if (Status.FAIL.equals(newRtdo.getStatus())) {
            this.status = Status.FAIL;
        }
        this.messages.addAll(newRtdo.getMessages());
        return this;
    }

    @JsonIgnore
    public boolean isOK() {
        return this.status.equals(Status.SUCCESS);
    }

    @Override
    public String toString() {
        return "Resultado{" + "status=" + status + ", data=" + data + ", message=" + messages + '}';
    }

}
