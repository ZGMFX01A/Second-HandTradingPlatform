package platform.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.dao.OrderItemDao;
import platform.entity.OrderItem;
import platform.entity.Product;
import platform.service.ProductService;

import java.util.*;

@Controller
@RequestMapping("/admin/chart")
public class AdminChartController {
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    ProductService productService;

    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/chart/list";
    }

    @RequestMapping("/getChart.do")
    @ResponseBody
    public Object eChart(String type) {

        System.out.println("type:"+type);
        List<String> legend = Arrays.asList("销量");
//      List<String> xAxis = Arrays.asList("衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子");
//      List<Integer> series = Arrays.asList(5, 20, 36, 10, 10, 20);

        List<String> xAxis = new ArrayList<>();
        List<Integer> series = new ArrayList<>();

        List<Integer> pidLists=orderItemDao.findAllPid();
        for (Integer pid:pidLists) {
            Product product=new Product();
            product.setTitle(productService.findById(pid).getTitle());
            String title=product.getTitle();
           // System.out.println(title);
            xAxis.add(title);
            int sumCount=0;
            List<OrderItem> productList=orderItemDao.findByProductId(pid);
            for (OrderItem o:productList
                 ) {
               sumCount+=o.getCount();
            }
            series.add(sumCount);
           // System.out.println(sumCount);
        }
//        System.out.println(xAxis);
//        System.out.println(series);

        //真实情况下Controller调用后台service dao进行数据查询
        Map<String, Object> map = new HashMap<>();
        map.put("legend", legend);
        map.put("xAxis", xAxis);
        map.put("series", series);
        return map;
    }
}
