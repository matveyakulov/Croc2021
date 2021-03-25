package com.github.matveyakulov.javaschool.homework4.findcomponents;

import com.github.matveyakulov.javaschool.homework4.adjacencymatrix.AdjacencyMatrix;
import com.github.matveyakulov.javaschool.homework4.vertex.Vertex;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

/**
 * Класс для тестов класса FindComponents.
 */
public class FindComponentsTest extends TestCase {

    /**
     * Матрица смежности.
     */
    AdjacencyMatrix<Vertex> adjMatr;

    /**
     * Вершины.
     */
    private Vertex vertex1;
    private Vertex vertex2;
    private Vertex vertex3;
    private Vertex vertex4;
    private Vertex vertex5;

    /**
     * Сборка графа.
     */
    public void setUp(){
        adjMatr = new AdjacencyMatrix<>();
        vertex1 = new Vertex(1, "qwe", "dss");
        vertex2 = new Vertex(2, "qw", "dss");
        vertex3 = new Vertex(3, "qe", "dss");
        vertex4 = new Vertex(4, "we", "dss");
        vertex5 = new Vertex(5, "we", "dss");
        adjMatr.addVertex(vertex1);
        adjMatr.addVertex(vertex2);
        adjMatr.addVertex(vertex3);
        adjMatr.addVertex(vertex4);
        adjMatr.addVertex(vertex5);
        adjMatr.addEdge(vertex1, vertex2);
        adjMatr.addEdge(vertex2, vertex3);
        adjMatr.addEdge(vertex3, vertex1);
    }

    /**
     * Тест рассчета количества компонент графа.
     */
    public void testCountComponents(){
        Assertions.assertEquals(3, adjMatr.getCountComponents());
    }

    /**
     * Тест вывода списка компонент графа.
     */
    public void testFindComponents(){
        Assertions.assertEquals("[[4], [5], [123]]", adjMatr.getComponents().toString());
    }
}
