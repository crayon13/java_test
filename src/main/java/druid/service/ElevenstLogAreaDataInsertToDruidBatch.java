package druid.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import druid.analytics.DruidDataInsertObject;
import druid.analytics.DruidDataSchema;
import druid.analytics.DruidDataSchemaParser;
import druid.analytics.DruidQuery;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.UnsupportedEncodingException;

public class ElevenstLogAreaDataInsertToDruidBatch {
    private static final long BATCH_NO = 11504;
    private static String BATCH_NM = "Log2.0 전시구좌 효율정보 적재 Druid Insert Batch";

    private static final String url = "http://172.29.64.71:8090/druid/indexer/v1/task";
    private static Gson gson = new GsonBuilder().setPrettyPrinting()
                                         .disableHtmlEscaping()
                                         .create();

    public static void main(String[] args) {

        try {
            DruidDataInsertObject insert
                = DruidQuery.DruidInsert.builder()
                                        .dataSource("elevenst_db_11stlog_area_analytics_v2")
                                        .ioDataGranularity("hour")
                                        .ioInputPath("/user/hive/warehouse/11st.db/elevenst_db_11stlog_area_analytics_v2")
                                        .ioFilePattern(".+")
                                        .ioPathFormat("'part_hour'=yyyyMMddHH")
                                        .dataSchemaParserColumns(
                                            Lists.newArrayList("log_time","page_id","poc_clf","area_label","trc_no","disp_spce_id", "pageview", "impression", "click")
                                        )
                                        .dataSchemaParserDimensionsSpec(
                                            Lists.newArrayList("page_id","poc_clf","area_label","trc_no","disp_spce_id")
                                        )
                                        .dataSchemaParserTimestampSpec(
                                            new DruidDataSchemaParser.ParseSpec.TimestampSpec("yyyyMMddHH", "log_time")
                                        )
                                        .dataSchemaMetricsSpecs(
                                            Lists.newArrayList(
                                                new DruidDataSchema.MetricsSpec("longSum", "pageview", "pageview")
                                                , new DruidDataSchema.MetricsSpec("longSum", "impression", "impression")
                                                , new DruidDataSchema.MetricsSpec("longSum", "click", "click")
                                            )
                                        )
                                        .intervals(
//                                            DruidDateUtils.DruidAnalyticsIntervalsByDay(
//                                                DateUtils.addDays(new Date(), -2)
//                                                , DateUtils.addDays(new Date(), 2)
//                                                , "yyyy-MM-dd")
                                                "2019-05-03/2019-05-03"
                                        )
                                        .build()
                                        .toQuery();

            HttpClient client = getHttpClient(2000);
            PostMethod post = getPostMethod(gson.toJson(insert), url);

            int returnValue = client.executeMethod(post);

            System.out.println(gson.toJson(insert));
            System.out.println(" returnValue  :  "+returnValue);

        } catch (Exception e) {

        }
    }
    private static PostMethod getPostMethod(String data, String url) throws UnsupportedEncodingException {
        PostMethod post = new PostMethod(url);

        StringRequestEntity requestEntity = new StringRequestEntity(data, "application/json", "UTF-8" );
        post.setRequestEntity(requestEntity);
        post.setRequestHeader("Content-Type","application/json");
        return post;
    }

    private static HttpClient getHttpClient(int timeout) {
        HttpConnectionManagerParams connectionManagerParams = new HttpConnectionManagerParams();
        connectionManagerParams.setConnectionTimeout(timeout);
        connectionManagerParams.setSoTimeout(timeout);

        HttpConnectionManager manager = new SimpleHttpConnectionManager();
        manager.setParams(connectionManagerParams);

        return new HttpClient(manager);
    }

}
