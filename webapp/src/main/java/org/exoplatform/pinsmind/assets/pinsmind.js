(function ($) {
//Ajax for managing like function
    $(document).on('click', '.btn-pin', function () {
        $(this).jzAjax('PinsMindController.pin()', {
            data: {'id': 'codefest15'},
            success: function (data) {
                alert("PIN as HOT idea!")
            }
        });
        return false;
    });
})($);
