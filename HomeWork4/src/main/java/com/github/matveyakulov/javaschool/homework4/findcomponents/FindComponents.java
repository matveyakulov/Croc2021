package com.github.matveyakulov.javaschool.homework4.findcomponents;

import java.util.List;

/**
 * Интерфейс для поиска компонент связности.
 */
public interface FindComponents{

    /**
     * Обход графа в глубину.
     * @param name имя вершины, с которой начинается обход.
     */
    void dfs(Integer name);

    /**
     * Поиск компонент связности в графе.
     * @return
     */
    List<String> findComponents();

    /**
     * Возвращает количество компонент связности в графе.
     * @return количество компонент связности в графе.
     */
    int getCountComponents();
}
