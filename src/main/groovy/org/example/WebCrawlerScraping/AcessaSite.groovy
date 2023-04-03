package org.example.WebCrawlerScraping

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpBuilder
import org.jsoup.Connection
import org.jsoup.Connection.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.apache.commons.io.FilenameUtils
import groovyx.net.http.Method
import groovyx.net.http.ContentType


class AcessaSite {

    Document carregaPagina() {
        HttpBuilder requestUrlPrincipal = HttpBuilder.configure {
            request.uri = 'https://www.gov.br/ans/pt-br'
        }

        Object responseUrlPrincipal = requestUrlPrincipal.get()

        Document documentUrlPrincipal = Jsoup.parse(responseUrlPrincipal.toString())

        return documentUrlPrincipal
    }

    Document acessaEspacoPrestadores(){
        Document siteAns = carregaPagina()
        Element botao = siteAns.select("a:contains(Espaço do Prestador de Serviços de Saúde)").first()
        String url = botao.attr("href")
        Response response = Jsoup.connect(url).execute()
        Document espacoPrestador = response.parse()

        return espacoPrestador
    }

    Document acessaPaginaTiss(){
        Document espacoPrestador = acessaEspacoPrestadores()
        Element botao = espacoPrestador.select("a:contains(TISS)").first()
        String url = botao.attr("href")
        Response response = Jsoup.connect(url).execute()
        Document paginaTiss = response.parse()

        return paginaTiss
    }
}
