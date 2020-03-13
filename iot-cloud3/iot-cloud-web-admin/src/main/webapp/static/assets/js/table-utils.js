let Table = function() {

    const checkAll = $('#checkAll');
    const checkItems = $('input[name="checkItem"]');

    /**
     * CheckBox 全选与取消全选
     */
    let handleInitCheckboxClick = function() {
        checkAll.click(function() {
            if ($(this).prop('checked')) {
                // 全选
                checkItems.prop('checked', true);
            } else {
                // 取消全选
                checkItems.prop('checked', false);
            }
        });
    }

    /**
     * 获取 CheckBox 选择值
     * @returns {any[]}
     */
    let handleGetCheckboxCheckedValues = function() {
        let arrCheckedValues = new Array();
        checkItems.each(function() {
            if ($(this).prop('checked')) {
                arrCheckedValues.push($(this).val())
            }
        })
        return arrCheckedValues;
    }

    return {
        init: function() {
            handleInitCheckboxClick();
        },

        getCheckboxCheckedValues: function() {
            return handleGetCheckboxCheckedValues();
        }
    }
}();

$(function() {
    Table.init();
});

