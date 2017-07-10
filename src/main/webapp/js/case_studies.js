jQuery(document).ready(function($) {
	//filter logic
	$("ul#filter > li > a").on("click", function(e){
		e.preventDefault();
		var openGroup = ['Requested', 'Inprogress', 'Approved'];
		var closeGroup = ['Published', 'Rejected', 'Withdrawn'];
		$(this).parent().addClass("active").siblings().removeClass("active");
		if($(this).html() === "All"){			
			$("#casestudy tbody tr").show();
		}else if($(this).html() === "Open"){
			$.each(openGroup, function( index, value ) {
				$("." + value).show();
			});
			$.each(closeGroup, function( index, value ) {
				$("." + value).hide();
			});
		}else if($(this).html() === "Closed"){
			$.each(openGroup, function( index, value ) {
				$("." + value).hide();
			});
			$.each(closeGroup, function( index, value ) {
				$("." + value).show();
			});
		}else if($(this).html() === "In progress"){
			$(".Inprogress").each(function(index){
				$(this).show();
			});
			$("#casestudy tbody tr:not('.Inprogress')").each(function(index){
				$(this).hide();
			});
		}else if($("." + $(this).html()).length === 0){
		    $("#casestudy tbody tr").hide();	
		}else{
			$("." + $(this).html()).each(function(index){
				$(this).show();
			});
			$("#casestudy tbody tr:not(." + $(this).html() + ")").each(function(index){
				$(this).hide();
			});
		}		
		$("#recordNum").html("(" + $("#casestudy tbody tr").filter(function(){
		    return this.style.display == 'table-row' || !this.style.display;
		}).length + ")");
	});
	
	$("#casestudy").tablesorter({sortList: [[0,1]]}); 
});