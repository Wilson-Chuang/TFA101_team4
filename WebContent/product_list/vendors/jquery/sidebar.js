var sidebar = (function() {
    "use strict";

    var $contnet         = $('#content'),
        $sidebar         = $('#sidebar'),
        $sidebarBtn      = $('#sidebar-btn'),
        $toggleCol       = $('body').add($contnet).add($sidebarBtn),
        sidebarIsVisible = false;

    $sidebarBtn.on('click', function() {

        if (!sidebarIsVisible) {
            bindContent();
        } else {
            unbindContent();
        }

        toggleMenu();
    });


    function bindContent() {

        $contnet.on('click', function() {
            toggleMenu();
            unbindContent();
        });
    }

    function unbindContent() {
        $contnet.off();
    }

    function toggleMenu() {

        $toggleCol.toggleClass('sidebar-show');
        $sidebar.toggleClass('show');

        if (!sidebarIsVisible) {
            sidebarIsVisible = true;
        } else {
            sidebarIsVisible = false;
        }
    }


})();