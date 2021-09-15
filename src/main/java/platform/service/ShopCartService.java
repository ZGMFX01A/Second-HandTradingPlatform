package platform.service;

import platform.entity.OrderItem;
import platform.entity.Shopcar;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车
 */
public interface ShopCartService {

    String NAME_PREFIX = "shop_cart_";

    /**
     * 加购物车
     * @param
     */
    void addCart(int productId, HttpServletRequest request) throws Exception;

    /**
     * 移除
     * @param productId
     * @param request
     */
    void remove(int productId, HttpServletRequest request) throws Exception;

    /**
     * 查看购物车
     * @param request
     * @return
     */
    List<OrderItem> listCart(HttpServletRequest request) throws Exception;

    List<Shopcar> carList(HttpServletRequest request)throws Exception;
}
