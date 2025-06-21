package org.example.disproject.repository;

import org.example.disproject.entity.UsedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedEmailRepository extends JpaRepository<UsedEmail, Long> {

}
