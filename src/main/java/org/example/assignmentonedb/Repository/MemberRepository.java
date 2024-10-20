package org.example.assignmentonedb.Repository;

import org.example.assignmentonedb.Data.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByEmailAndPassword(String email, String password);
    Member findByEmail(String email);
}

