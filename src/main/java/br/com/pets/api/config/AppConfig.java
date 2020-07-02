package br.com.pets.api.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
@Configuration
public class AppConfig {
	  @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	    @Bean
		public ObjectMapper objectMapper() {
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
			ObjectMapper dateFormatMapper = new ObjectMapper();

			dateFormatMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			dateFormatMapper.registerModule(new ParameterNamesModule())
					.registerModule(new Jdk8Module())
					.registerModule(new JavaTimeModule()); // new module, NOT JSR310Module

			dateFormatMapper.setDateFormat(dateFormat);
		  return dateFormatMapper;
		}

	}


