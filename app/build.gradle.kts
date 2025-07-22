plugins {
    // Aplica o plugin de Kotlin para JVM
    kotlin("jvm") version "1.9.22" // Use a versão mais recente compatível com o Spring Boot

    // Aplica o plugin do Spring Boot
    id("org.springframework.boot") version "3.2.7" // Use a versão mais recente estável do Spring Boot
    
    // Gerencia dependências do Spring Boot de forma mais fácil
    id("io.spring.dependency-management") version "1.1.5" // Use a versão mais recente estável

    // O plugin 'application' ainda é útil para `gradle run` e scripts de execução simples,
    // mas o `bootJar` do Spring Boot será o principal para o JAR distribuível.
    application
}

group = "com.seuprojeto" // Substitua pelo seu grupo/pacote (ex: com.podvisualizer)
version = "1.0-SNAPSHOT" // Versão do seu aplicativo

// Define a versão da JVM que seu projeto usará para compilação e execução
java {
    sourceCompatibility = JavaVersion.VERSION_21 // Definido para Java 21
    targetCompatibility = JavaVersion.VERSION_21 // Definido para Java 21
}

repositories {
    // Onde o Gradle procura pelas bibliotecas
    mavenCentral() // Repositório Maven central, a maioria das libs estão aqui
    // maven("https://repo.spring.io/milestone") // Exemplo: se precisar de versões milestone do Spring
    // maven("https://jitpack.io") // Exemplo: se precisar de libs do GitHub via JitPack
}

dependencies {
    // Dependências do Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Essencial para Spring Boot com Kotlin
    // Removido: implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") - Não é necessário para Java 21

    // Dependência do Spring Boot para criar a API Web (servidor embarcado, REST Controllers)
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // Se você for manipular XML/JSON diretamente, adicione Jackson (já vem com spring-boot-starter-web, mas bom saber)
    // implementation("com.fasterxml.jackson.module:jackson-module-kotlin") 

    // **Suas dependências para ProjectLibre e MXJP**
    // ESTAS SÃO EXEMPLOS. VOCÊ PRECISA ENCONTRAR AS COORDENADAS MAJESTICAS CORRETAS.
    // Pesquise por "ProjectLibre Maven dependency" e "MXJP Maven dependency".
    // Ex:
    // implementation("org.projectlibre:projectlibre-core:X.Y.Z") // Substitua X.Y.Z pela versão correta
    // implementation("com.mycompany:mxjp:A.B.C") // Substitua A.B.C pela versão correta, e groupId/artifactId

    // Dependências de teste (JUnit Jupiter é o padrão para Spring Boot com Kotlin)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0")) // Garante que todas as libs do JUnit 5 tenham a mesma versão
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5") // Suporte Kotlin para JUnit 5
}

// Configuração do plugin 'application' (para `gradle run`)
application {
    // Sua classe principal que inicia o Spring Boot
    mainClass.set("com.seuprojeto.podvisualizer.PodVisualizerApplicationKt") // Se for Kotlin, geralmente termina com "Kt"
}

// Configuração para testes com JUnit Platform (JUnit 5)
tasks.test {
    useJUnitPlatform()
}

// Configuração para o JAR executável do Spring Boot (tarefa `bootJar`)
// Isso criará um JAR único que inclui todas as dependências e pode ser executado.
tasks.bootJar { // Note o 'tasks.' aqui
    archiveFileName.set("pod-visualizer.jar") // Nome do arquivo JAR final
    mainClass.set("com.seuprojeto.podvisualizer.PodVisualizerApplicationKt") // Sua classe principal para o JAR executável
}

// Opcional: Tarefa para compilar o Kotlin e o Java
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21" // Definido para Java 21
    }
}