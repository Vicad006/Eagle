package org.church.repositories;

import org.church.entities.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipStatusRepository extends JpaRepository<MembershipStatus, Integer> {


}
