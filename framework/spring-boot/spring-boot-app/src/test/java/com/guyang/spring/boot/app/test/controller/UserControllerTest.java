package com.guyang.spring.boot.app.test.controller;

import com.guyang.spring.boot.app.test.common.BaseTestApplication;
import com.guyang.spring.boot.model.User;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-15 17:44
 */
@WebAppConfiguration
public class UserControllerTest extends BaseTestApplication {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MockHttpSession mockHttpSession;

    @Before
    public void initMockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockHttpSession = new MockHttpSession();

        User guyang = new User("guyang", "123456");

        mockHttpSession.setAttribute("user", guyang);

    }


    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/json/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void save1() throws Exception {
        //application/x-www-form-urlencoded
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                Arrays.asList(new BasicNameValuePair("loginName", "guyang")), "utf-8");
        mockMvc.perform(MockMvcRequestBuilders.post("/user/json/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_FORM_URLENCODED).content(EntityUtils.toString(formEntity))
                //.param("loginName","guyang").param("password","123456")//表单提交
        ).andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void save2() throws Exception {
        //jsonString
        String jsonParam = "{\"loginName\":\"guyang\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/json/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonParam))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void save3() throws Exception {

        //urlParams
        mockMvc.perform(MockMvcRequestBuilders.get("/user/json/save"))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void save4() throws Exception {

        //urlParams
        mockMvc.perform(MockMvcRequestBuilders.get("/user/json/save")
                .param("loginName","ggg")
                .param("phoneNumber","1")
        )
                .andDo(MockMvcResultHandlers.print());


    }

}
