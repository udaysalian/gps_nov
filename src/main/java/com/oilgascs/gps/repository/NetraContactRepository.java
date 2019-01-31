package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Contact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NetraContactRepository extends ContactRepository{

    @Query("select contacts from Contact contacts where contacts.loginId.login =?#{principal.username}")
    public Contact findByUserIsCurrentUser();
}
