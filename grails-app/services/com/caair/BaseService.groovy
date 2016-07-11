package com.caair

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired

import javax.sql.DataSource

/**
 * Base Service class
 * Every class extends com.caair.BaseService
 * Contains some common texts and methods so that all class can use them
 */
class BaseService {

    static transactional = false

    // used in action services
    public static final String ERROR_FOR_INVALID_INPUT = "Error occurred for invalid inputs"
    public static
    final String ENTITY_NOT_FOUND_ERROR_MESSAGE = "No entity found with this id or might have been deleted by someone!";
    public static final String IS_ERROR = 'isError'
    public static final String MESSAGE = 'message'

    // --- end ---

    // used in Domain services
    public final static String ID = "id";
    public final static String ASCENDING_SORT_ORDER = "asc";

    @Autowired
    DataSource dataSource;

    // methods for executing insert query
    public List executeInsertSql(String query) {
        Sql sql = new Sql(dataSource)
        return sql.executeInsert(query)
    }

    // methods for executing insert query with params
    public List executeInsertSql(String query, Map params) {
        Sql sql = new Sql(dataSource)
        return sql.executeInsert(query, params)
    }

    // method for executing update query
    public int executeUpdateSql(String query) {
        Sql sql = new Sql(dataSource)
        return sql.executeUpdate(query)
    }

    // method for executing update query with params
    public int executeUpdateSql(String query, Map params) {
        Sql sql = new Sql(dataSource)
        return sql.executeUpdate(query, params)
    }

    // method for executing query
    public boolean executeSql(String query) {
        Sql sql = new Sql(dataSource)
        return sql.execute(query)
    }

    // method for executing query with params
    public boolean executeSql(String query, Map params) {
        Sql sql = new Sql(dataSource)
        return sql.execute(query, params)
    }

    // method for executing select query
    public List<GroovyRowResult> executeSelectSql(String query) {
        Sql sql = new Sql(dataSource)
        return sql.rows(query)
    }

    // method for executing select query with param
    public List<GroovyRowResult> executeSelectSql(String query, Map params) {
        Sql sql = new Sql(dataSource)
        return sql.rows(query, params)
    }

    /**
     * Sets result state to indicate error by adding a key "isError" with TRUE value
     *
     * @param resultMap Existing result map where the result state will be put
     * @param message Optional message to be set (e.g., to be shown in UI)
     * @return Same resultMap with a status set
     */
    protected Map setError(Map resultMap, String message) {
        resultMap.put(IS_ERROR, Boolean.TRUE)
        if (message != null) {
            resultMap.put(MESSAGE, message)
        }
        return resultMap
    }

    /**
     * Sets result state to indicate success by adding a key "isError" with FALSE value
     *
     * @param resultMap Existing result map where the result state will be put
     * @param message Optional message to be set (e.g., to be shown in UI)
     * @return Same resultMap with a status set
     */
    protected Map setSuccess(Map resultMap, String message) {
        resultMap.put(IS_ERROR, Boolean.FALSE)
        if (message != null) {
            resultMap.put(MESSAGE, message)
        }
        return resultMap
    }
}
