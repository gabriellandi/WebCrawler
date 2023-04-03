package org.example.WebCrawlerScraping

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.commons.io.FilenameUtils
import org.example.Model.Arquivos
import org.example.WebCrawlerScraping.AcessaSite
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class ScrapingVersaoMesAno {
    AcessaSite acess = new AcessaSite()

    Document versaoMesAno(){
        Document paginaTiss = acess.acessaPaginaTiss()
        Element botao = paginaTiss.select("a:contains(Clique aqui para acessar a versão)").first()
        String url = botao.attr("href")
        Connection.Response response = Jsoup.connect(url).execute()
        Document versaoMesAno = response.parse()
        return versaoMesAno
    }

    List<Element> tabelaArquivosTiss(){
        Document versaoMesAno = versaoMesAno()
        Element tabela = versaoMesAno.getElementsByClass("table").first()
        Elements conteudoTabela = tabela.getElementsByTag("tbody").first().getElementsByTag("tr")
        return conteudoTabela.toList()
    }

    List<Arquivos> instanciaListaObjetos(){
        List<Element> tabela = tabelaArquivosTiss()
        ArrayList<Arquivos> listaArquivos = new ArrayList<Arquivos>()

        tabela.forEach {tr ->
            String name = tr.getElementsByTag("td").get(0).text()
            String versao = tr.getElementsByTag("td").get(1).text()
            String link = tr.getElementsByTag("a").first().attr("href")

            Arquivos arquivo = new Arquivos(name, versao, link.toString())
            listaArquivos.add(arquivo)
        }
        return listaArquivos
    }

    void baixaArquivos(List<Arquivos> urls) {
        urls.each { url ->
            def extension = FilenameUtils.getExtension(url.link)

            // Define o nome do arquivo a partir da URL e da extensão
            def outputFileName = new URL(url.link).getFile().tokenize('/').last()

            def http = new HTTPBuilder(url.link)
            def outputFile = new File("./Downloads/Aquivos_padrao_TISS/" + outputFileName)

            http.request(Method.GET, ContentType.ANY) { req ->
                // Define o output da resposta para um arquivo
                response.success = { resp, stream ->
                    outputFile.withOutputStream { out ->
                        stream.eachByte(1024) { buffer, length -> out.write(buffer, 0, length) }
                    }
                }

                // Trata erros
                response.failure = { resp ->
                    println "Erro ao baixar arquivo: ${resp.statusLine}"
                }
            }

            if (outputFile.exists()) {
                println "Download concluído! O arquivo foi salvo em ${outputFile.absolutePath}."
            } else {
                println "Erro: o arquivo não foi baixado corretamente."
            }
        }
    }

}
