package platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.dao.ShopCarDao;
import platform.entity.OrderItem;
import platform.entity.Product;
import platform.entity.Shopcar;
import platform.entity.User;
import platform.service.ProductService;
import platform.service.ShopCartService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hfb
 * @date 2017/11/21
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ProductService productService;
    @Autowired
    ShopCarDao shopCarDao;

    /**
     * 加购物车
     * 将商品id保存到Session中List<Integer>中
     *
     * @param productId
     * @param request
     */
    @Override
    public void addCart(int productId, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
        //List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        //List<Integer> productIds=new ArrayList<>();
//        if (productIds == null) {
//            productIds = new ArrayList<>();
//           request.getSession().setAttribute(NAME_PREFIX + loginUser.getId(), productIds);
//        }
//        productIds.add(productId);
//        for (Integer pid:productIds){
//
//        }
        List<Integer> productIdList=shopCarDao.findProductsByUserId(loginUser.getId());
        if (productIdList.size()==0)
        {
            shopCarDao.addShopcar(productId, loginUser.getId());
        }
        else {
            if(productIdList.contains(productId)==false)
            {
                shopCarDao.addShopcar(productId, loginUser.getId());
            }
        }

        //shopCarDao.addShopcar(productId,loginUser.getId());
    }
    /**
     * 移除
     *
     * 移除session List中对应的商品Id
     *
     * @param productId
     * @param request
     */
    @Override
    public void remove(int productId, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
//       // List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
//        List<Shopcar> shopcarList=shopCarDao.findAllByUserId(loginUser.getId());
//        List<Integer> productIds = new ArrayList<>();
//        for (Shopcar shopcar : shopcarList){
//            productIds.add(shopcar.getProductId());
//        }
//        Iterator<Integer> iterator = productIds.iterator();
//        while (iterator.hasNext()) {
//            if (productId == iterator.next()) {
//                iterator.remove();
//            }
//        }
        shopCarDao.deleteByProductId(productId);
    }

    /**
     * 查看购物车
     *
     * 查询出session的List中所有的商品Id,并封装成List<OrderItem>返回
     *
     * @param request
     * @return
     */
    @Override
    public List<OrderItem> listCart(HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");

        List<Shopcar> shopcarList=shopCarDao.findAllByUserId(loginUser.getId());
        System.out.println(shopcarList);
        List<Integer> productIds = new ArrayList<>();
        for (Shopcar shopcar : shopcarList){
            productIds.add(shopcar.getProductId());
        }
        //List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        // key: productId value:OrderItem

        Map<Integer, OrderItem> productMap = new HashMap<>();
        if (productIds == null){
            return new ArrayList<>();
        }
        // 遍历List中的商品id，每个商品Id对应一个OrderItem
        for (Integer productId : productIds) {
            if (productMap.get(productId) == null) {
                Product product = productService.findById(productId);
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setProductId(productId);
               // int count = shopCarDao.findByProductId(productId).getCount();
                int count=  shopCarDao.findByProductIdAndAndUserId(productId,loginUser.getId()).getCount();
                orderItem.setCount(count);
                orderItem.setSubTotal(product.getShopPrice()*count);
                productMap.put(productId, orderItem);
            } else {
                OrderItem orderItem = productMap.get(productId);
                //int count = shopCarDao.findByProductId(productId).getCount();
                int count=  shopCarDao.findByProductIdAndAndUserId(productId,loginUser.getId()).getCount();
                orderItem.setCount(count);
                Double subTotal = orderItem.getSubTotal();
                orderItem.setSubTotal(orderItem.getSubTotal()+subTotal);
                productMap.put(productId, orderItem);
            }
        }
        List<OrderItem> orderItems = new ArrayList<>(productMap.values());
        return orderItems;
    }

    @Override
    public List<Shopcar> carList(HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");

        List<Shopcar> shopcarList=shopCarDao.findAllByUserId(loginUser.getId());
        System.out.println(shopcarList);
        List<Integer> productIds = new ArrayList<>();
        for (Shopcar shopcar : shopcarList){
            productIds.add(shopcar.getProductId());
        }
        //List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        // key: productId value:OrderItem

        Map<Integer, Shopcar> productMap = new HashMap<>();
//
        // 遍历List中的商品id，每个商品Id对应一个OrderItem
        for (Integer productId : productIds) {
            if (productMap.get(productId) == null) {
                Product product = productService.findById(productId);
                Shopcar shopcar=new Shopcar();
                shopcar.setProduct(product);
                shopcar.setProductId(productId);
                //int count = shopCarDao.findByProductId(productId).getCount();
                int count=  shopCarDao.findByProductIdAndAndUserId(productId,loginUser.getId()).getCount();
                shopcar.setCount(count);
                //shopcar.setCount(1);
                shopcar.setSubTotal(product.getShopPrice()*count);
                productMap.put(productId, shopcar);
            }
            else {
                Shopcar shopcar = productMap.get(productId);
                //int count = shopcar.getCount();
                //int count = shopCarDao.findByProductId(productId).getCount();
                int count=  shopCarDao.findByProductIdAndAndUserId(productId,loginUser.getId()).getCount();
                shopcar.setCount(count);
                Double subTotal = shopcar.getSubTotal();
                shopcar.setSubTotal(shopcar.getSubTotal()+subTotal);
                productMap.put(productId, shopcar);
           }
        }
        List<Shopcar> shopcars = new ArrayList<>(productMap.values());
        return shopcars;
       // return shopcarList;
    }
}
