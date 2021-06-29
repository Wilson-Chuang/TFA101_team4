$(document).ready(function () {
  $('#my-img').click(function () {
      $('#img-upload').click();
  });
});



var sidebar = new StickySidebar('#sidebar', { // 要固定的側邊欄
    containerSelector: '#main-content', // 側邊欄外面的區塊
    innerWrapperSelector: '.sidebar__inner',
    topSpacing: 0, // 距離頂部 20px，可理解成 padding-top:20px
    rightSpacing: 0,
    leftSpacing: 0,
    bottomSpacing: 0 // 距離底部 20px，可理解成 padding-bottom:20px
});

var sidebar = new StickySidebar('#sidebar2', { // 要固定的側邊欄
    containerSelector: '#main-content2', // 側邊欄外面的區塊
    innerWrapperSelector: '.sidebar__inner2',
    topSpacing: 0, // 距離頂部 20px，可理解成 padding-top:20px
    rightSpacing: 0,
    leftSpacing: 0,
    bottomSpacing: 0 // 距離底部 20px，可理解成 padding-bottom:20px
});



$(function () {

    var $win = $(window);

    var $backToTop = $('.js-back-to-top');

    // 當用戶滾動到離頂部100像素時，展示回到頂部按鈕

    $win.scroll(function () {

        if ($win.scrollTop() > 100) {

            $backToTop.show();

        } else {

            $backToTop.hide();

        }

    });

    // 當用戶點擊按鈕時，通過動畫效果返回頭部

    $backToTop.click(function () {

        $('html, body').animate({ scrollTop: 0 }, 200);

    });

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
