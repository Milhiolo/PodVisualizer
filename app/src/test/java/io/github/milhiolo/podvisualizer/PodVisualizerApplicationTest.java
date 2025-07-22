// Define o pacote onde esta classe de teste está localizada.
// Certifique-se de que este pacote corresponde à estrutura de pastas do seu projeto:
// app/src/test/java/io/github/milhiolo/podvisualizer/
package io.github.milhiolo.podvisualizer;

// Importa as anotações e classes necessárias para testes Spring Boot com JUnit 5.
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Classe de teste para a aplicação PodVisualizer.
 * Esta classe é usada para garantir que o contexto da aplicação Spring Boot
 * carrega corretamente.
 *
 * Anotações:
 * - @SpringBootTest: Indica que esta é uma classe de teste do Spring Boot.
 * Ela carrega o contexto completo da aplicação Spring, permitindo que você
 * teste seus componentes Spring (controladores, serviços, repositórios, etc.)
 * como eles seriam executados em um ambiente real.
 */
@SpringBootTest
class PodVisualizerApplicationTests {

    /**
     * Teste simples para verificar se o contexto da aplicação Spring Boot carrega.
     * Se o contexto carregar sem exceções, o teste é considerado bem-sucedido.
     */
    @Test
    void contextLoads() {
        // Este método não precisa de nenhuma lógica específica.
        // A simples execução e o carregamento bem-sucedido do contexto já validam o teste.
    }

}
