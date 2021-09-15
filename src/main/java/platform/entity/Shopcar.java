package platform.entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shopcar")
public class Shopcar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private Integer orderId;
    /**
     * 商品Id
     */
    @Column
    private Integer productId;
    /**
     * 数量
     */
    @Column
    private Integer count;

    @Column
    private Integer userId;

    @Column
    private Double subTotal;

    @Transient
    private Product product;

    public Shopcar() {
    }

    public Shopcar(int id, Integer orderId, Integer productId, Integer count, Integer userId, Double subTotal, Product product) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.userId = userId;
        this.subTotal = subTotal;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Shopcar{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                ", userId=" + userId +
                ", subTotal=" + subTotal +
                '}';
    }
}
