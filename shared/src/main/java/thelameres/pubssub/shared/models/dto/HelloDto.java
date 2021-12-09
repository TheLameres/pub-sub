package thelameres.pubssub.shared.models.dto;

import java.io.Serializable;
import java.time.Instant;


public class HelloDto implements Serializable {

    static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Instant timestamp;
    private Status status;


    public HelloDto(Long id, String name, Instant timestamp, Status status) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HelloDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
