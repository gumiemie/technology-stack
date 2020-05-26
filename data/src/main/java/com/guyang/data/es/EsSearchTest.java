package com.guyang.data.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 使用java查询es的测试类
 * @date 2020-01-10 16:28
 */
public class EsSearchTest {
    private static final RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("10.1.1.199", 9200, "http")));
    //elasticsearch索引名称
    private static final String INDEX = "data_center_project";

    //elasticsearch索引数据类型
    private static final String TYPE = "project";

    @Test
    public void first() throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.types(TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.terms("bidderId").field("bidderId").size(Integer.MAX_VALUE).subAggregation(AggregationBuilders.sum("tradePrice").field("amountCny")));

        searchRequest.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest);

        List<? extends Terms.Bucket> buckets = ((ParsedStringTerms) (response.getAggregations().get("bidderId"))).getBuckets();
        buckets.forEach(buck->{
            buck.getKeyAsNumber();
            long docCount = buck.getDocCount();
            Double tradePrice = ((ParsedSum) (buck.getAggregations().get("tradePrice"))).getValue();
        });
        System.out.println("查询结束");
    }


}
