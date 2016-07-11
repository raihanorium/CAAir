package com.caair.entity

/**
 * <p>
 * <strong>Module:</strong> CAAir </br>
 * <strong>Purpose:</strong> One or more entity/features perform under by a customer.
 * Customer has association with few other domains as listed below.
 * </p>
 *
 * <p><strong>Foreign Reference:</strong> Other domain, which has foreign key reference of Customer:</p>
 * <ul>
 *     <li>{@link com.caair.entity.PaymentInfo#customerId}</li>
 *     <li>{@link com.caair.entity.TicketInfo#customerId}</li>
 * </ul>
 */
class Customer {

    long id                 // primary key (Auto generated by its own sequence)
    long version            // entity version in the persistence layer
    String name             // name if the customer
    String email            // email address of customer
    String phone            // customer phone number

    static constraints = {
        name(blank: false,nullable: false)
        email(blank: false,nullable: false,unique: true,email: true)
        phone(blank: false,nullable: false)
    }

    static mapping = {
        id generator: 'sequence', params: [sequence: 'customer_id_seq']
    }

    @Override
    public String toString() {
        return "Customer[" +
                "Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phone + '\'' +
                ']';
    }
}