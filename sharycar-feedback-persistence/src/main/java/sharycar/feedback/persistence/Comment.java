package sharycar.feedback.persistence;

import jdk.internal.jline.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comments")
@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String comment;

    @NotNull
    private Integer car_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarId(Integer id) {
        this.car_id = id;
    }

    public Integer getCarId() {
        return this.car_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
