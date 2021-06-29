
//輪播
$(function () {

  var gNum = 0;//組數的初始值
  var dX = 0;//水平座標位置
  var WW = $(".slider").width();//外層寬度
  //以下變數為總組數

  var AA = Math.floor($(".slider ul").width() / WW);

  $(".status li").eq(gNum).addClass("now");

  $(".next").click(function () {
    if (gNum < AA) {
      gNum++;
      dX = gNum * WW * -1;
    } else {
      gNum = 0;
      dX = 0;
    }
    SS();
  });

  $(".prev").click(function () {
    if (gNum > 0) {
      gNum--;
      dX = gNum * WW * -1;
    }
    SS();
  });

  function SS() {
    $(".slider ul").stop().animate({ left: dX }, 700);
    $(".status li").removeClass().eq(gNum).addClass("now");
  }

  $(".status li").click(function () {
    gNum = $(this).index();
    dX = gNum * WW * -1;
    SS();
  });


});


//幻燈片
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) { slideIndex = 1 }
  if (n < 1) { slideIndex = slides.length }
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex - 1].className += " active";
}
