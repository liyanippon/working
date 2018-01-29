using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Models
{
    public class SqlInit
    {
        DataMaintain dataMaintain = new DataMaintain();
        public SqlInit()
        {
            //Linq to sql 增删改查
            /*dataMaintain.students.Add(new Student {  Id="123" ,Name = "小明", Age = 12 });
            dataMaintain.students.Add(new Student {  Id="124" ,Name = "小黄", Age = 15 });
            dataMaintain.students.Add(new Student {  Id="133" ,Name = "小清", Age = 23 });           
            dataMaintain.SaveChanges();*/

            //dataMaintain.students.SqlQuery("");


        }

        public Boolean addStudent(Student student)
        {
            dataMaintain.students.Add(student);
            dataMaintain.SaveChanges();
            return true;
        }

        public Boolean searchStudent() //查
        {
            var q =

                from c in dataMaintain.students

                where c.Name == "小明"

                select c;
            //String qo = q;
            foreach (var customer in q)
            {
                System.Diagnostics.Debug.Write(customer.Id);
                System.Diagnostics.Debug.Write(customer.Name );
                System.Diagnostics.Debug.Write(customer.Age);
            }
            return true;
        }
        public List<Student> searchStudentAll() //查
        {
            var q =

                from c in dataMaintain.students

                select c;
            //String qo = q;
            List<Student> list = new List<Student> ();
            foreach (var customer in q)
            {
                Student s = new Student();
                s.Id = customer.Id;
                s.Name = customer.Name;
                s.Age = customer.Age;
                list.Add(s);
            }
            return list;
        }


        public Boolean deleteStudent(String Id) //删
        {
            var q =

                from c in dataMaintain.students

                where c.Id == Id

                select c;

            foreach (var customer in q)
            {
                dataMaintain.students.Remove(customer);
            }
            try
            {
                dataMaintain.SaveChanges();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                // Provide for exceptions.
            }
            return true;
        }

        public Boolean updateStudent() //改
        {
            var q =

                from c in dataMaintain.students

                where c.Name == "小黄"

                select c;

            foreach (var customer in q)
            {
                customer.Age = 18;
                customer.Name = "刘胜";                
            }

            try
            {
                dataMaintain.SaveChanges();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                // Provide for exceptions.
            }
            return true;
        }
    }
}