package com.github.matveyakulov.javaschool.homework4.adjacencymatrix;

import com.github.matveyakulov.javaschool.homework4.vertex.Vertex;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

/**
 * Класс для тестов матрицы смежности.
 */
public class AdjacencyMatrixTest extends TestCase {

    /**
     * Создание матрицы смежности.
     */
    private AdjacencyMatrix<Vertex> adjMatr;

    /**
     * Вершины.
     */
    private Vertex vertex1;
    private Vertex vertex2;

    /**
     * Заполнение матрицы вершинами.
     */
    public void setUp(){
        adjMatr = new AdjacencyMatrix<>();
        vertex1 = new Vertex(1, "qwe", "dss");
        adjMatr.addVertex(vertex1);
        vertex2 = new Vertex(2, "qwe1", "dss");
        adjMatr.addVertex(vertex2);
    }

    /**
     * Тест добавления новых вершин.
     */
    public void testAddVertex(){
        Vertex vertex3 = new Vertex(3, "asd", "dss");
        adjMatr.addVertex(vertex3);
        Assertions.assertEquals(3, adjMatr.size());
    }

    /**
     * Тест добавления новых ребер.
     */
    public void testAddEdge(){
        adjMatr.addEdge(vertex1, vertex2);
        Assertions.assertEquals(true, adjMatr.checkEdge(vertex1, vertex2));
    }

    /**
     * Тест удаления вершин.
     */
    public void testRemoveVertex(){
        adjMatr.removeVertex(vertex1);
        Assertions.assertEquals(false, adjMatr.checkVertex(vertex1));
    }

    /**
     * Тест удаления ребер.
     */
    public void testRemoveEdge(){
        adjMatr.removeEdge(vertex1, vertex2);
        Assertions.assertEquals(false, adjMatr.checkEdge(vertex1, vertex2));
    }


}
