package org.openapitools.configuration;

import com.mic.product.infrastructure.domain.dto.ProductTypeDTO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration(value = "org.openapitools.configuration.enumConverterConfiguration")
public class EnumConverterConfiguration {

    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.productTypeConverter")
    Converter<String, ProductTypeDTO> productTypeConverter() {
        return new Converter<String, ProductTypeDTO>() {
            @Override
            public ProductTypeDTO convert(String source) {
                return ProductTypeDTO.fromValue(source);
            }
        };
    }

}
