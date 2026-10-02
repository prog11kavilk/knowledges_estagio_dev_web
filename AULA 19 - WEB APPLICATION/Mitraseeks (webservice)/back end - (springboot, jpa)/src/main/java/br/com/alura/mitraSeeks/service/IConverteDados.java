package br.com.alura.mitraSeeks.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
