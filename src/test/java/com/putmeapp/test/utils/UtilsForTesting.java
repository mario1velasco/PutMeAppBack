package com.putmeapp.test.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UtilsForTesting {

    @Autowired
    private MockMvc mockMvc;

    public JSONArray getJSONArray(String endPoint) throws Exception {
        MvcResult result = this.mockMvc.perform(get(endPoint)).andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        JSONArray data = new JSONArray(content);
        return data;
    }

    public JSONObject getFirstJSONObjectFromJSONArray(String endPoint) throws Exception {
        MvcResult result = this.mockMvc.perform(get(endPoint)).andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        JSONArray data = new JSONArray(content);
        JSONObject firstObject = data.getJSONObject(0);
        return firstObject;
    }

    public JSONObject getJSONObject(String endPoint) throws Exception {
        MvcResult result = this.mockMvc.perform(get(endPoint)).andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject data = new JSONObject(content);
        return data;
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
