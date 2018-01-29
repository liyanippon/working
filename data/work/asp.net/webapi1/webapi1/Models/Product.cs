using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webapi1.Models
{
    public class Product
    {
        public int Id { get; set; }
        public String Name { get; set; }
        public String Category { get; set; }
        public decimal Price { get; set; }
    }
}