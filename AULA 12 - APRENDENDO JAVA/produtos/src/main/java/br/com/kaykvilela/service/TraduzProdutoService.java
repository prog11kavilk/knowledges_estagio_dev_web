package br.com.kaykvilela.service;

import br.com.kaykvilela.model.Produto;
import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

public class TraduzProdutoService {

    public void traduzir(Produto produto) {
        String novoNome = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, produto.getName());
        String novaDescricao = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, produto.getDescription());
        String novaCategoria = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, produto.getCategory());

        produto.setName(novoNome);
        produto.setCategory(novaCategoria);
        produto.setDescription(novaDescricao);

    }
}
