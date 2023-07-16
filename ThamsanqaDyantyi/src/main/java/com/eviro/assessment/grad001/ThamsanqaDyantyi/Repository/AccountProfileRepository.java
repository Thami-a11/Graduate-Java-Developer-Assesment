package com.eviro.assessment.grad001.ThamsanqaDyantyi.Repository;

import com.eviro.assessment.grad001.ThamsanqaDyantyi.Models.AccountProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProfileRepository extends CrudRepository<AccountProfile,Long> {
 AccountProfile findByNameAndSurname(String name, String surname);

}
