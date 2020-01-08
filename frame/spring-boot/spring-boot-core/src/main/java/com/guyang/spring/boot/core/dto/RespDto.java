package com.guyang.spring.boot.core.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-12-31 17:41
 */
public class RespDto implements Serializable {

    private Integer respStatus;
    private String respMsg;
    private final Map params = new HashMap();


    public Map put(Object key, Object value) {
        params.put(key, value);
        return params;
    }

    public Map putAll(Map map){
        params.putAll(map);
        return params;
    }

    public RespDto(final Integer respStatus, final String respMsg) {
        this.respStatus = respStatus;
        this.respMsg = respMsg;
    }

    public RespDto() {
        this.respStatus = 201;
        this.respMsg = "操作成功";
    }

    public Integer getRespStatus() {
        return respStatus;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public Map getParams() {
        return params;
    }
}
