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
        source: newArr
    });
    
    $( "#date" ).datepicker();
    
	$('form').on('submit', function(e) {
	    e.preventDefault();
	    var validationFields = [$("#adviser"), $("#project"), $("#resourceInstance")];
	    var validateResult = $.validate.doMultiValidation(validationFields, true, $.validator.requiredChecker); 
	    var isValid = validateResult.every(function(val){
	    	return val;
	    });
	    if(isValid){
	    	this.submit();
	    }
	});
});