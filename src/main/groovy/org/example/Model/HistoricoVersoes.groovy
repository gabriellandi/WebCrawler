package org.example.Model

class HistoricoVersoes {
    String competencia
    String publicacao
    String inicioDeVigencia
    String limiteDeImplantacao
    String organizacional
    String conteudoEstrutura
    String representacaoConceitos
    String segurancaPrivacida
    List comunicacao

    HistoricoVersoes(String competencia, String publicacao, String inicioDeVigencia, String limiteDeImplantacao, String organizacional, String conteudoEstrutura, String representacaoConceitos, String segurancaPrivacida, List comunicacao) {
        this.competencia = competencia
        this.publicacao = publicacao
        this.inicioDeVigencia = inicioDeVigencia
        this.limiteDeImplantacao = limiteDeImplantacao
        this.organizacional = organizacional
        this.conteudoEstrutura = conteudoEstrutura
        this.representacaoConceitos = representacaoConceitos
        this.segurancaPrivacida = segurancaPrivacida
        this.comunicacao = comunicacao
    }


    @Override
    public String toString() {
        return  "competencia='" + competencia + '\'' +
                ", publicacao='" + publicacao + '\'' +
                ", inicioDeVigencia='" + inicioDeVigencia + '\'' +
                ", limiteDeImplantacao='" + limiteDeImplantacao + '\'' +
                ", organizacional='" + organizacional + '\'' +
                ", conteudoEstrutura='" + conteudoEstrutura + '\'' +
                ", representacaoConceitos='" + representacaoConceitos + '\'' +
                ", segurancaPrivacida='" + segurancaPrivacida + '\'' +
                ", comunicacao=" + comunicacao
    }
}
