package com.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JSONUtil {
	
	public static Logger log = LoggerFactory.getLogger(JSONUtil.class);
	
	public static <T> String getJsonStr(T t) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(t);
		}catch(JsonProcessingException ex){
			log.error("parse json fail"+ex.getMessage());
			return "";
		}
	}
	
	public static <T> T readJsonToObj(String json, Class<T> t) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json,t); 
		}catch(JsonProcessingException ex){
			ex.printStackTrace();
			log.error(ex.getMessage());
			log.error("jackson parse object fail");
//			throw new ServiceException(STATE.ERROR_JSON);
		}catch(IOException ex){
			log.error("jackson parse object fail");
//			throw new ServiceException(STATE.ERROR_JSON);
		}
		return null;
	}

}
