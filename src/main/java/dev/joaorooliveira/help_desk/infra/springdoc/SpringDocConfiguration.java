package dev.joaorooliveira.help_desk.infra.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Help Desk API")
                        .description(
                                """
                                API REST para gerenciamento de chamados de suporte técnico de TI.

                                Principais funcionalidades:

                                - Cadastro e gerenciamento de funcionários
                                - Cadastro e gerenciamento de técnicos
                                - Abertura e gerenciamento de chamados
                                - Atribuição de chamados a técnicos
                                - Conclusão e cancelamento de chamados
                                - Controle de status e regras de negócio
                                - Filtros e consultas paginadas
                                - Relatórios e estatísticas de chamados

                                Projeto desenvolvido com Spring Boot para fins educacionais.
                                """
                        )
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("João Oliveira")
                                .email("oliveira.joaov@proton.me"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local")
                ))
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Repositório do Projeto")
                                .url("https://github.com/Joaorooliveira/help-desk")
                );
    }
}