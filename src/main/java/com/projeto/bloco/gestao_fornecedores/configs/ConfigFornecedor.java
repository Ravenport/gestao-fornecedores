package com.projeto.bloco.gestao_fornecedores.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigFornecedor {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
