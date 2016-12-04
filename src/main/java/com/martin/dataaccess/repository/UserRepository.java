package com.martin.dataaccess.repository;

import com.martin.dataaccess.model.DBUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<DBUser>
{
    @Query("select * from user where name = ?0")
    DBUser findByName(String name);
}
