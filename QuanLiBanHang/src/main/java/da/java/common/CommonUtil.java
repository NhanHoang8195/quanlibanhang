package da.java.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    
    private static final ObjectMapper mapper = new ObjectMapper();
    public static Object getObjectFromJson(String json, Class<?> T){
        
        logger.debug("IN - getObjectFromJson()");
        
        Object obj = null;
        try {
            obj = mapper.readValue(json, T);
        } catch(Exception e){
            System.out.println(e);
        } finally {
            logger.debug("OUT - getObjectFromJson()");
        }
        return obj;
        
    }
    
    
    public static String parseObjectToJson(Object data) {
        logger.debug("IN - parseObjectToJson()");
        String json = null;
        try {
            json = mapper.writeValueAsString(data);
            return json;
        } catch(Exception e) {
            logger.error("Can not parse Object to json");
            return json;
        } finally {
            logger.debug("OUT - parseObjectToJson()");
        }
    }
}
