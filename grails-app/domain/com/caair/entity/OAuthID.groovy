package com.caair.entity

/**
 * <p>
 * <strong>Module:</strong> CAAir </br>
 * <strong>Purpose:</strong> One or more entity/features perform under OAuthID.
 *
 * <p><strong>Local Reference:</strong> Has-a relationship with other domains:</p>
 * <ul>
 *     <li>{@link com.caair.entity.AppUser#id as userId}</li>
 * </ul>
 */
class OAuthID {
    String provider
    String accessToken
    long userId

    static constraints = {
        provider(blank: false, nullable: false)
        accessToken(blank: false, nullable: false, unique: true)
        userId(nullable: false)
    }
}
