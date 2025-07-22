/* Esse arquivo será a classe principal do sistema */
// Define o pacote onde esta classe está localizada.
// Certifique-se de que este pacote corresponde à estrutura de pastas do seu projeto:
// app/src/main/java/io/github/milhiolo/podvisualizer/
package io.github.milhiolo.podvisualizer;

// Importa as classes necessárias do Spring Framework para criar uma aplicação Spring Boot.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A classe principal da aplicação PodVisualizer.
 * Esta classe é o ponto de entrada para a sua aplicação Spring Boot.
 *
 * Anotações:
 * - @SpringBootApplication: É uma anotação de conveniência que adiciona:
 * - @Configuration: Marca a classe como uma fonte de definições de beans para o contexto da aplicação.
 * - @EnableAutoConfiguration: Habilita a configuração automática do Spring Boot,
 * que tenta configurar automaticamente sua aplicação Spring com base nas dependências JAR que você adicionou.
 * - @ComponentScan: Habilita a varredura de componentes para encontrar outros componentes,
 * configurações e serviços nos pacotes definidos.
 */
@SpringBootApplication
public class PodVisualizerApplication {

    /**
     * O método principal que é o ponto de entrada da aplicação Java.
     *
     * @param args Argumentos de linha de comando passados para a aplicação.
     */
    public static void main(String[] args) {
        // Inicia a aplicação Spring Boot.
        // SpringApplication.run() inicializa o contexto Spring, configura o servidor web embarcado
        // (como o Tomcat, por padrão) e implanta a aplicação.
        SpringApplication.run(PodVisualizerApplication.class, args);
    }
}
