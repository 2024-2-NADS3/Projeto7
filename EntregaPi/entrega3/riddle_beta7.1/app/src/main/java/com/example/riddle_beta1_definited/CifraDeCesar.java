package com.example.riddle_beta1_definited;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CifraDeCesar {

    // Método para criptografar o conteúdo usando a cifra de César
    public static String cifra(String mensagem, int deslocamento) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensagem.toCharArray()) {
            if (Character.isUpperCase(c)) {
                resultado.append((char) ((c - 'A' + deslocamento) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                resultado.append((char) ((c - 'a' + deslocamento) % 26 + 'a'));
            } else {
                resultado.append(c); // Mantém caracteres que não são letras
            }
        }
        return resultado.toString();
    }

    // Método para criptografar o conteúdo de um arquivo Java
    public static void criptografarArquivo(String caminhoArquivo, int deslocamento) throws IOException {
        String conteudo = new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
        String conteudoCriptografado = cifra(conteudo, deslocamento);
        String novoCaminho = caminhoArquivo.replace(".java", "_criptografado.java");
        Files.write(Paths.get(novoCaminho), conteudoCriptografado.getBytes());
        System.out.println("Arquivo criptografado salvo em: " + novoCaminho);
    }

    // Método para percorrer todos os arquivos .java de um diretório
    public static void criptografarTodosOsArquivos(String diretorio, int deslocamento) throws IOException {
        try (Stream<Path> arquivos = Files.walk(Paths.get(diretorio))) {
            arquivos
                    .filter(arquivo -> arquivo.toString().endsWith(".java")) // Filtra apenas arquivos .java
                    .forEach(arquivo -> {
                        try {
                            criptografarArquivo(arquivo.toString(), deslocamento);
                        } catch (IOException e) {
                            System.out.println("Erro ao criptografar o arquivo: " + arquivo);
                        }
                    });
        }
    }

    public static void main(String[] args) throws IOException {
        // Testando a cifra diretamente com uma mensagem
        String mensagemTeste = "Hello, World!";
        int deslocamento = 5;

        // Criptografa a mensagem
        String mensagemCriptografada = cifra(mensagemTeste, deslocamento);

        // Imprime a mensagem original e a criptografada
        System.out.println("Mensagem Original: " + mensagemTeste);
        System.out.println("Mensagem Criptografada: " + mensagemCriptografada);

        // Mensagem de confirmação
        System.out.println("A criptografia foi realizada com sucesso!");

        // Criptografar todos os arquivos .java no diretório
        String diretorio = "C:\\Users\\mauri\\PM\\riddle_beta7.1\\app\\src\\main\\java\\com\\example\\riddle_beta1_definited";
        criptografarTodosOsArquivos(diretorio, deslocamento);
    }
}
