using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using IBatisNet.Common;
using IBatisNet.DataMapper;
using IBatisNet.DataMapper.Configuration;
using IBatisNet.Common.Utilities;
using IBatisNet.Common.Logging;
using System.Collections;
namespace WebApplication2.Models
{
    public class StudentDao
    {
        public void DoSelect()
        {
            DomSqlMapBuilder builder = new DomSqlMapBuilder();
            ISqlMapper Map = builder.Configure("SqlMap.config");
            // SqlMapper sqlMapper = builder.Configure() as SqlMapper;
            Student stu = new Student
            {
                Id = 5,
                Username = "鱿鱼"
            };

            //IList<Student> o = (IList<Student>)Map.QueryForList("Student.login", stu);
            object s = Map.QueryForList("Student.login", stu);

            ArrayList list = (ArrayList)Map.QueryForList("Student.login", stu);
            foreach (var item in list)
            {
                Object[] ss =(Object[])item;
                foreach (var jk in ss)
                {
                    object nm=jk;
                }
            }
            
        }
    }
}