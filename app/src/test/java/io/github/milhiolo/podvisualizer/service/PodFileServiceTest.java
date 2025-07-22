// Define o pacote onde esta classe de teste está localizada.
// Certifique-se de que este pacote corresponde à estrutura de pastas do seu projeto:
// app/src/test/java/io/github/milhiolo/podvisualizer/service/
package io.github.milhiolo.podvisualizer.service;

// Importa as anotações e classes necessárias para testes JUnit 5 (JUnit Jupiter).
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir; // Para criar diretórios temporários para testes
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*; // Importa asserções estáticas para facilitar a leitura

/**
 * Classe de teste para o PodFileService.
 * Testa a funcionalidade de processamento de arquivos .pod.
 *
 * Anotações:
 * - @SpringBootTest: Carrega o contexto completo da aplicação Spring Boot para que
 * os beans (como PodFileService) possam ser injetados e testados em um ambiente real.
 */
@SpringBootTest
class PodFileServiceTest {

    // Injeta uma instância de PodFileService.
    // O Spring Boot gerencia a criação e injeção deste serviço.
    @Autowired
    private PodFileService podFileService;

    // Anotação @TempDir do JUnit 5 para criar um diretório temporário para os testes.
    // Isso é útil para criar arquivos de teste sem afetar o sistema de arquivos real.
    @TempDir
    Path tempDir;

    private File testPodFile; // Arquivo .pod temporário para os testes

    /**
     * Método executado antes de cada teste.
     * Usado para configurar o ambiente de teste, como criar um arquivo .pod temporário.
     */
    @BeforeEach
    void setUp() throws IOException {
        // Cria um arquivo .pod de exemplo para o teste.
        // ATENÇÃO: Para um teste real, você precisaria de um arquivo .pod válido.
        // Este é apenas um placeholder para que o teste compile.
        testPodFile = tempDir.resolve("test_project.pod").toFile();
        // Para um teste funcional, você precisaria copiar um arquivo .pod real para testPodFile.
        // Exemplo: Files.copy(Paths.get("caminho/para/seu/arquivo/real.pod"), testPodFile.toPath());
        
        // Simplesmente cria um arquivo vazio para evitar FileNotFoundException por enquanto.
        // Em um cenário real, o conteúdo deste arquivo precisaria ser um .pod válido.
        Files.writeString(testPodFile.toPath(), "Conteúdo de teste para .pod");
    }

    /**
     * Testa o método processFile com um arquivo .pod existente.
     */
    @Test
    void testProcessFile_existingFile() throws IOException {
        // Chama o método processFile com o caminho do arquivo .pod temporário.
        Map<String, Object> projectData = podFileService.processFile(testPodFile.getAbsolutePath());

        // Verifica se os dados do projeto não são nulos.
        assertNotNull(projectData, "Os dados do projeto não devem ser nulos.");
        
        // Exemplo de asserções (adicione mais conforme a estrutura real dos seus dados .pod):
        // assertTrue(projectData.containsKey("projectName"), "Deve conter o nome do projeto.");
        // assertTrue(projectData.containsKey("tasks"), "Deve conter a lista de tarefas.");
        // assertTrue(projectData.containsKey("resources"), "Deve conter a lista de recursos.");
        
        // Para um teste mais completo, você faria asserções sobre os valores esperados
        // baseados no conteúdo do seu arquivo .pod de teste.
        // Exemplo: assertEquals("Meu Projeto Teste", projectData.get("projectName"));
    }

    /**
     * Testa o método processFile com um caminho de arquivo que não existe.
     */
    @Test
    void testProcessFile_fileNotFound() {
        // Define um caminho para um arquivo que certamente não existe.
        String nonExistentFilePath = "/path/to/non/existent/file.pod";

        // Verifica se o método lança uma IOException quando o arquivo não é encontrado.
        IOException thrown = assertThrows(IOException.class, () -> {
            podFileService.processFile(nonExistentFilePath);
        }, "Deveria lançar IOException para arquivo não encontrado.");

        // Verifica se a mensagem de erro contém a string esperada.
        assertTrue(thrown.getMessage().contains("Arquivo não encontrado"), "A mensagem de erro deve indicar que o arquivo não foi encontrado.");
    }

    // Adicione mais métodos de teste para diferentes cenários, como:
    // - Arquivo .pod inválido/corrompido
    // - Arquivo .pod com diferentes estruturas de tarefas/recursos
    // - Testes de integração com o Spring context, se necessário
}
