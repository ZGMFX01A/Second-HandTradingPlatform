package platform.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.dao.ShopCarDao;
import platform.entity.*;
import platform.entity.pojo.ResultBean;
import platform.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    UserService userService;


    /**
     * 获取商品信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/get.do")
    public ResultBean<Product> getProduct(int id) {
        Product product = productService.findById(id);
        return new ResultBean<>(product);
    }

    /**
     * 打开商品详情页面
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/get.html")
    public String toProductPage(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        map.put("product", product);
        return "mall/product/info";
    }

    /**
     * 查找热门商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/hot.do")
    public ResultBean<List<Product>> getHotProduct() {
        List<Product> products = productService.findHotProduct();
        return new ResultBean<>(products);
    }

    /**
     * 查找最新商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/new.do")
    public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findNewProduct(pageable);
        return new ResultBean<>(products);
    }

    /**
     * 打开分类查看商品页面
     *
     * @return
     */
    @RequestMapping("/category.html")
    public String toCatePage(int cid, Map<String, Object> map) {
        Classification classification = classificationService.findById(cid);
        map.put("category", classification);
        return "mall/product/category";
    }

    @RequestMapping("/toCart.html")
    public String toCart(){
        return "mall/product/cart";
    }

    /**
     * 按一级分类查找商品
     *
     * @param cid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/category.do")
    public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCid(cid, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 按二级分类查找商品
     *
     * @param csId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/categorySec.do")
    public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCsid(csId, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 根据一级分类查询它所有的二级分类
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCategorySec.do")
    public ResultBean<List<Classification>> getCategorySec(int cid){
        List<Classification> list = classificationService.findByParentId(cid);
        return new ResultBean<>(list);
    }

    /**
     * 加购物车
     *
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCart.do")
    public ResultBean<Boolean> addToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.addCart(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * 移除购物车
     *
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCart.do")
    public ResultBean<Boolean> delToCart(int productId,HttpServletRequest request) throws Exception {
        shopCartService.remove(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * 评论列表
     * @param pid
     * @return
     */
    @RequestMapping("/getComments.do")
    @ResponseBody
    public ResultBean<List<Comment>> getComments(Integer pid) {
        System.out.println(pid);
        List<Comment> commentList= commentService.findComments(pid);
        return new ResultBean<>(commentList);
    }

    /**
     * 查看购物车商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listCart.do")
    public ResultBean<List<Shopcar>> listCart(HttpServletRequest request) throws Exception {
        List<Shopcar> shopcars = shopCartService.carList(request);
        return new ResultBean<>(shopcars);
    }

    /**
     * 修改购物车中商品数量
     */
    @ResponseBody
    @RequestMapping("/plusCart.do")
    public int updateCar(int productId){
        shopCarDao.plusCar(productId);
        int afCount=shopCarDao.findCount(productId);
        return afCount;
    }

    @ResponseBody
    @RequestMapping("/subCart.do")
    public int subCar(int productId){
        int bfCount=shopCarDao.findCount(productId);
        if(bfCount>=2){
            shopCarDao.subCar(productId);
        }
        int afCount=shopCarDao.findCount(productId);
        return afCount;
    }

    @ResponseBody
    @RequestMapping("/getAddress.do")
    public ResultBean<User> getAddress(int uid){
        System.out.println("用户id："+uid);
        User user= userService.findById(uid);
        User user1=new User();
        user1.setUsername(user.getUsername());
        user1.setAddr(user.getAddr());
        user1.setPhone(user.getPhone());
        return new  ResultBean<>(user1);
    }
}
