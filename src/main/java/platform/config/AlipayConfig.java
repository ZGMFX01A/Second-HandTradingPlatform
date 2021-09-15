package platform.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
/*商家账号egcbvs1706@sandbox.com
买家账号buoomi0279@sandbox.com
swhwyr4131@sandbox.com
登录密码111111
支付密码111111

 */
@Configuration
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000117688741";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCzjJUat+uB9xs4133B20eIPLbgjX105JFUlB2ECLne1hqG9jFAIVgQGQqPM0VzPRYoP1vC+I0scAwhCPo24QnwsxRji72dS1kxpfNiZDp6HsgTeE1N/LNlDb4Pje4AyEKFTSltdR2QBx6m4+VSnTGh5dsPI4cwWUuhjnYtAOL4RboElwTZYEwLoIokp+ekq+PBzrrZsBaHrOC9AjllsNL6TPE1qg6/kEpGW1W59sQKQ0/i/tT0jqrsG0KFLUYrldWwcSs4Ga0d21ZKiJUXmIy5Q27JKRR4GiTwdRyo93GsmvfPU7qFAzKWqIKn/SFDAE+9BQOCJti+h34Y8LtISdFrAgMBAAECggEAWfQ615IBmbF1n/rGdJMpuWXBsarxidjhvuAeEYJVrQDTdX5umlSaTCZflfL3/o5CRZPIo1vnTrYsPXjD8y63zjHKKz1dmCqh2y/rSgYxDfHSB38kK8hJEH3cFOrkrNuoCCTqdn5Qe1I11yMiR0ueDCCeqW0BVAXI0H4RUua4z+03/L4mlv9aITHPLyxlMtSS5G8PMyTm5CkSZRmfMherKIVqsWD2kPPkg7exlnXq3e36HKQlRAMZqILC+oOaIsEINX+k04ddHGKjoT4IXqq6U4MEJ4d3C7W8awnuQjSHUaqnOR3d4MjZUh6y/3eW+8I/VbOBjkD3GgEwKj54Hokc4QKBgQDg54O/WkXL3nVyml3XnDTOTUZPfCzKPshJ1JVYhV6GlpV9UjT1EYgfOzJIdzekhJBLizXCSY4STepXuhAs6e6xF6DzR+ot3qyY/31OzfxnufYX6rVjWEqlVrMU4bAmBMH3Uk3YHjxfNZsAwiU1l3Faxv42GjYsid6o0dvOpy0hwwKBgQDMX7ihkVU3pXkuhHnmn127FR23HgvR0eoDAFiCyWhif+/AbmYlgBrubzEOhcmLflUgq/EqLpAHUeNrxg6Ldg3JvpkHvrCjYQ3GQqhGHE1PqNM+fe44y4FnnJPg+wMLzGI39OHm9x7xu3bZWGuqlmtlHE1if4t1rtVCCCQcEqOvOQKBgA3KxM6QR6V37/XmN4kIYEiifoRTVh/U981nRv1VuHVCeMplpy+8Mn/PzoaZIQVqx7qKP/K5NggzVQKqO2G1utiLXWht6ZOEi0sap+GUSj1GLyxKTXNZL8Kx7WuDZ5z9P7PeDT4KpKMmE2pZfZSqw93QRDinJNlYXxo1PGiQ4finAoGALukt1RAp8lruJOpNnM0p0552MqkX53J6d3Pl3Sdj6XAaVR5CHgt4oxAsv3ugFn/KBHTTJ26Gqtd2Dy/n+MUBRzSTpb50hxvo8IAuZeFrMYS78FUxkCHOMz0yiGq1eUjRpqx0CfLhTHVOQ3dTR1W9kvjbHmfOWBXoZ3684yC4J6kCgYAxy2mp8RMWfoHFe5yFyBDsFZYeVo7m/hbjoog5O6E6XWSgsUY7hpAblbYxQ5VTMg5+UztChIF3Iyv1wMhpTMOvHcVTPV4iQJUMCNeb60bZ5OFldnBWd63Sn37wG0cevW8WwT4x+aSm0tBBZsHeNGHebPcZSOgH9gXqEVdbvNSREA==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiMbAul5FP2diBB+lC34H9BQikuQPQ/7HKkSdSc+1fZfs4riZgPNxEqyamLRGSVeaQLhAESHZoIkbwQLDMMp48Ixrnbx0WkAfA89s2kopkDnwQh9o9264I/bPtQEy32HlvAl6eJhekw6N31gGACmdMi7MJFnMLpHEmacmqjWsaURXOyykN9EVEoFDNBlYO9L4RNJWdrfnAuPL1kyC4e1Tr2onuA/wx7ry3EIV6ZCZZCi+rLXqeduzTM+I9uR5zPsuAzS1GWBd55pKoDJ+uUEX0xpoWba0zTMdnBSju9M+Mt0GGO+cmstjWYLvKJpCdzXFRzphWkVdxCM2+jX3u5p9wQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8081/mall/order/toalipayNotifyNotice.html";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8081/mall/order/toalipayReturnNotice.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

