let Message = function() {

    const toast = Swal.mixin({
        toast: true,
        position: 'top',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true
    })

    /**
     * 显示成功信息
     */
    let handleShowSuccess = function(title) {
        toast.fire({
            type: 'success',
            title: title
        })
    }

    /**
     * 显示失败信息
     */
    let handleShowFail = function(title) {
        toast.fire({
            type: 'error',
            title: title
        })
    }

    return {
        showSuccess: function(title) {
            handleShowSuccess(title);
        },

        showFail: function(title) {
            handleShowFail(title);
        }
    }
}();


