let DateUtils = function() {

    let handleFormatDate = function(date, fmt) {
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        const o = {
            'M+': date.getMonth() + 1,
            'd+': date.getDate(),
            'H+': date.getHours(),
            'm+': date.getMinutes(),
            's+': date.getSeconds()
        }
        for (const k in o) {
            if (new RegExp(`(${k})`).test(fmt)) {
                const str = o[k] + '';
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : handlePadLeftZero(str));
            }
        }
        if (fmt.indexOf('N') !== -1) {
            return '';
        }
        return fmt;
    }

    let handleNewDate = function(value) {
        if (value === undefined) {
            return new Date();
        }
        // 解决invalid date
        value = value.replace(new RegExp(/-/gm), '/');
        return new Date(value);
    }


    let handleAddMonth = function(date, months) {
        if (months === undefined || months === '') {
            months = 1;
        }
        const tempDate = new Date(date);
        tempDate.setMonth(tempDate.getMonth() + months);
        return formatDate(tempDate, 'yyyy-MM');
    }

    let handleAddYear = function(date, years) {
        if (years === undefined || years === '') {
            years = 1;
        }
        const tempDate = new Date(date);
        tempDate.setFullYear(tempDate.getFullYear() + years);
        return formatDate(tempDate, 'yyyy');
    }

    let handleAddDate = function(date, days) {
        if (days === undefined || days === '') {
            days = 1;
        }
        const tempDate = new Date(date);
        tempDate.setDate(tempDate.getDate() + days);
        return formatDate(tempDate, 'yyyy-MM-dd');
    }


    let handlePadLeftZero = function(str) {
        return ('00' + str).substr(str.length);
    }


    return {
        formatDate: function(date, fmt) {
            return handleFormatDate(date, fmt);
        },

        newDate: function(value) {
            return handleNewDate(value);
        },

        addMonth: function(date, months) {
            return handleAddMonth(date, months);
        },

        addYear: function(date, years) {
            return handleAddYear(date, years);
        },

        addDate: function(date, days) {
            return handleAddDate(date, days);
        }
    }
}();


