(function ($) {
	$(document).ready(function () {
		$('#btn-submit').click(function () {
			$('#addNewIdeaForm').submit();
		});

		$('#newIdeaButton').click(function () {
			var modal = $('#newIdeaModal');
			modal.show();
			modal.css("top", $(window).height()/2 -150);
			modal.css("left", $(window).width()/2 + 150);

		});

		$('#closeNewIdeaButton').click(function () {
			$('#newIdeaModal').hide();
		});
		
		$("#information").click(
			function () {
			var modal = $('#introductionModal');
			modal.show();
			modal.css("top", $(window).height()/2-300);
			modal.css("left", $(window).width()/2);
		});
		
		$('#closeInfo').click(function () {
			$('#introductionModal').hide();
		});
		
	});

})($);