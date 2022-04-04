<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page buffer="8192kb" autoFlush="true" %>
<html xmlns:th="https://www.thymeleaf.org">
<head>
	<link href="/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="/css/font-awesome.css" rel="stylesheet">
	<link href="/css/side-slider.css" rel="stylesheet">
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.validate.min.js"></script>
	<script src="/js/notAllow.js"></script>
	<title>DHFL Maintenance</title>	
</head>
<style>
div#sideslider-smartbutton {
    font-size: 13px;
    font-family: inherit;
    padding: 0;
    background: white;
}
.sideslider-tab {
    border-radius: 3px;
}
div#successReceipt {
    background: white;
    border-top: 3px solid #ed1c24;
    box-shadow: 0 10px 10px rgba(0,0,0,.05);
}
form#form_overdue,
form#form_charges {
    height: 250px;
}
.error {
	color: #ed1c24;
    font-size: 12px;
    font-weight: 100;
}
label#lbl_disclaimer {
    /* color: red; */
    font-size: 12px;
    font-weight: 100;
}
table.trans_tbl>tbody > tr > td {
    width: 50%;
    padding-left: 5%;
}
table.trans_tbl {
    border: 1px solid #999999a1;
    width: 80%;
    margin-left: 10%;
}
/*Transaction animation CSS*/
.ame_paystep {
    display: block;
    clear: both;
    margin: 20px auto;
    width: 445px;
}.ame_paystepbg {
    display: block;
    margin: 0px;
    padding: 0px;
}.ame_paystepinfo {
    display: block;
    margin-bottom: 15%;
    margin: 0px;
    padding: 0px;
    font-size: 11px;
    color: #444444;
    text-align: center;
}.step1.active {
    float: left;
    margin: 0;
    padding: 0;
    width: 90px;
}.step2 {
    float: left;
    margin: 0;
    padding: 0;
    text-align: center;
    width: 59%;
}.step3 {
    float: right;
    margin: 0;
    padding: 0;
    width: 90px;
}.ame_paystepbg > img {
    width: 100%;
}
/* End of Transaction animation CSS*/
#resendOtpBtn{
	float:right;
}
img#img_captcha_refresh {
    width: 100px;
    padding: 2px 0px 2px 0px;
}
img#img_captcha {
    /*width: 70%;*/
    height: 5%;
    margin-top: 1px;
    border-radius: 3px;
}
thead>tr {
    background-color: #8080800f;
}
thead>tr {
    background-color: #8080800f;
}
label.lbl_captcha {
    font-size: 18px;
    background-color: #ed1c2480;
    color: #fff;
    padding: 5px 10px 5px 10px;
    border-radius: 5px;
    font-family: cursive;
    font-weight: 100;
}
h1 {
    color: #fff;
    font-size: 4.6rem;
    font-weight: 600;
    text-align: center;
    padding-bottom: 1rem;
    text-transform: none;
}
.rightNav {
    display: inline-block;
    float: right;
    padding-top: 1rem;
}span.call {
    float: left;
    display: inline-block;
}span.call>a {
    color: #fff;
    font-weight: bold;
    font-size: 2.4rem;
    padding-left: 3rem;
    padding-right: 4rem;
    display: inline-block;
    position: relative;
    pointer-events: none;
}
p{
	font-size: 14px;
    padding: 0;
    color: #3e3e3e;
    opacity: .5;
    padding: 2rem 0;
    line-height: 2rem;
    font-weight: 400;
    text-align: center;
}
footer .lastFooter {
    background-color: #ecf0f5;
    /* border-top: 1px solid #d6d6d6;
    padding-bottom: 6.5rem; */
    text-align: center;
}
.col-md-8.form-group.details_frm {
    padding: 15px 15px 15px 15px;
    border: 1px #00000014 solid;
    /* margin-top: 20px; */
    border-top-color: #00a65a;
    background-color: #ecf0f5;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #ed1c24;
    box-shadow: 0 10px 10px rgba(0,0,0,.05);
}
body {
	background-attachment: fixed;
    background-color: #ecf0f5;
    background-image: url('/images/background1.jpg');
    background-position: top;
    background-repeat: no-repeat;
    background-size: cover;
}
footer .lastFooter {
    background-color: #25408e;
    text-align: center;
    color: white;
}
.qrcode_img {
    width: 50%;
}
.form-control {
	display: inherit;
}
th, td {
    font-size: 13px;
    font-family: inherit;
}
.box-title {
    display: inline-block;
    font-size: 16px;
    margin: 0;
    line-height: 1;
    padding-bottom: 5px;
}
.div_amt_details {
    color: black !important;
    width: 16px;
    height: 16px;
    text-align: center;
    border-radius: 16px;
    font-size: 12px;
    border: #00000045 solid 1px;
    font-weight: bolder;
    margin-top: 10px;
    background: #252525;
}
.div_amt_details>a {
    color: white;
    font-weight: 900;
    font-family: monospace;
}
button.glyphicon.glyphicon-search.btn.btn-primary.mb-2 {
    background-color: #428bca;
    border: #428bca;
    padding: 10px 10px 10px 10px;
}
button.glyphicon.btn.btn-primary.mb-2 {
    background-color: #ed1c24;
    border: #ed1c24;
    padding: 10px 10px 10px 10px;
}
form.form-group.details_frm {
	padding: 15px 15px 15px 15px;
    border: 1px #00000014 solid;
    /* margin: 20px; */
    border-top-color: #00a65a;
    background-color: #ecf0f5;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #ed1c24;
    box-shadow: 0 10px 10px rgba(0,0,0,.05);
}
body{
	background-color: #ecf0f5;
}
.row {
    margin-right: 0;
    margin-left: 0;
}
</style>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-sm bg-light navbar-light static-top">
		<img style="margin: 25px;" src="/images/logo.svg" alt="Logo"
			style="width:40px;">
		<div class="rightNav">
			<span class="call"> <a href="tel:18002666444">1800 2666 444</a>
			</span>
		</div>
	</nav>
	<div class="row ">
		<div class="col-md-12">
			<!-- search fields  -->
			<div class="col-md-4" id="div_search_form">
				
			</div>
		</div>
	</div>
	<!-- Transaction flow animation -->
	<div class="row">
		<div class="col-md-12">
			<div class="ame_paystep">
				<div class="ame_paystepbg">
					<!-- <img src="/images/order1.png"> -->
					<%-- <c:if test="${ step_image!=null}">
						<img src="${step_image}">
					</c:if> --%>					
					<%-- <img src="${step_image_final}" style="display:${display1}"/> --%>
				</div>
				<%-- <c:if test="${ step_image!=null}">
					<div class="ame_paystepinfo">
						<div class="step1 active">
							Provide your <br> Details
						</div>
						<div class="step2">
							Do <br> Payment
						</div>
						<div class="step3">
							Receive Online <br> Confirmation
						</div>
					</div>
				</c:if> --%>
			</div>
		</div>
	</div>
	<footer>
		<div class="lastFooter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<p>
							<strong><h1>Temporarily Down For Maintenance..!!!</h1> </strong>
						</p>
					</div>
				</div>		
			</div>
		</div>
	</footer>
</body>
<script type='text/javascript'>
    // Tool tip logic
    $(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
	    // --- Disabling Continue with Payment on submit
	    $(':input[name="playOverDue"]').prop('disabled', true);
	    $(':input[name="playOverDueCharge"]').prop('disabled', true);
	    
	    // on change of "Charges to Pay" input text
	    $('#charge_to_pay').keyup(function() {
	    	var originalvalue = $("#charge_to_pay").val();
	    	$("#charge_to_pay_r").text(originalvalue);
	   	});
	   	$('#pay_emi_text').keyup(function() {
	    	var originalvalue = $("#pay_emi_text").val();
	    	$("#pay_emi").text(originalvalue);
	   	});
	    // Disabling search fields on ready state
	   	if($('input[id="brLoanCodeParam"]').is(':checked')){
			$(':input[id="brLoanCode"]').prop('disabled', false);
			$(':input[id="applNo"]').prop('disabled', true);
		}	
		// Disabling amount input fields
	   	$("#pay_emi_text").prop('disabled', true); 
    	$("#charge_to_pay").prop('disabled', true);
	});
    $(function () {
        var $inputs = $('input[name=brLoanCode],input[name=applNo]');
        $inputs.on('input', function () {
            // Set the required property of the other input to false if this input is not empty.
            $inputs.not(this).prop('required', !$(this).val().length);
        });
    });
    // ------------- Selsction of overdue amount
    $('input[name="amount"]').change(function () {
    	$('input[name="amount_to_pay1"]').prop('checked', false);
    	setOverDueEmiBlock();
    });
	// ------------- Selection of Charges
    $('input[name="amount_to_pay1"]').change(function () {
    	$('input[name="amount"]').prop('checked', false);
    	setOverDueChargesBlock();
    });
    // ----------------
	// -------------- Function type 1 -----------------
  	function setOverDueEmiBlock(){
		$(':input[name="playOverDue"]').prop('disabled', false);
  		$(':input[name="playOverDueCharge"]').prop('disabled', true);
  		$("#pay_emi_text").prop('disabled', true);
  		$("#charge_to_pay").prop('disabled', true);
  		if($('input[id="pay_emi"]').is(':checked')){
	    	console.log("Checked pay_emi_text");
	    	$("#pay_emi_text").prop('disabled', false);
	    }if(!$('input[id="pay_emi"]').is(':checked')){
	    	$("#charge_to_pay").prop('disabled', true);
	    	$("#pay_emi_text").prop('disabled', true);
		}
	}  	

  	function setOverDueChargesBlock(){
		$(':input[name="playOverDueCharge"]').prop('disabled', false);
    	$(':input[name="playOverDue"]').prop('disabled', true);
    	$("#charge_to_pay").prop('disabled', true);
    	$("#pay_emi_text").prop('disabled', true);
  		if($('input[id="charge_to_pay_r"]').is(':checked')){
	    	console.log("Checked charge_to_pay");
	    	$("#charge_to_pay").prop('disabled', false);
	    }if(!$('input[id="charge_to_pay_r"]').is(':checked')){
	    	$("#charge_to_pay").prop('disabled', true);
	    	$("#pay_emi_text").prop('disabled', true);
		}
	}
	// ------------------- End of function type 1 ----------------
	function printData(divName) {
		var printContents = document.getElementById(divName).innerHTML;
		var originalContents = document.body.innerHTML;
		document.body.innerHTML = printContents;
		window.print();
		document.body.innerHTML = originalContents;
	}

	/*$('#printReceipt').on('click', function() {
		printData();
	})*/

	// Changing Placeholder text
	$('input[name="search_param"]').change(
		function() {
			if ($('input[id="brLoanCodeParam"]').is(':checked')) {
				$("input[id='brLoanCode']").attr('placeholder',
						'Enter 11 digit Unique Loan Code');
				$("input[id='brLoanCode']").attr('maxlength', '11');
				$("input[id='brLoanCode']").attr('minlength', '11');
			}
			if ($('input[id="appNoParam"]').is(':checked')) {
				$("input[id='brLoanCode']").attr('placeholder',
						'Enter Application Number');
				$("input[id='brLoanCode']").attr('maxlength', '8');
				$("input[id='brLoanCode']").attr('minlength', '8');
			}
		});
	// Refresh Captcha
	$('#img_captcha_refresh').click(function() {
		var d = new Date();
		$('#img_captcha').attr('src', '/captcha?' + d.getTime());
	});
	// onclick resend OTP
	$("#resendOtpBtn").click(function(){
		/*$('#form_search :input').prop('disabled', false);
		$('input[id="brLoanCode"]').prop('disabled', false);
		$('#otpValidateForm').empty();
		$('#otpSentResponse').empty();*/
		$.ajax({url: "/resendOtp", 
			method : "POST",
			success: function(result){
				alert(result);
			},fail: function(data, error){
				console.log(data+ " Erro:"+error);
			}
		});
	});
	// Search Form validation

	$("#form_search").validate({
		rules : {
			brLoanCode : "required",
			captcha : "required",
			mobileNumber : {
				required : true,
				digits : true,
				minlength : 10,
				maxlength : 10,
			},
			password : {
				required : true,
				minlength : 5,
			}
		},
		messages : {
			brLoanCode : {
				required : "Enter Application Number/Loan Code.",
			},
			captcha : {
				required : "Enter Captcha",
			},
			mobileNumber : {
				required : "Please enter phone number",
				digits : "Please enter valid phone number",
			}
		}
	});
	// End of Search form validation
	// OTP form validation
	$("#form_validate_otp").validate({
		rules : {
			otpData : {
				required : true,
				digits : true,
				minlength : 4,
				maxlength : 4,
			}
		},
		messages : {
			otpData : {
				required : "Enter OTP",
			}
		}
	});
	// End of OTP form validation
	// Overdue form validation
	$("#form_overdue").validate({
	  rules : {
	    pay_emi_text : {
	      required : true,
	      digits : true
	    }
	  },
	  messages : {
	    pay_emi_text : {
	      required : jQuery.format("Enter amount between maximum and minimum."),
	    }
	  }
	});
	// End of Overdue form validation
	// Overdue Charges form validation
	$("#form_charges").validate({
		rules : {
			charge_to_pay : {
				required : true,
				digits : true
			}
		},
		messages : {
			charge_to_pay : {
				required : jQuery.format("Enter amount between maximum and minimum."),
			}
		}
	});
	// End of Overdue Charges form validation
	jQuery.extend(jQuery.validator.messages, {
	    //maxlength: jQuery.validator.format("Enter amount between maximum and minimum."),
	    //minlength: jQuery.validator.format("Enter amount between maximum and minimum."),
	    max: jQuery.validator.format("Enter amount between maximum and minimum."),
	    min: jQuery.validator.format("Enter amount between maximum and minimum.")
	});
	// clear search form values
	$("#btn_make_another_pay").click(function(){
		window.location='/payment';
		$('#brLoanCode').val("");
		$('#mobileNumber').val("");
	});
	// Validate search fields
	function validateNumber(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;
	    return true;
	}	
	// Script to avoid decimal values
	function validateDecimal(el){
	 var ex = /^[0-9]*$/;
	 if(ex.test(el.value)==false){
	   el.value = el.value.substring(0,el.value.length - 1);
	  }
	}
	// Validate amount to restrict decimal values
	$("#pay_emi_text, #charge_to_pay").on("keypress keyup blur",function (event) {    
       $(this).val($(this).val().replace(/[^\d].+/, ""));
        if ((event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
	// Script to round to two decimals
	function setTwoNumberDecimalEMI(event) {
	    var formData = parseFloat($('#pay_emi_text').val()).toFixed(2);
	  	$('#pay_emi_text').val(formData);
	}function setTwoNumberDecimalCharges(event) {
	    var formData = parseFloat($('#charge_to_pay').val()).toFixed(2);
	  	$('#charge_to_pay').val(formData);
	}
</script>
<script src="/js/jquery.side-slider.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sideslider').sideSlider();
    });
    (function() {
        var input = document.getElementById('mobileNumber');
        var pattern = /^[6-9][0-9]{0,9}$/;
        var value = input.value;
        !pattern.test(value) && (input.value = value = '');
        input.addEventListener('input', function() {
            var currentValue = this.value;
            if(currentValue && !pattern.test(currentValue)) this.value = value;
            else value = currentValue;
        });
    })();
</script>
</html>
