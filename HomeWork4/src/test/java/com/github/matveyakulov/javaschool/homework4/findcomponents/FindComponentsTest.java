package com.github.matveyakulov.javaschool.homework4.findcomponents;

import com.github.matveyakulov.javaschool.homework4.adjacencymatrix.AdjacencyMatrix;
import com.github.matveyakulov.javaschool.homework4.components.Components;
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

    public void setUp(){
        adjMatr = new AdjacencyMatrix<>();
        vertex1 = new Vertex(1, "qwe", "dss");
        vertex2 = new Vertex(2, "qw", "dss");
        vertex3 = new Vertex(3, "qe", "dss");
        vertex4 = new Vertex(4, "we", "dss");
        adjMatr.addVertex(vertex1);
        adjMatr.addVertex(vertex2);
        adjMatr.addVertex(vertex3);
        adjMatr.addVertex(vertex4);
        adjMatr.addEdge(vertex1, vertex2);
        adjMatr.addEdge(vertex2, vertex3);
        adjMatr.addEdge(vertex3, vertex1);

    }

    public void testCountComponents(){
        adjMatr.findComponents();
        Assertions.assertEquals(2, adjMatr.getCountComponents());
    }

    public void testFindComponents(){
        Components components = new Components(adjMatr.findComponents());
        Assertions.assertEquals("[[4][123]]", components.toString());
    }
}
