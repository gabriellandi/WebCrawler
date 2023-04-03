package org.example.WebCrawlerScraping

import org.example.Model.Arquivos
import org.example.Model.HistoricoVersoes
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import java.time.Year

class ScrapingHistoricoVersoes {
    AcessaSite acess = new AcessaSite()

    Document historicoVersoes(){
        Document paginaTiss = acess.acessaPaginaTiss()
        Element botao = paginaTiss.select("a:contains(Clique aqui para acessar todas as versões dos Componentes)").first()
        String url = botao.attr("href")
        Connection.Response response = Jsoup.connect(url).execute()
        Document historicoVersoes = response.parse()

        return historicoVersoes
    }

    List<Element> tabelaHistoricoTiss(){
        Document historicoVersoes = historicoVersoes()
        Elements conteudoTabela = historicoVersoes.getElementsByTag("tbody").first().getElementsByTag("tr")
        return conteudoTabela.toList()
    }

    List instanciaListaHistoricoVersoes(){
        List<Element> tabela = tabelaHistoricoTiss()
        ArrayList<HistoricoVersoes> listaVersoes = new ArrayList<HistoricoVersoes>()

        tabela.forEach {tr ->
            List<String> listaComunicados = []
            String competencia = tr.getElementsByTag("td").get(0).text()
            String publicacao = tr.getElementsByTag("td").get(1).text()
            String inicioVigencia = tr.getElementsByTag("td").get(2).text()
            String limiteImplantacao = tr.getElementsByTag("td").get(3).text()
            String organizacional = tr.getElementsByTag("td").get(4).text()
            String conteudoEstrutura = tr.getElementsByTag("td").get(5).text()
            String representacaoConceitos = tr.getElementsByTag("td").get(6).text()
            String segurancaPrivacidade = tr.getElementsByTag("td").get(7).text()
            String comunicacao = tr.getElementsByTag("td").get(8).text()
            listaComunicados.addAll(comunicacao.split(' '))


            int limiteScraping = competencia.split("/")[1].toInteger()
            if(limiteScraping<2016){
                return
            }

            HistoricoVersoes announcement = new HistoricoVersoes(competencia, publicacao, inicioVigencia, limiteImplantacao, organizacional, conteudoEstrutura, representacaoConceitos, segurancaPrivacidade, listaComunicados)
            listaVersoes.add(announcement)
        }

        return listaVersoes
    }
}
