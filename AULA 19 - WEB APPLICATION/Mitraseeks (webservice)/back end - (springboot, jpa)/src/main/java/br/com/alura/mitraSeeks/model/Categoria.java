package br.com.alura.mitraSeeks.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    FICCAO("Sci-Fi", "Ficção Científica"),
    AVENTURA("Adventure", "Aventura"),
    ANIMACAO("Animation", "Animação"),
    TERROR("Horror", "Terror"),
    DOCUMENTARIO("Documentary", "Documentário");

    private String categoriaOmdb;
    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    // O OMDb manda vários gêneros ("Biography, Drama, History"); usa o primeiro que tiver categoria, ou null
    public static Categoria primeiraConhecida(String generosOmdb) {
        if (generosOmdb == null) {
            return null;
        }
        for (String genero : generosOmdb.split(",")) {
            for (Categoria categoria : Categoria.values()) {
                if (categoria.categoriaOmdb.equalsIgnoreCase(genero.trim())) {
                    return categoria;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return categoriaPortugues;
    }
}
