let FormValidate = function() {

    let form = $('#form');

    /**
     * 处理验证
     */
    let handleValidate = function(formId, rules, messages) {
        if (formId != '' || formId != null) {
            form = $('#' + formId);
        }

        form.validate({
            rules: rules,
            messages: messages,
            errorElement: 'span',
            errorPlacement: function(error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').children('label').append(error);
            },
            highlight: function(element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });
    }

    return {
        validate: function(formId, rules, messages) {
            handleValidate(formId, rules, messages);
        }
    }
}();


