using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using System.Runtime.Remoting.Contexts;
namespace WebApplication1.Models
{
    public class DataMaintain: DbContext
    {
        public DataMaintain()
        {
            //Database.SetInitializer<DataMaintain>(new DropCreateDatabaseAlways<DataMaintain>());//清空数据库  

        }
        public DbSet<Student> students { set; get; }
    }
}