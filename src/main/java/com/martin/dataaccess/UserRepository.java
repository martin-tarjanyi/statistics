package com.martin.dataaccess;

import com.martin.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User>
{
    @Query("select * from user where name = ?0")
    User findByName(String name);
}
