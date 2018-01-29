using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Models
{
    public class Student
    {
        [Key]
        public String Id { get; set; }
        public String Name { get; set; }
        public int Age { get; set; }
    }
}