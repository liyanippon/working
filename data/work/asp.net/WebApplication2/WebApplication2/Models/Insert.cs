using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using IBatisNet.Common;
using IBatisNet.DataMapper;
using IBatisNet.DataMapper.Configuration;
using IBatisNet.Common.Utilities;
 using IBatisNet.Common.Logging;

namespace WebApplication2
{
    public class Insert
    {
        public void DoCreate()
        {
            DomSqlMapBuilder builder = new DomSqlMapBuilder();
            ISqlMapper Map = builder.Configure("SqlMap.config");
           // SqlMapper sqlMapper = builder.Configure() as SqlMapper;
            Student stu=new Student
            {
                Username = "鱿鱼",
                Password = "arsong"
            };
            
            
            Map.Insert("Student.create", stu);
            
        }
       
    }
}