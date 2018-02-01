using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using IBatisNet.Common;

using IBatisNet.DataMapper;

using IBatisNet.DataMapper.Configuration;

using IBatisNet.Common.Utilities;

using IBatisNet.DataAccess;

using IBatisNet.DataAccess.Configuration;

using IBatisNet.DataAccess.Interfaces;

 using IBatisNet.Common.Logging;
namespace useribatis
{
    public class Insert
    {
        public void doCreate() {
            DomSqlMapBuilder builder = new DomSqlMapBuilder();
            ISqlMapper Map = builder.Configure("SqlMap.config");
           // SqlMapper sqlMapper = builder.Configure() as SqlMapper;
            Student stu=new Student();
            stu.username="李敏镐3";
            stu.password="1234567";
           
            Map.Insert("Student.create", stu);
        }
       
    }
}