# PodVisualizer

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-JVM-blueviolet?style=for-the-badge&logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.7-brightgreen?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.14.2-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-Mihhiolo-black?style=for-the-badge&logo=github)

---

## 📄 Descrição do Projeto

O **PodVisualizer** é uma aplicação backend desenvolvida em **Kotlin** com **Spring Boot** que tem como objetivo principal processar e extrair dados de arquivos com a extensão `.pod`, que são gerados pelo software de gerenciamento de projetos **ProjectLibre**.

Este backend atua como um serviço, expondo uma API HTTP que será consumida por uma aplicação frontend (atualmente em desenvolvimento com Angular). O frontend enviará o caminho (ou os bytes, se for um upload) do arquivo `.pod` para o PodVisualizer, que então lerá, interpretará e converterá o conteúdo desse arquivo em formatos mais amigáveis para visualização, como **planilhas (por exemplo, Excel)** ou **documentos PDF**.

### Funcionalidades Planejadas:

* **Processamento de Arquivos `.pod`**: Leitura e parsing do conteúdo dos arquivos do ProjectLibre.
* **API RESTful**: Exposição de endpoints para receber requisições do frontend com os dados dos arquivos.
* **Geração de Planilhas**: Conversão dos dados do `.pod` para formatos de planilha (e.g., `.xlsx`).
* **Geração de PDF**: Conversão dos dados do `.pod` para formato PDF.
* **Integração com ProjectLibre e MXJP**: Utilização de bibliotecas específicas para manipulação dos dados de projeto.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem**: Kotlin (JVM)
* **Framework Web**: Spring Boot 3.2.7
* **Build Tool**: Gradle 8.14.2 (Kotlin DSL)
* **Java Version**: OpenJDK 21
* **Bibliotecas de Processamento**:
    * ProjectLibre Core (para manipulação de arquivos `.pod`) - *Dependência a ser confirmada*
    * MXJP (para leitura/gravação de dados de projeto) - *Dependência a ser confirmada*
    * Apache POI (para geração de arquivos Excel) - *A ser adicionado*
    * iText / Apache PDFBox (para geração de arquivos PDF) - *A ser adicionado*
* **Testes**: JUnit Jupiter (JUnit 5) com Spring Test

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

* **OpenJDK 21** ou superior
* **Git**
* **Gradle** (opcional, o projeto inclui o Gradle Wrapper)

### Clonando o Repositório

```bash
git clone [https://github.com/Mihhiolo/PodVisualizer.git](https://github.com/Mihhiolo/PodVisualizer.git)
cd PodVisualizer
