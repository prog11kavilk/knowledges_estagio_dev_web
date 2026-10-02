package com.kaykvilela.screenmatch_appSpring.service;

//construção da interaface para padronizar em várias classes
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
