package com.martin.dataaccess.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@ToString
@EqualsAndHashCode
@Table("user")
public class DBUser
{
    @PrimaryKey
    private String name;

    @Column("password")
    private String password;

    public DBUser()
    {
    }

    public DBUser(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
