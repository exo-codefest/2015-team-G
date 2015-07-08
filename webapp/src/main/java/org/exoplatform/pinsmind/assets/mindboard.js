(function ($) {
	$(document).ready(function () {
		$('#btn-submit').click(function () {
			var name = $("#newIdea-modal #name").val();
			var desc = $("#newIdea-modal #desc").val();
		    $(this).jzAjax('PinsMindController.createNew()', {
		        data: {'name': name,"description": desc},
		        success: function (data) {
		            addNewIdeaUI(data);
		        }
		    });
		    return false;
		});
    });

	function createNew(name,callback) {
		$(document).jzAjax('PinsMindController.createNew()', {
		    data: {'id': name},
		    success: function (newIdea) {
		    	callback(newIdea);
		    }
		});
	}
	
})($);


