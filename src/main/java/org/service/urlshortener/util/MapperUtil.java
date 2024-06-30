package org.service.urlshortener.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.InvalidJsonDataException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Slf4j
public class MapperUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper mapper(){
        var deserializationFeature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
        var serializationFeature = SerializationFeature.FAIL_ON_EMPTY_BEANS;

        mapper
                .setSerializationInclusion(NON_NULL);

        mapper
                .configure(deserializationFeature, false)
                .configure(serializationFeature, false);

        mapper
                .registerModule(new JavaTimeModule())
                .disable(WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    public static String writeValueAsString(Object object){
        try{
            return mapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            log.error("[ERROR] Exception ->{}", e.getMessage());
            throw new InvalidJsonDataException(ErrorMessage.INVALID_JSON_DATA_ERROR);
        }
    }

    @SneakyThrows
    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return mapper().readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error("[ERROR] Exception -> {}", e.getMessage());
            throw new InvalidJsonDataException(ErrorMessage.INVALID_JSON_DATA_ERROR);
        }
    }

    @SneakyThrows
    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("[ERROR] Exception -> {}", e.getMessage());
            throw new InvalidJsonDataException(ErrorMessage.INVALID_JSON_DATA_ERROR);
        }
    }
}
