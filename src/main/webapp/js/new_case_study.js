jQuery(document).ready(function($) {
	//auto complete for the project codes
    var projectCodes = $("#projectCodes").val();
    var projectCodeArray = projectCodes.split(",");
    var newArr = [];
    newArr.push(projectCodeArray[0].substring(1, projectCodeArray[0].length));
    for(var i = 1; i < projectCodeArray.length - 1; i++){    	
    	newArr.push(projectCodeArray[i].trim());
    }
    newArr.push(projectCodeArray[projectCodeArray.length - 1].substring(0, projectCodeArray[projectCodeArray.length - 1].length - 1).trim());
    $( "#project" ).autocomplete({
        source: newArr,
        //get the researchers based on the selected project
        select: function (e, obj) {
            var projectCode = obj.item.value;
            $.ajax({
			    url: 'get_researchers_byproject',
			    type: 'get',
			    data: 'projectCode=' + projectCode,
			    dataType: 'json'
			}).done(function(response){
				var str = "<option value='0'>--unassigned--</option>";
				var str_researchers = "";
		        $.each(response, function(index, researcher){
		            str += '<option value=' + researcher.id + ' selected>' + researcher.fullName + '</option>';
		        	str_researchers += '<option value=' + researcher.id + ' selected>' + researcher.fullName + '</option>';
		        });
		        $('#researchers').html('').html(str_researchers);
		        $('#mainResearcher').html('').html(str);
		        $('#contactPerson').html('').html(str);
		        $('#mainResearcher').val("0");
		        $('#contactPerson').val("0");
			});
        }
    });
    
    //main researcher and contact person dropdown init
    /*$("#researchers").on("change", function(o){
    	var str = "";
        $("#researchers option:selected").each(function(){
            str += '<option value=' + $(this).val() + '>' + $(this).text() + '</option>';
        });
        $('#mainResearcher').html('').html(str);
        $('#contactPerson').html('').html(str);
    });*/
    
    $('#requested').datepicker({dateFormat: "yy-mm-dd"});
    $('#rejected').datepicker({dateFormat: "yy-mm-dd"});
    $('#inprogress').datepicker({dateFormat: "yy-mm-dd"});
    $('#withdrawn').datepicker({dateFormat: "yy-mm-dd"});
    $('#approved').datepicker({dateFormat: "yy-mm-dd"});
    $('#published').datepicker({dateFormat: "yy-mm-dd"});
    
	$('form').on('submit', function(e) {
	    e.preventDefault();
	    var validationFields = [$("#name"), $("#project"), $("#requestor"), $("#contactPerson")];
	    var validateResult = $.validate.doMultiValidation(validationFields, true, $.validator.requiredChecker); 
	    var isValid = validateResult.every(function(val){
	    	return val;
	    });
	    var validateResultForCharlength = $.validate.doValidation($("#name"), $.validator.charChecker);
	    if(isValid && validateResultForCharlength){
	    	this.submit();
	    }
	});
});