package com.guyang.data.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-02 14:06
 */
public class SolrClientExample {

    //连接单点solr
    private final static SolrClient httpSolrClient = new HttpSolrClient.Builder("http://211.151.208.173:8983/solr").build();

    //通过zk连接solr集群
    private final static SolrClient cloudSolrrClient = new CloudSolrClient.Builder(Arrays.asList("slsl","wwww"), Optional.empty()).build();


    @Test
    public void execute(){

    }

}

