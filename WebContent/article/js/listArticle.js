
//標籤頁切換
$(function () {

  var $li = $('ul.tabs-pages li');

  $($li.eq(0).addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();

  $li.click(function () {

    $($(this).find('a').attr('href')).show().siblings('.tab-inner').hide();

    $(this).addClass('active').siblings('.active').removeClass('active');

  });

});


