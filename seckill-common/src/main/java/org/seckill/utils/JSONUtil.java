package org.seckill.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 */
public class JSONUtil {
    private static final Logger log = LoggerFactory.getLogger(JSONUtil.class);

    private JSONUtil() {
    }

    // 获取ObjectMapper, 可自由调用其方法
    public static ObjectMapper getInstance() {
        return ObjectMapperHolder.INSTANCE;
    }

    /**
     * JSON转字符串
     */
    public static String obj2json(Object obj) {
        String resultJson = "";
        try {
            resultJson = JSONUtil.getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        return resultJson;
    }

    /**
     * 功能描述: JSON转字符串忽略空值
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     * @Date: 2020/1/22 13:33
     */
    public static String obj2jsonIgnoreNull(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String resultJson = "";
        try {
            resultJson = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        return resultJson;
    }

    /**
     * 功能描述: JSON to Java object
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     * @Date: 2020/1/22 13:39
     */
    public static <T> T json2Bean(String jsonString, Class<T> clz) {
        JSONUtil.getInstance().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        T t = null;
        try {
            t = JSONUtil.getInstance().readValue(jsonString, clz);
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        return t;
    }

    /**
     * 功能描述: JSON String 转 Map<String, Object>
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     * @Date: 2020/1/22 14:14
     */
    public static Map<String, Object> json2map(String jsonString) {
        Map<String, Object> resultMap = null;
        try {
            resultMap = JSONUtil.getInstance().readValue(jsonString, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        return resultMap;
    }

    /**
     * 功能描述: object 转 map
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    public static Map<String, Object> obj2map(Object obj) {
        return JSONUtil.getInstance().convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 功能描述: JSON String 转 Map<String, String>
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    public static Map<String, String> json2StrMap(String jsonString) {
        Map<String, String> resultMap = null;
        try {
            resultMap = JSONUtil.getInstance().readValue(jsonString, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        return resultMap;
    }

    /**
     * 功能描述: map转JavaBean
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     * @Date: 2020/1/22 14:38
     */
    public static <T> T map2bean(Map<String, Object> map, Class<T> clz) {
        return JSONUtil.getInstance().convertValue(map, clz);
    }

    /**
     * 功能描述: 字符串转 Map<String, T>
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     * @Date: 2020/1/22 14:25
     */
    public static <T> Map<String, T> json2GenericsMap(String jsonString, Class<T> clz) {
        Map<String, Map<String, Object>> map = null;
        try {
            map = JSONUtil.getInstance().readValue(jsonString, new TypeReference<Map<String, Map<String, Object>>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        Map<String, T> result = new HashMap<>(16);
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2bean(entry.getValue(), clz));
        }
        return result;
    }

    /**
     * 功能描述: Map 转 JSON String
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    public static String map2json(Map map) {
        ObjectMapper objectMapper = JSONUtil.getInstance();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        String resultJson = "";
        try {
            resultJson = objectMapper.writeValueAsString(map);
        } catch (IOException e) {
            log.error("JSON Convert Failed: ", e);
        }
        return resultJson;
    }

    /**
     * 功能描述: 转换泛型Collection
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... typeElementClass) {
        ObjectMapper objectMapper = JSONUtil.getInstance();
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, typeElementClass);
    }

    public static <T> List<T> jsonToTypeList(String jsonString, Class<T> clz) {
        ObjectMapper objectMapper = JSONUtil.getInstance();
        JavaType javaType = getCollectionType(List.class, clz);
        List<T> list = null;
        try {
            list = objectMapper.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            log.error("Json Convert Failed: ", e);
        }
        return list;
    }

    private static class ObjectMapperHolder {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }
}
