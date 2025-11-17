package com.projeto.bloco.gestao_fornecedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class GestaoFornecedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoFornecedoresApplication.class, args);
	}

}
