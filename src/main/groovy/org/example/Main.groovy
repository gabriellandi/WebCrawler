import org.example.WebCrawlerScraping.ScrapingErrosNoEnvio
import org.example.WebCrawlerScraping.ScrapingHistoricoVersoes
import org.example.WebCrawlerScraping.ScrapingVersaoMesAno

public class Main {
    public static void main(String[] args) throws IOException {
        ScrapingErrosNoEnvio planilhaErros = new ScrapingErrosNoEnvio()
        ScrapingVersaoMesAno arquivosTiss = new ScrapingVersaoMesAno()
        ScrapingHistoricoVersoes tabelaVersoes = new ScrapingHistoricoVersoes()

            Scanner leitor = new Scanner(System.in)
            boolean app = true
            String verificador = "";
            while(app) {
                println()
                println "Seja bem vindo ao Crawler\n" +
                        "Digite o numero correspondente à ação desejada\n" +
                        "1. Baixar Tabela de erros no envio para a ANS\n" +
                        "2. Baixar Padrão TISS atualizado\n" +
                        "3. Listar Histórico das versões dos Componentes do Padrão TISS a partir de JAN/2016\n" +
                        "4. Encerrar a aplicação"
                println("Digite um número de 1 a 4: ")
                verificador = leitor.nextLine()
                String regexVerificador = '^[1234]$'
                if(verificador.matches(regexVerificador)){
                    switch (verificador){
                        case "1":
                            println "Para salvar os arquivos encerre a aplicação"
                            planilhaErros.baixaPlanilhaErros()
                            break
                        case "2":
                            println "Para salvar os arquivos encerre a aplicação"
                            arquivosTiss.baixaArquivos(arquivosTiss.instanciaListaObjetos())
                            break
                        case "3":
                            List versoes = tabelaVersoes.instanciaListaHistoricoVersoes()
                            versoes.forEach {versao ->
                                println versao.toString()
                            }
                            break
                        case "4":
                            app = false
                            break
                    }
                } else {
                    println "Digite uma opção valida"
                }

            }
            leitor.close()
    }
}