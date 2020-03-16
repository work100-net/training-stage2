let CookieUtils = function() {
    const defaultSidebarCollapse = 'false';
    const expiresDays = 7;

    let handleInitSidebarCollapse = function() {
        if ($.cookie('sidebar_collapse') == undefined) {
            $.cookie('sidebar_collapse', defaultSidebarCollapse, { expires: expiresDays });
        }
    }

    let handleChangeSidebarCollapse = function() {
        let sidebar_collapse = $.cookie('sidebar_collapse');
        if (sidebar_collapse == undefined) {
            $.cookie('sidebar_collapse', defaultSidebarCollapse == 'true' ? 'false' : 'true', { expires: expiresDays });
        } else {
            $.cookie('sidebar_collapse', sidebar_collapse == 'true' ? 'false' : 'true', { expires: expiresDays });
        }
    }

    return {
        initSidebarCollapse: function() {
            handleInitSidebarCollapse();
        },
        changeSidebarCollapse: function() {
            handleChangeSidebarCollapse();
        }
    }
}();

$(function() {
    CookieUtils.initSidebarCollapse();
});

