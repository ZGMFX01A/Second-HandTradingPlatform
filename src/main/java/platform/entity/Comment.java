package platform.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int pc_Id;

    @Column
    private int productId;

    @Column
    private int userId;

    @Column
    private String comment;

    @Transient
    private User user;
    @Transient
    private String username;


    public Comment() {
    }

    public Comment(int pc_Id, int productId, int userId, String comment, User user, String username) {
        this.pc_Id = pc_Id;
        this.productId = productId;
        this.userId = userId;
        this.comment = comment;
        this.user = user;
        this.username = username;
    }

    public int getPc_Id() {
        return pc_Id;
    }

    public void setPc_Id(int pc_Id) {
        this.pc_Id = pc_Id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
