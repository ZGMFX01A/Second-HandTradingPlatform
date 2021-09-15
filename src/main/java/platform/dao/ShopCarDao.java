package platform.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import platform.entity.Shopcar;

import javax.transaction.Transactional;
import java.util.List;

public interface ShopCarDao extends JpaRepository<Shopcar, Integer> {
    //根据用户id加载购物车里所有商品
    List<Shopcar> findAllByUserId(Integer userId);

    Shopcar findByProductIdAndAndUserId(Integer pid,Integer uid);
    //修改商品数量
    @Modifying
    @Transactional
    @Query(value = "update  shopcar set count =count+1 where product_id=?",nativeQuery = true)
    void plusCar(Integer productId);
    //修改商品数量
    @Modifying
    @Transactional
    @Query(value = "update  shopcar set count =count-1 where product_id=?",nativeQuery = true)
    void subCar(Integer productId);

    //查询用户购物车中所有的产品ID
    @Modifying
    @Transactional
    @Query(value = "select product_id from shopcar where user_id=?",nativeQuery = true)
    List<Integer> findProductsByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "delete from shopcar where product_id=?",nativeQuery = true)
    void deleteByProductId(Integer productId);

    @Modifying
    @Transactional
    @Query(value = "insert into `shopcar` (product_id,user_id) values (?1,?2)",nativeQuery = true)
    void addShopcar(Integer productId,Integer userId);

    Shopcar findByProductId(Integer productId);

    @Query(value = "select `count` from `shopcar` where product_id=?",nativeQuery = true)
    int findCount(Integer productId);

    void deleteByUserId(int uid);
}
