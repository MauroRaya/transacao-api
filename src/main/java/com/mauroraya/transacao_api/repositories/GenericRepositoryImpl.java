package com.mauroraya.transacao_api.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mauroraya.transacao_api.interfaces.GenericRepository;

@Repository
public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    private final ArrayList<T> lista;

    public GenericRepositoryImpl() {
        lista = new ArrayList<T>();
    }

    public List<T> obter() {
        return lista;
    }

    public void criar(T item) {
        lista.add(item);
    }

    public void deletarTodos() {
        lista.clear();
    }
}
