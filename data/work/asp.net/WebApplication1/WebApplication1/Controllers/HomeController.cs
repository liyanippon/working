using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";

            Console.Write("write");
            return View();
        }

        public ActionResult Test()
        {
            ViewBag.Name = "名字";
            ViewBag.Bag = "time";
            System.Diagnostics.Debug.Write("i love you");
            return View();
        }

        public ActionResult Login()
        {
            ViewBag.Name = "名字";
            string username = Request.QueryString["fname"];//get提交
            string password = Request.QueryString["pass"];
            ViewBag.username = username;
            ViewBag.password = password;
            return View("Test");
        }

        public JsonResult DeleteStu()
        {
            string id = Request.Form["Id"];//post提交
            SqlInit sqlInit = new SqlInit();
            //删除信息
            if (id != null)
            {
                sqlInit.deleteStudent(id);
            }
            //从数据库查询所有的信息
            List<Student> list = sqlInit.searchStudentAll();
            return Json(list, JsonRequestBehavior.DenyGet);
        }

        public JsonResult AjaxForm() //请求成功后网页跳转
        {
            ViewBag.Name = "名字";
            string name = Request.Form["name"];//post提交
            string age = Request.Form["age"];//post提交
            //string id = Request.QueryString["Id"];//get提交
            DateTime dt = DateTime.Now;
            SqlInit sqlInit = new SqlInit();
            if (name!=null)
            {
                Student s = new Student();
                s.Id = dt.ToString("yyyyMMddHHmmss");
                s.Name = name;
                s.Age = Convert.ToInt32(age);

                //数据库操作
                
                sqlInit.addStudent(s);
            }

            //从数据库查询所有的信息
            List<Student> list = sqlInit.searchStudentAll();
            
            //Dictionary<string, object> dictionary = new Dictionary<string, object>();
            //dictionary.Add("student", list);
            return Json(list, JsonRequestBehavior.DenyGet);
        }

        public ActionResult FormTest()
        {
            //post提交
            string car = Request.Form["car"];
            string bike = Request.Form["bike"];
            string week = Request.Form["week"];
            List<Week> list = new List<Week>();
            Week weeks = new Week();
            weeks.Number = "mon";
            weeks.Name = "星期一";
            list.Add(weeks);
            weeks = new Week();
            weeks.Number = "tue";
            weeks.Name = "星期二";
            list.Add(weeks);
            weeks = new Week();
            weeks.Number = "wed";
            weeks.Name = "星期三";
            list.Add(weeks);
            weeks = new Week();
            weeks.Number = "thu";
            weeks.Name = "星期四";
            list.Add(weeks);
            weeks = new Week();
            weeks.Number = "fri";
            weeks.Name = "星期五";
            list.Add(weeks);
            weeks = new Week();
            weeks.Number = "sat";
            weeks.Name = "星期六";
            list.Add(weeks);
            weeks = new Week();
            weeks.Number = "sun";
            weeks.Name = "星期日";
            list.Add(weeks);
            ViewBag.dic = list;
            
            //数据库操作
            SqlInit sqlInit = new SqlInit();
            sqlInit.searchStudent();
            return View("FormTest");
        }

    }

    
}
