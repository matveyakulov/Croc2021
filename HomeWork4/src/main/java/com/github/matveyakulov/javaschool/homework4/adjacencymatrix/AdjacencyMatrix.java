package com.github.matveyakulov.javaschool.homework4.adjacencymatrix;

import com.github.matveyakulov.javaschool.homework4.findcomponents.FindComponents;
import com.github.matveyakulov.javaschool.homework4.vertex.Vertex;

import java.util.*;

/**
 * Класс, описывающий граф в виде матрицы смежности.
 */
public class AdjacencyMatrix<T extends Vertex> implements FindComponents{

    /**
     * Матрица смежности для графа.
     */
    private Map<Integer, List<T>> adjMatr;

    /**
     * Массив пройденных вершин.
     */
    private Map<Integer, Boolean> visited;

    /**
     * Строка, содаржащая компоненты графа.
     */
    private StringBuilder component;

    /**
     * Количество компонент связности в графе.
     */
    private Integer countComponents;

    public AdjacencyMatrix() {
        this.adjMatr = new HashMap<>();
    }

    /**
     * Добавление новой вершины в граф.
     * @param vertex вершина, которую нужно добавить.
     */
    public void addVertex(T vertex){
        adjMatr.put(vertex.getNum(), new ArrayList<>());
        this.findComponents();
    }

    /**
     * Добавление ребра в граф.
     * @param from вершина, в которой начинается ребро.
     * @param to вершина, в которой заканчивается ребро.
     */
    public void addEdge(T from, T to){
        adjMatr.get(from.getNum()).add(to);
        adjMatr.get(to.getNum()).add(from);  // так как граф неориентированный
        this.findComponents();
    }

    /**
     * Удаление вершины из графа.
     * @param vertex имя удаляемой вершины ребра.
     */
    public void removeVertex(T vertex){

        if(adjMatr.get(vertex.getNum()) != null){      //удаляем все ребра, связанные с этой вершиной
            List<T> list = adjMatr.get(vertex.getNum());
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < adjMatr.size(); j++){
                    adjMatr.get(j).remove(list.get(i));
                }
            }
        }
        adjMatr.remove(vertex.getNum());
        this.findComponents();


    }

    /**
     * Удаление ребра из графа.
     * @param from вершина, в которой начинается ребро.
     * @param to вершина, в которой заканчивается ребро.
     */
    public void removeEdge(T from, T to){
        adjMatr.get(from.getNum()).remove(to);
        adjMatr.get(to.getNum()).remove(from);
        this.findComponents();
    }

    /**
     * Возвращает количество вершин в графе.
     * @return количество вершин в графе.
     */
    public Integer size(){
        return adjMatr.size();
    }

    /**
     * Проверяет наличие ребра в графе между вершинами.
     * @param from имя первой вершины.
     * @param to имя второй вершины.
     * @return true, если ребро есть и false - иначе.
     */
    public boolean checkEdge(T from, T to) {
        return adjMatr.get(from.getNum()).contains(to);
    }

    /**
     * Проверяет наличие вершины в графе.
     * @param vertex вершина.
     * @return true, если вершина есть и false - иначе.
     */
    public boolean checkVertex(T vertex) {
        return adjMatr.containsKey(vertex.getNum());
    }

    /**
     * Обход графа в глубину.
     * @param vertexKey идентификатор вершины, с которой начинается обход.
     */
    public void dfs(Integer vertexKey) {
        component.append(vertexKey);
        visited.put(vertexKey, true);
        List<T> list = adjMatr.get(vertexKey);
        for (Vertex vertexId : list) {
            if (!visited.get(vertexId.getNum()) && adjMatr.get(vertexKey).contains(vertexId)) {
                dfs(vertexId.getNum());
            }
        }
    }

    /**
     * Поиск компонент связности в графе.
     */
    public void findComponents() {
        component = new StringBuilder();
        countComponents = 0;
        visited = new HashMap<>();
        for(Integer key: adjMatr.keySet()) {
            visited.put(key, false);
        }
        for (Integer key : adjMatr.keySet()) {
            if (!visited.get(key)) {
                component.append("[");
                dfs(key);
                countComponents++;
                component.append("] ");
            }
        }
    }

    /**
     * Возвращает упорядоченный список компонент связности графа.
     * @return упорядоченный список компонент связности графа.
     */
    public List<String> getComponents(){
        List<String> componentList = Arrays.asList(component.toString().split(" "));
        componentList.sort(Comparator.comparing(String::length));
        return componentList;
    }
    /**
     * Возвращает количество компонент связности в графе.
     *
     * @return количество компонент связности в графе.
     */
    @Override
    public int getCountComponents() {
        return countComponents;
    }


}
