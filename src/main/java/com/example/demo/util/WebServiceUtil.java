package com.example.demo.util;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
public class WebServiceUtil {
    private static final Logger log = LoggerFactory.getLogger(WebServiceUtil.class);

    private WebServiceUtil() {

    }

    /**
     * WebServiceUtil 通用调用webservice接口
     *
     * @description 动态调用
     * @param wsdlAddress wsdl地址
     * @param method 调用方法
     * @param parameters 参数列表，按照接口参数顺序依次放入数组
     * @return
     * @author wangjing-5
     * @date 2019/3/22 16:03
     * @version V3.0.0
     */
    public static String sendData(String wsdlAddress, String method, Object[] parameters) throws Exception {
        if (StringUtils.isBlank(wsdlAddress)) {
            log.info("WSDL地址调用前检测是否可用：wsdl地址为空，直接退出");
            return null;
        }
        try {
            /** 如果wsdl地址不为空，先验证此地址是否可用 */
            HttpURLConnection connection = (HttpURLConnection) new URL(wsdlAddress).openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(30000);
            if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                log.info("WSDL地址不可用:{}", wsdlAddress);
            }
        } catch (IOException e) {
            /** 连接失败，直接抛出异常，继续访问下一台集群服务器，避免长时间连接占用资源 */
            log.error("WSDL地址调用前检测是否可用：经检测连接不可用", e);
            throw new ConnectException();
        }
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient(wsdlAddress);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        /** 设置超时时间 */
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(3000);
        policy.setReceiveTimeout(30000);
        conduit.setClient(policy);

        /** 处理由于webservice接口和实现不在同一个包下，namespace不一致，而找不到调用方法的问题 */
        Endpoint endpoint = client.getEndpoint();
        String nameSpace = endpoint.getService().getName().getNamespaceURI();
        QName opName = new QName(nameSpace, method);
        BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();
        if (bindingInfo.getOperation(opName) == null) {
            for (BindingOperationInfo operationInfo : bindingInfo.getOperations()) {
                if (method.equals(operationInfo.getName().getLocalPart())) {
                    opName = operationInfo.getName();
                    break;
                }
            }
        }
        try {
            Object[] objects = client.invoke(opName, parameters);
            return objects[0].toString();
        } catch (Exception e) {
            log.error("webService动态调用接口异常，【url】:{},【method】:{},【parameters】:{}", wsdlAddress, method, parameters, e);
        } finally {
            /** 为了防止服务端改了方法名以后,客户端仍然获取的是老的方法名 */
            BusFactory.setDefaultBus(null);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String requestXml="<xml><data><header><tranNo>ec9a89b6-a5ca-4d92-a252-62226c6ecd24</tranNo><svcName>CommissionServer</svcName><svcCode>LIS_CMS_0001</svcCode><oriSys>OLDLIS</oriSys><operator>System</operator><transTime>2021-01-12 09:45:54</transTime></header><body><commission><serialNo /><manageCom>86230100</manageCom><contNo>10582301212390458166</contNo><polNo>10000000000239106302</polNo><mainPolNo>10000000000239106302</mainPolNo><polType>1</polType><polState>1</polState><riskCode>L11210</riskCode><riskType>H</riskType><subRiskFlag>M</subRiskFlag><payDate>2020-03-01</payDate><CValiDate>2021-01-13</CValiDate><signdate>2021-01-12</signdate><signTime>09:44:07</signTime><getPolDate /><visitDate /><hesitationDays>15</hesitationDays><hesitationStartDate /><hesitationEndDate /><prem>442</prem><premType>01</premType><payCount>1</payCount><polYear>1</polYear><payYearsType>A</payYearsType><payYears>1000</payYears><yearsType>Y</yearsType><years>1</years><payIntv>0</payIntv><payMode /><saleChnnl>01</saleChnnl><branchType>1</branchType><agentCode>GX01000234</agentCode><serviceAgentCode>GX01000234</serviceAgentCode><selfFlag /></commission></body></data></xml>";
        String result=WebServiceUtil.sendData("http://10.10.64.186:18820/services/commissionInputService?wsdl","commissionInput",new Object[]{requestXml,"000001","5059b6e4-dafd-4501-b436-1ee6d7e7a1ef"});
        System.out.println("-------==========---------------"+result);
    }
}
