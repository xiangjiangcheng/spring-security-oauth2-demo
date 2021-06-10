package com.example.security.repository;

import com.example.security.domain.user.OAuthClientDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiang JiangCheng
 */
@Repository
public interface OAuthClientDetailsRepository extends CrudRepository<OAuthClientDetails, Long> {

    OAuthClientDetails findByClientId(String clientId);
}
