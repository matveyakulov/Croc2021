package com.github.matveyakulov.javaschool.project.database.provider;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

/**
 * Тесты класса DataSourceProvider.
 */
public class DataSourceProviderTest extends TestCase {

    /**
     * Data source.
     */
    private DataSourceProvider dataSourceProvider;

    /**
     * Начальная сборка.
     *
     * @throws IOException
     */
    public void setUp() throws IOException {
        dataSourceProvider = new DataSourceProvider("app.properties");
    }

    /**
     * Тест метода getSource.
     */
    public void testGetSource() {

        Assertions.assertEquals("db_weather", dataSourceProvider.getDataSource().getDatabaseName());
        Assertions.assertEquals("fallsd", dataSourceProvider.getDataSource().getUser());
        Assertions.assertEquals("poison13", dataSourceProvider.getDataSource().getPassword());

    }
}
