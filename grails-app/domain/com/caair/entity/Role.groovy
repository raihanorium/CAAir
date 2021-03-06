package com.caair.entity

/**
 * <p>
 * <strong>Module:</strong> CAAir </br>
 * <strong>Purpose:</strong> One or more entity/features perform under Role.
 *
 * <p><strong>Foreign Reference:</strong> Other domain, which has foreign key reference of Role:</p>
 * <ul>
 *     <li>{@link com.caair.entity.AppUserRole#roleId}</li>
 * </ul>
 */
class Role{

    long id                     // primary key (Auto generated by its own sequence)
    long version                // entity version in the persistence layer
    String name                 // Unique name
    String authority            // Role authority (ROLE_Role.id_Company.id) e.g ROLE_-1_1, ROLE_5_101

    static mapping = {
        cache true
        id generator: 'sequence', params: [sequence: 'role_id_seq']
    }

    static constraints = {
        authority blank: false, unique: true
    }
}
