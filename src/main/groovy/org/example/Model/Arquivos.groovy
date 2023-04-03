package org.example.Model

class Arquivos {
    String name
    String versao
    String link

    Arquivos(String name, String versao, String link) {
        this.name = name
        this.versao = versao
        this.link = link
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", versao='" + versao + '\'' +
                ", link='" + link;
    }
}
