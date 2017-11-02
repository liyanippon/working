package com.example.admin.coinmachine.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by admin on 2017/9/19.
 */
@DatabaseTable(tableName = "tb_user") //表名tb_user
public class User {
    @DatabaseField(generatedId = true)  //id自动增长
    private int id;
    @DatabaseField(columnName = "name") //name列
    private String name;
    @DatabaseField(columnName = "desc") //desc列
    private String desc;

    public User()
    {
    }

    public User(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
}