using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace MVCMovie.Models
{
    public class Movie
    {
        [Display(Name = "电影编号")]
        public int ID { get; set; }      //电影编号 

        [Display(Name = "电影名称")]
        [Required(ErrorMessage = "必填")]
        [StringLength(60, MinimumLength = 3, ErrorMessage = "必须是[3,60]个字符")]
        public string Title { get; set; }     //电影名称 

        [Display(Name = "上映日期")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy/MM/dd}")]
        public DateTime ReleaseDate { get; set; }    //上映时间 

        [Display(Name = "电影类型")]
        [Required]
        public string Genre { get; set; }      //电影类型 

        [Display(Name = "电影票价")]
        [Range(1, 100)]
        [DataType(DataType.Currency)]
        public decimal Price { get; set; }    //电影票价 

        [Display(Name = "电影分级")]
        [StringLength(5)]
        [Required]
        public string Rating { get; set; }     //电影分级
    }

}
