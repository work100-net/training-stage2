let ModalDialog = function() {

    /**
     * 弹出确认对话框
     * @param modalId
     * @param title
     * @param message
     * @param callback
     * @param callback_params
     */
    let handleShowConfirm = function(modalId, title, message, callback, callback_params) {
        let modal = $('<div id="' + modalId + '"></div>');

        let modalDialog = $('<div></div>');
        let modalContent = $('<div></div>');
        let modalHeader = $('<div></div>');
        let modalBody = $('<div></div>');
        let modalFooter = $('<div></div>');

        let html;

        // modal-header html
        modalHeader.attr('class', 'modal-header');
        html = '';
        html = html + '<h4 class="modal-title">' + title + '</h4>';
        html = html + '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
        html = html + '  <span aria-hidden="true">&times;</span>';
        html = html + '</button>';
        modalHeader.html(html)

        // modal-body html
        modalBody.attr('class', 'modal-body');
        html = '';
        html = html + '<p>' + message + '</p>';
        modalBody.html(html);

        // modal-footer html
        let btnCancel = $('<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>');
        let btnOk = $('<button type="button" class="btn btn-primary">确定</button>');
        btnOk.on('click', function() {
            modal.modal('hide');
            callback(callback_params);
        });

        modalFooter.attr('class', 'modal-footer');
        modalFooter.append(btnCancel);
        modalFooter.append(btnOk);

        // modal-content
        modalContent.attr('class', 'modal-content');
        modalContent.append(modalHeader);
        modalContent.append(modalBody);
        modalContent.append(modalFooter);

        // modal-dialog
        modalDialog.attr('class', 'modal-dialog');
        modalDialog.append(modalContent);

        modal.attr('class', 'modal fade');
        modal.append(modalDialog);
        modal.modal('show');

        $(modal).on('hidden.bs.modal', function(e) {
            modal.remove();
        })

        $("body").append(modal);
    }

    /**
     * 弹出提示框
     * @param modalId
     * @param title
     * @param message
     */
    let handleShowAlert = function(modalId, title, message) {
        let modal = $('<div id="' + modalId + '"></div>');

        let modalDialog = $('<div></div>');
        let modalContent = $('<div></div>');
        let modalHeader = $('<div></div>');
        let modalBody = $('<div></div>');
        let modalFooter = $('<div></div>');

        let html;

        // modal-header html
        modalHeader.attr('class', 'modal-header');
        html = '';
        html = html + '<h4 class="modal-title">' + title + '</h4>';
        html = html + '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
        html = html + '  <span aria-hidden="true">&times;</span>';
        html = html + '</button>';
        modalHeader.html(html)

        // modal-body html
        modalBody.attr('class', 'modal-body');
        html = '';
        html = html + '<div>' + message + '</div>';
        modalBody.html(html);

        // modal-footer html
        let btnCancel = $('<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>');

        modalFooter.attr('class', 'modal-footer');
        modalFooter.append(btnCancel);

        // modal-content
        modalContent.attr('class', 'modal-content');
        modalContent.append(modalHeader);
        modalContent.append(modalBody);
        modalContent.append(modalFooter);

        // modal-dialog
        modalDialog.attr('class', 'modal-dialog');
        modalDialog.append(modalContent);

        modal.attr('class', 'modal fade');
        modal.append(modalDialog);
        modal.modal('show');

        $(modal).on('hidden.bs.modal', function(e) {
            modal.remove();
        })

        $("body").append(modal);
    }

    return {
        showConfirm: function(modalId, title, message, callback, callback_params) {
            handleShowConfirm(modalId, title, message, callback, callback_params);
        },
        showAlert: function(modalId, title, message) {
            handleShowAlert(modalId, title, message);
        }
    }
}();


