package crawlerTest;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpConfigTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//设置连接的最长时间，单位为毫秒
                .setConnectionRequestTimeout(500) //设置获取连接的最长时间，单位为毫秒
                .setSocketTimeout(10*1000) //设置数据传输的最长时间，单位为毫秒
                .build();

        //给请求设置请求信息
        httpGet.setConfig(config);

        CloseableHttpResponse response =null;

        try {
             response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
