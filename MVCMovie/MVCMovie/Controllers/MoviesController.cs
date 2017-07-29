using System;
using MVCMovie.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace MVCMovie.Controllers
{
    public class MoviesController : Controller
    {
        MovieDBContext db = new MovieDBContext();
        public ActionResult Index(string date, int? moneyFirst, int? moneyLast, string movieGenre, string searchString)
        {
            var GenreLst = new List<string>();
            var DateLst = new List<string>();
            DateLst.Add("未上映");
            ViewBag.date = new SelectList(DateLst);
            var GenreQry = from d in db.Movies orderby d.Genre select d.Genre;
            GenreLst.AddRange(GenreQry.Distinct());    //去重 
            ViewBag.movieGenre = new SelectList(GenreLst);
            var movies = from m in db.Movies select m;
            if (!String.IsNullOrEmpty(date))
            {
                movies = movies.Where(d => (DateTime.Compare(DateTime.Now, d.ReleaseDate)) < 0);
            }
            if (moneyFirst != null && moneyLast != null)
            {
                if (moneyLast < moneyFirst)
                {
                    int? temp;
                    temp = moneyFirst;
                    moneyFirst = moneyLast;
                    moneyLast = temp;
                }
                movies = movies.Where(t => (t.Price >= moneyFirst) && (t.Price <= moneyLast));
            }
            if (!String.IsNullOrEmpty(movieGenre))
            {
                movies = movies.Where(x => x.Genre == movieGenre);
            }
            if (!String.IsNullOrEmpty(searchString))
            {
                movies = movies.Where(s => s.Title.Contains(searchString));
            }
            return View(movies);
        }

        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [Authorize]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Movie movie)
        {
            if (ModelState.IsValid)
            {
                db.Movies.Add(movie);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(movie);
        }

        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Movie movie = db.Movies.Find(id);
            if (movie == null)
            {
                return HttpNotFound();
            }
            return View(movie);
        }

        [HttpPost]
        public ActionResult Delete(int id)
        {
            Movie movie = db.Movies.Find(id);
            db.Movies.Remove(movie);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Movie movie = db.Movies.Find(id);
            if (movie == null)
            {
                return HttpNotFound();
            }
            return View(movie);
        }

        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Movie movie = db.Movies.Find(id);
            if (movie == null)
            {
                return HttpNotFound();
            }
            return View(movie);
        }

        [HttpPost]
        public ActionResult Edit(Movie movie)
        {
            if (ModelState.IsValid)
            {
                db.Entry(movie).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(movie);
        }
    }
}