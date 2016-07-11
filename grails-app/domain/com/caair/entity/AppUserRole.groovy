package com.caair.entity

/**
 * <p>
 * <strong>Module:</strong> CAAir </br>
 * <strong>Purpose:</strong> One or more entity/features perform under AppUser.
 * </p>
 *
 * <p><strong>Local Reference:</strong> Has-a relationship with other domains:</p>
 * <ul>
 *     <li>{@link com.caair.entity.AppUser#id as userId}</li>
 *     <li>{@link com.caair.entity.Role#id as roleId}</li>
 * </ul>
 */
class AppUserRole implements Serializable {

    long userId         // AppUser.id
    long roleId         // Role.id

    static mapping = {
        id composite: ['userId', 'roleId']
        version false
        userId index: 'app_user_role_user_idx'
        roleId index: 'app_user_role_role_idx'
    }
}
