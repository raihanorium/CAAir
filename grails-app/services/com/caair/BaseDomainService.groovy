package com.caair

import javax.annotation.PostConstruct

/**
 * Base domain class extended by all domain service
 */
public abstract class BaseDomainService extends BaseService {

    /**
     * abstract method for create default schema which will be implemented in Domain service
     */
//    public abstract void createDefaultSchema();

    public Class domainClass

    @PostConstruct
    public abstract void init()

    /**
     * Read object by id in read-only mode
     * @param id - id of object
     * @return - object which has been read
     */
    public Object read(long id) {
        return domainClass.read(id)
    }

    /**
     * Create object in DB
     * @param -object to be saved
     * @return -saved object
     */
    public Object create(Object obj) {
        obj.save()
        return obj
    }

    /**
     * Updates object in DB
     * @param object to persist
     * @return -updated object
     */
    public Object update(Object obj) {
        obj.save()
        return obj
    }

    /**
     * Deletes object from database
     * @param object to delete
     */
    public void delete(Object obj) {
        obj.delete()
    }

    /**
     * get List of objects by domain
     * @param domainClass
     * @return - list of objects
     */
    public List<Object> list() {
        return domainClass.list(readOnly: true)
    }

    /**
     * create default data
     * @return - true if defaul data is successfully loaded, otherwise false
     */
    public abstract boolean createDefaultData()
}
