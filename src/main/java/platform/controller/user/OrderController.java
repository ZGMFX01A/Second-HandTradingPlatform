package platform.controller.user;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.config.AlipayConfig;
import platform.dao.OrderItemDao;
import platform.dao.ProductDao;
import platform.entity.Order;
import platform.entity.OrderItem;
import platform.entity.Product;
import platform.entity.pojo.ResultBean;
import platform.service.CommentService;
import platform.service.OrderService;
import platform.service.impl.ClassificationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CommentService commentService;
    @Autowired
    ProductDao productDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ClassificationServiceImpl classificationServiceImpl;


    final static Logger log = LoggerFactory.getLogger(OrderController.class);

    /**
     * 打开订单列表页面
     *
     * @return
     */
    @RequestMapping("/toList.html")
    public String toOrderList() {
        return "mall/order/list";
    }

    /**
     * 查询用户订单列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<Order>> listData(HttpServletRequest request) {
        List<Order> orders = orderService.findUserOrder(request);
        return new ResultBean<>(orders);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/getDetail.do")
    @ResponseBody
    public ResultBean<List<OrderItem>> getDetail(Integer orderId) {
        List<OrderItem> orderItems = orderService.findItems(orderId);
        return new ResultBean<>(orderItems);
    }


    @RequestMapping("/toComment.html")
    public String toComment(Integer productid,Integer uid, HttpServletRequest request) throws IOException {
            request.getSession().setAttribute("pid",productid);
            request.getSession().setAttribute("uid",uid);
            Product product= productDao.findOne(productid);
            request.getSession().setAttribute("product",product);
            return "mall/order/comment";
    }

    /**
     * 添加评论
     * @param comm
     * @param pid
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/write.do")
    public ResultBean<Boolean> writecomment(Integer pid,Integer uid,String comm) {
        commentService.write(pid,uid,comm);
        return new ResultBean<>(true);
    }


    /**
     * 提交订单
     *
     * @param name
     * @param phone
     * @param addr
     * @param request
     * @param response
     */
    @RequestMapping("/submit.do")
    public void submit(String name,
                       String phone,
                       String addr,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        orderService.submit(name, phone, addr, request, response);
    }

    /**
     * 支付方法
     *
     * @param orderId
     */
    @RequestMapping("pay.do")
    @ResponseBody
    public ResultBean<Boolean> pay(int orderId, HttpServletResponse response) throws IOException {
        orderService.pay(orderId);
        return new ResultBean<>(true);
    }
//    /**
//     *
//     * @Title: AlipayController.java
//     * @Package com.sihai.controller
//     * @Description: 前往支付宝第三方网关进行支付
//     * Copyright: Copyright (c) 2017
//     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
//     * @author xukai
//     * @date 2021年7月14日
//     * @version V1.0
//     */
//    @RequestMapping("toPay.html")
//    @ResponseBody
//    public String pay(Integer orderId , HttpServletRequest request, HttpServletRequest response) throws Exception {
//
//
//        Order order=orderService.findById(orderId);
//
//        List<OrderItem> list=orderItemDao.findByOrderId(orderId);
//        StringBuffer OrderItemsName = new StringBuffer();
//        StringBuffer OrderItemsDesc = new StringBuffer();
//        for (OrderItem orderItem:list)
//        {
//            Product product = productDao.findOne(orderItem.getProductId());
//            OrderItemsDesc.append( classificationServiceImpl.findById(product.getCsid()).getCname()+";");
//            orderItem.setProduct(product);
//            OrderItemsName.append(product.getTitle()+";");
//
//        }
//
//        //获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
//
//        //设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//
//        //商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = "11";
//        //付款金额，必填
//        String total_amount = String.valueOf(order.getTotal());
//        //订单名称，必填
//        String subject = String.valueOf(OrderItemsName);
//        //商品描述，可空
//        String body = String.valueOf(OrderItemsDesc);
//
//        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
//        String timeout_express = "1c";
//
//        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//                + "\"total_amount\":\""+ total_amount +"\","
//                + "\"subject\":\""+ subject +"\","
//                + "\"body\":\""+ body +"\","
//                + "\"timeout_express\":\""+ timeout_express +"\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//
//        //请求
//        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        //修改订单状态
////        orderService.pay(orderId);
//
//        //输出订单id
//        System.out.println(orderId);
//        System.out.println(out_trade_no);
//        //输出订单总价
//        System.out.println(order.getTotal());
//        //输出订单产品名称
//        System.out.println(OrderItemsName);
//        //输出订单产品类型
//        System.out.println(OrderItemsDesc);
//
//        return result;
//    }
//
//    /**
//     *
//     * @Title: AlipayController.java
//     * @Package com.sihai.controller
//     * @Description: 支付宝同步通知页面
//     * Copyright: Copyright (c) 2017
//     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
//     * @author xukai
//     * @date 2021年7月14日
//     * @version V1.0
//     * @return
//     */
//    @RequestMapping("/toalipayReturnNotice.html")
//    public String alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
//
//        log.info("支付成功, 进入同步通知接口...");
//
//        //获取支付宝GET过来反馈信息
//        Map<String,String> params = new HashMap<String,String>();
//        Map<String,String[]> requestParams = request.getParameterMap();
//        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            params.put(name, valueStr);
//        }
//
//        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
//
//        //——请在这里编写您的程序（以下代码仅作参考）——
//        if(signVerified) {
//            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//            //付款金额
//            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
//
//            // 修改订单状态，改为 支付成功，已付款;
//            orderService.pay(Integer.parseInt(out_trade_no));
//            Order order=orderService.findById(Integer.parseInt(out_trade_no));
//
//            List<OrderItem> list=orderItemDao.findByOrderId(Integer.parseInt(out_trade_no));
//            StringBuffer OrderItemsName = new StringBuffer();
//            StringBuffer OrderItemsDesc = new StringBuffer();
//            for (OrderItem orderItem:list)
//            {
//                Product product = productDao.findOne(orderItem.getProductId());
//                OrderItemsDesc.append( classificationServiceImpl.findById(product.getCsid()).getCname()+";");
//                orderItem.setProduct(product);
//                OrderItemsName.append(product.getTitle()+";");
//
//            }
//            log.info("********************** 支付成功(支付宝同步通知) **********************");
//            log.info("* 订单号: {}", out_trade_no);
//            log.info("* 支付宝交易号: {}", trade_no);
//            log.info("* 实付金额: {}", total_amount);
//            log.info("* 购买产品: {}", OrderItemsName);
//            log.info("***************************************************************");
//
//        }else {
//            log.info("支付, 验签失败...");
//        }
//
//        return "mall/order/list";
//    }
//
//    /**
//     *
//     * @Title: AlipayController.java
//     * @Package com.sihai.controller
//     * @Description: 支付宝异步 通知页面
//     * Copyright: Copyright (c) 2017
//     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
//     * @author xukai
//     * @date 2021年7月14日
//     * @version V1.0
//     */
//    @RequestMapping( "/toalipayNotifyNotice.html")
//    @ResponseBody
//    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
//
//        log.info("支付成功, 进入异步通知接口...");
//        //获取支付宝POST过来反馈信息
//        Map<String,String> params = new HashMap<String,String>();
//        Map<String,String[]> requestParams = request.getParameterMap();
//        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            //乱码解决，这段代码在出现乱码时使用
////			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            params.put(name, valueStr);
//        }
//
//        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
//
//        //——请在这里编写您的程序（以下代码仅作参考）——
//
//		/* 实际验证过程建议商户务必添加以下校验：
//		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
//		4、验证app_id是否为该商户本身。
//		*/
//        if(signVerified) {//验证成功
//            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//
//            //付款金额
//            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
//
//            if(trade_status.equals("TRADE_FINISHED")){
//                //判断该笔订单是否在商户网站中已经做过处理
//                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                //如果有做过处理，不执行商户的业务程序
//
//                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
//                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//            }else if (trade_status.equals("TRADE_SUCCESS")){
//                //判断该笔订单是否在商户网站中已经做过处理
//                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                //如果有做过处理，不执行商户的业务程序
//
//                //注意：
//                //付款完成后，支付宝系统发送该交易状态通知
//
//
//                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
//                orderService.pay(Integer.parseInt(out_trade_no));
//
//                Order order=orderService.findById(Integer.parseInt(out_trade_no));
//                List<OrderItem> list=orderItemDao.findByOrderId(Integer.parseInt(out_trade_no));
//                StringBuffer OrderItemsName = new StringBuffer();
//                StringBuffer OrderItemsDesc = new StringBuffer();
//                for (OrderItem orderItem:list)
//                {
//                    Product product = productDao.findOne(orderItem.getProductId());
//                    OrderItemsDesc.append( classificationServiceImpl.findById(product.getCsid()).getCname()+";");
//                    orderItem.setProduct(product);
//                    OrderItemsName.append(product.getTitle()+";");
//
//                }
//                log.info("********************** 支付成功(支付宝同步通知) **********************");
//                log.info("* 订单号: {}", out_trade_no);
//                log.info("* 支付宝交易号: {}", trade_no);
//                log.info("* 实付金额: {}", total_amount);
//                log.info("* 购买产品: {}", OrderItemsName);
//                log.info("***************************************************************");
//            }
//            log.info("支付成功...");
//
//        }else {//验证失败
//            log.info("支付, 验签失败...");
//        }
//        return "success";
//    }

    /**
     * 确认收货
     * @param orderId
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("receive.do")
    @ResponseBody
    public ResultBean<Boolean> receive(int orderId, HttpServletResponse response) throws IOException {
        orderService.receive(orderId);
        return new ResultBean<>(true);
    }

    /**
     * 确认收货
     * @param orderId
     * @param response
     * @return
     * @throws IOException
     */

    @RequestMapping("del.do")
    @ResponseBody
    public ResultBean<Boolean> del(int orderId, HttpServletResponse response) throws IOException {
        orderService.delOrder(orderId);
        return new ResultBean<>(true);
    }


}
