package com.github.matveyakulov.javaschool.homework4.findcomponents;

import java.util.List;

/**
 * Интерфейс для поиска компонент связности.
 */
public interface FindComponents{

    /**
     * Обход графа в глубину.
     * @param num номер вершины, с которой начинается обход.
     */
    void dfs(Integer num);

    /**
     * Поиск компонент связности в графе.
     */
    void findComponents();

    /**
     * Возвращает упорядоченный список компонент связности графа.
     * @return упорядоченный список компонент связности графа.
     */
    List<String> getComponents();

    /**
     * Возвращает количество компонент связности в графе.
     * @return количество компонент связности в графе.
     */
    int getCountComponents();
}
