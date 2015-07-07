(function ($) {
	$(document).ready(function () {
		//Ajax for managing like function
	    $('#btn-pin').click(function () {
	        $(this).jzAjax('PinsMindController.pin()', {
	            data: {'id': 'codefest15'},
	            success: function (data) {
	                alert("PIN as HOT idea!")
	            }
	        });
	        return false;
	    });
    });
})($);
