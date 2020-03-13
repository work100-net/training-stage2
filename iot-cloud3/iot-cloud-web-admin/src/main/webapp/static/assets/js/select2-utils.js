let Select2 = function() {

    let handleInitSelect2 = function() {
        //Initialize Select2 Elements
        $('.select2').select2();

        //Initialize Select2 Elements
        $('.select2bs4').select2({
            theme: 'bootstrap4'
        });
    }

    return {
        init: function() {
            handleInitSelect2();
        }
    }
}();

$(function() {
    Select2.init();
});

