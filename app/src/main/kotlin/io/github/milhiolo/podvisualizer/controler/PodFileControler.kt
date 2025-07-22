// Define o pacote onde esta classe está localizada.
// Certifique-se de que este pacote corresponde à estrutura de pastas do seu projeto:
// app/src/main/kotlin/io/github/milhiolo/podvisualizer/controller/
package io.github.milhiolo.podvisualizer.controller

// Importa o serviço que lida com o processamento de arquivos .pod.
import io.github.milhiolo.podvisualizer.service.PodFileService
// Importa classes do Spring Framework para lidar com respostas HTTP.
import org.springframework.http.ResponseEntity
// Importa anotações do Spring Framework para definir um controlador REST e mapear requisições.
import org.springframework.web.bind.annotation.*
// Importa a classe para lidar com o upload de arquivos.
import org.springframework.web.multipart.MultipartFile
// Importa a classe File para manipulação de arquivos temporários.
import java.io.File

/**
 * Controlador REST para o processamento de arquivos .pod.
 * Gerencia as requisições HTTP relacionadas ao upload e processamento de arquivos de projeto.
 *
 * Anotações:
 * - @RestController: Uma anotação de conveniência que combina @Controller e @ResponseBody.
 * Indica que esta classe é um controlador de Spring e que os valores de retorno dos métodos
 * devem ser diretamente vinculados ao corpo da resposta HTTP.
 * - @RequestMapping("/api/pod"): Mapeia todas as requisições que começam com "/api/pod" para este controlador.
 * - @CrossOrigin(origins = ["http://localhost:4200"]): Habilita o Cross-Origin Resource Sharing (CORS)
 * para permitir requisições do frontend Angular que provavelmente estará rodando em uma porta diferente (ex: 4200).
 * Ajuste esta URL para a URL real do seu frontend Angular em produção.
 */
@RestController
@RequestMapping("/api/pod")
@CrossOrigin(origins = ["http://localhost:4200"])
class PodFileController(
    // Injeção de dependência: O Spring automaticamente fornece uma instância de PodFileService.
    private val podFileService: PodFileService
) {

    /**
     * Endpoint para fazer upload e processar um arquivo .pod.
     *
     * @param file O arquivo MultipartFile enviado na requisição. O nome do parâmetro
     * na requisição HTTP deve ser "file" (@RequestParam("file")).
     * @return ResponseEntity contendo um mapa dos dados do projeto ou uma mensagem de erro.
     */
    @PostMapping("/upload-and-process")
    fun uploadAndProcessPodFile(@RequestParam("file") file: MultipartFile): ResponseEntity<Map<String, Any>> {
        // Verifica se o arquivo enviado está vazio.
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body(mapOf("error" to "O arquivo enviado está vazio."))
        }

        return try {
            // Cria um arquivo temporário no sistema de arquivos para armazenar o conteúdo do upload.
            // Isso é necessário porque a biblioteca MPXJ geralmente trabalha com File ou InputStream,
            // e transferir o MultipartFile para um File temporário é uma abordagem comum.
            val tempFile = File.createTempFile("uploaded-pod-", ".pod")
            // Transfere o conteúdo do MultipartFile para o arquivo temporário.
            file.transferTo(tempFile)

            // Chama o serviço para processar o arquivo .pod e obter os dados do projeto.
            val projectData = podFileService.processFile(tempFile.absolutePath)
            // Deleta o arquivo temporário após o processamento para limpar recursos.
            tempFile.delete()

            // Retorna uma resposta HTTP 200 OK com os dados do projeto.
            ResponseEntity.ok(projectData)
        } catch (e: Exception) {
            // Em caso de erro durante o processamento, retorna uma resposta HTTP 500 Internal Server Error
            // com uma mensagem de erro.
            ResponseEntity.internalServerError().body(mapOf("error" to "Erro ao processar arquivo: ${e.message}"))
        }
    }
}
