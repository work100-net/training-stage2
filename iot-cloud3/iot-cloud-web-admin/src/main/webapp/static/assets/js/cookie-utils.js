let CookieUtils = function() {
    const cookie_key_sidebar_collapse = 'sidebar_collapse';
    const defaultSidebarCollapse = 'false';
    const expiresDays = 7;

    let handleInitSidebarCollapse = function() {
        if ($.cookie(cookie_key_sidebar_collapse) == undefined) {
            $.cookie(cookie_key_sidebar_collapse, defaultSidebarCollapse, { expires: expiresDays });
        }
    }

    let handleChangeSidebarCollapse = function() {
        let sidebar_collapse = $.cookie(cookie_key_sidebar_collapse);
        if (sidebar_collapse == undefined) {
            $.cookie(cookie_key_sidebar_collapse, defaultSidebarCollapse == 'true' ? 'false' : 'true', { expires: expiresDays });
        } else {
            $.cookie(cookie_key_sidebar_collapse, sidebar_collapse == 'true' ? 'false' : 'true', { expires: expiresDays });
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

