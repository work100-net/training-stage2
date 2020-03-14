let Table = function() {

    const checkAll = $('#checkAll');

    /**
     * CheckBox 全选与取消全选
     */
    let handleInitCheckboxClick = function() {
        const checkItems = $('input[name="checkItem"]');
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
        const checkItems = $('input[name="checkItem"]');
        let arrCheckedValues = new Array();
        checkItems.each(function() {
            if ($(this).prop('checked')) {
                arrCheckedValues.push($(this).val())
            }
        })
        return arrCheckedValues;
    }

    /**
     * 分页数据加载
     * @param url
     * @param columns
     */
    let handleLoadDataTable = function(url, columns, searchParams) {
        const dataTable = $('#dataTable');
        dataTable.DataTable({
            'paging': true,
            'lengthChange': false,
            'searching': false,
            'ordering': false,
            'info': true,
            'autoWidth': false,
            'serverSide': true,
            'deferRender': true,
            'destroy': true,
            'ajax': {
                'url': url,
                'type': 'POST',
                'data': function(d) {
                    //删除多余请求参数
                    for (const key in d) {
                        if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) {
                            //以columns开头的参数删除
                            delete d[key];
                        }
                    }

                    //附加查询参数
                    if (searchParams) {
                        //给d扩展参数
                        $.extend(d, searchParams);
                    }
                },
            },
            'columns': columns,
            'language': {
                'sProcessing': '处理中...',
                'sLengthMenu': '显示 _MENU_ 项结果',
                'sZeroRecords': '没有匹配结果',
                'sInfo': '显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项',
                'sInfoEmpty': '显示第 0 至 0 项结果，共 0 项',
                'sInfoFiltered': '(由 _MAX_ 项结果过滤)',
                'sInfoPostFix': '',
                'sSearch': '搜索:',
                'sUrl': '',
                'sEmptyTable': '表中数据为空',
                'sLoadingRecords': '载入中...',
                'sInfoThousands': ',',
                'oPaginate': {
                    'sFirst': '首页',
                    'sPrevious': '上页',
                    'sNext': '下页',
                    'sLast': '末页'
                },
                'oAria': {
                    'sSortAscending': ': 以升序排列此列',
                    'sSortDescending': ': 以降序排列此列'
                }
            },
            'drawCallback': function(settings) {
                handleInitCheckboxClick();
                checkAll.prop('checked', false);
            }
        });
    }


    return {
        initCheckboxClick: function() {
            handleInitCheckboxClick();
        },

        getCheckboxCheckedValues: function() {
            return handleGetCheckboxCheckedValues();
        },

        loadDataTable: function(url, columns, searchParams) {
            handleLoadDataTable(url, columns, searchParams);
        }
    }
}();

$(function() {
    Table.initCheckboxClick();
});

