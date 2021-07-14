<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="http://daudecinc.tk/images/dau.ico">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="http://daudecinc.tk/css/dd5.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.js"></script>
	<script type="text/javascript" src="http://daudecinc.tk/js/validate-form.js"></script>
	<title>Dau de cinc</title>
	
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css" />
	<script>
	    jQuery(function($) {
	        $("#startDate").datetimepicker({
	        	dateFormat: 'dd/mm/yy',
	        	timeFormat:'HH:mm'
	        });
	        $("#endDate").datetimepicker({
	        	dateFormat: 'dd/mm/yy',
	        	timeFormat:'HH:mm'
	        });
	    });
	    
	    function fillEndDate(){
	    	var startDate = $("#startDate").val();
	    	var endDate = $("#endDate").val();
	    	
	    	if(endDate == "") $("#endDate").val(startDate);
	    }
	  </script>
</head>