<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/datepicker.css"/>" />
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.9.1.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
<%response.setHeader("Cache-Control", "no-cache, private, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>
<form id="form" name="form" method="post"
	action="<c:url value='expense/importfile/'/>"
	enctype="multipart/form-data">
	<h3>Consultation des reportings</h3>
	<br>
	<table class="tableAlignTop">
		<tr>
			<td valign="top"><%@include file="../template/niveaufilter.jsp"%></td>
			<td valign="top">


				<table>

					<tr id="idoperateur">
						<td><label>Op&eacute;rateur :</label></td>
						<td><select id="idselectoperateur" style="width: 250px;"
							name="operateur">
								<option value="-1" selected="selected">Tous</option>
								<c:forEach items="${operateurList}" var="items">
									<option value="<c:out value="${items}" />"><c:out
											value="${items}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr id="idlineStatut">
						<td><label>Statut de la ligne :</label></td>
						<td><select id="idselectlineStatut" style="width: 250px;"
							name="lineStatut">
								<option value="-1" selected="selected">Tous</option>
								<c:forEach items="${lineStatutList}" var="items">
									<option value="<c:out value="${items}" />"><c:out
											value="${items}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr id="idMaterialType">
						<td><label>Type de matériel :</label></td>
						<td><select id="idselectMaterialType" style="width: 250px;"
							name="MaterialType">
								<option value="-1" selected="selected">Tous</option>
								<c:forEach items="${MaterialTypeList}" var="items">
									<option value="<c:out value="${items}" />"><c:out
											value="${items}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Période :</label></td>
					</tr>
					<tr>
						<td><label for="datestart">Du :</label></td>
						<td>
							<div id="sandbox-container-year">
								<div class="input-append date">
									<input type="text" id="agasi_expensesbundle_reporttype_annee"
										name="agasi_expensesbundle_reporttype[annee]"
										required="required" readonly="true" /> <span class="add-on">
										<i class="icon-calendar"></i>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td><label for="dateend">Au :</label></td>
						<td>
							<div id="sandbox-container-month">
								<div class="input-append date">
									<input type="text" id="agasi_expensesbundle_reporttype_mois"
										name="agasi_expensesbundle_reporttype[mois]"
										required="required" readonly="true" /> <span class="add-on">
										<i class="icon-calendar"></i>
									</span>
								</div>
								&nbsp;<i class="icon-remove"
									onclick="javascript:FormCleanMonth()"> </i><span
									id="errorDateMessage" style="display: none; color: red;">La
									période sélectionnée ne peut pas excéder 12 mois.</span>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td colspan="2"><table id="idreportingradio">
					<tr>
						<td><input name="reportingradio" type="radio"
							onclick="checkvalue(this.value);" checked="checked"
							value="Benchgroupe" /></td>
						<td><label>Bench groupe</label></td>
						<td><input name="reportingradio" type="radio"
							onclick="checkvalue(this.value);" value="SuiviConso" /></td>
						<td><label>Suivi des conso</label></td>
						<td><input name="reportingradio" type="radio"
							onclick="checkvalue(this.value);" value="TopXConso" /></td>
						<td><label>Top X des conso</label></td>
						<td class="idtrligneaffiche"
							style="display: none; padding-left: 40px;"><label
							for="idligneaffiche">Lignes affichées :</label></td>
						<td class="idtrligneaffiche" style="display: none"><input
							id="idligneaffiche" type="text" value="10" style="width: 100px"
							maxlength="4" /></td>
					</tr>
				</table></td>

		</tr>
	</table>
	<br> <br>
	<table align="center">
		<tr>
			<td><input type="button" value="Afficher le tableau"
				onclick="setNameField();generateBirt();" id="searchBt" /> <input
				type="button" value="Exporter les données au format CSV"
				onclick="exportcsv();" id="exportcsvBt" /> <input type="button"
				value="Exporter en PDF" onclick="setNameField();generateBirtPDF();"
				id="exportpdfBt" /></td>
		</tr>
	</table>
	<input type="hidden" id="idradiovalue" name="radiovaluehidden"
		value="Benchgroupe" /> <input type="hidden" id="dateendtraiteM"
		value="${maxDateFacMonth}" /> <input type="hidden" id="dateendtraiteY"
		value="${maxDateFacYear}" />
</form>
<%@include file="../template/niveaufilterscript.jsp"%>

<script type="text/javascript">
var dialog = new Dialog();
$(document).ready(	function() {

		;(function($){
			$.fn.datepicker.dates['fr'] = {
				days: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"],
				daysShort: ["Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"],
				daysMin: ["D", "L", "Ma", "Me", "J", "V", "S", "D"],
				months: ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"],
				monthsShort: ["Jan", "Fév", "Mar", "Avr", "Mai", "Jui", "Jul", "Aoû", "Sep", "Oct", "Nov", "Déc"],
				today: "Aujourd'hui",
				weekStart: 1,
				format: "dd/mm/yyyy"
			};
		}(jQuery));

		var endDate = new Date();
		endDate.setMonth($("#dateendtraiteM").val() - 1);
		endDate.setFullYear($("#dateendtraiteY").val());
		var monthdu;
		var monthduInt = endDate.getMonth()+1;
		if(monthduInt < 10){
			monthdu = '0' + monthduInt.toString();
		}else{
			monthdu = monthduInt.toString();
		}
		var dateFormat = "mm/yyyy";
		var dateMap = {};
		var activePicker;

		// Setup Years
		$('#sandbox-container-year .input-append.date').attr('data-year', endDate.getFullYear());
		$('#sandbox-container-month .input-append.date').attr('data-year', endDate.getFullYear());

		$('#sandbox-container-year .input-append.date').datepicker({
							autoclose: true,
							format: dateFormat,
							startView: 1,
							minViewMode: 1,
							language: "fr",
							startDate: "<c:out value='${minDateFacMonth}'/>/<c:out value='${minDateFacYear}'/>",
							endDate: "<c:out value='${maxDateFacMonth}'/>/<c:out value='${maxDateFacYear}'/>",
		})
		.on('show', function(ev) {

		})
		.on('hide', function(ev) {
			// Reset 2nd period
			$('#agasi_adminbundle_reporttype_mois').val('');
			// Set Start Date
			$('#sandbox-container-month .input-append.date')
				.datepicker('setStartDate', $('#sandbox-container-year .input-append.date').data('date'));
			if(getDateFromPicker($('#sandbox-container-month .input-append.date').data('date'),"/") < getDateFromPicker($('#sandbox-container-year .input-append.date').data('date'),"/"))
			{
				$("#agasi_expensesbundle_reporttype_mois").val('');
			}
		});


		$('#sandbox-container-month .input-append.date').datepicker({
							autoclose: true,
							format: dateFormat,
							startView: 1,
							minViewMode: 1,
							language: "fr",
							startDate: "<c:out value='${minDateFacMonth}'/>/<c:out value='${minDateFacYear}'/>",
							endDate: "<c:out value='${maxDateFacMonth}'/>/<c:out value='${maxDateFacYear}'/>",
						})
		.on('show', function(ev) {

		});

		// # REMOVE DISABLED ATTR
		$('select option').removeAttr('disabled');

		// # OnCheckboxToggle -- Show/Hide Limit
		$('#topdec').on('change', function() {
			$('.xconso-wrapper').show();
		});

		$('input[type="radio"]:not(#topdec)').on('change', function() {
			$('.xconso-wrapper').hide();
		});

		$('.datepicker-months .prev').on('click', function(e) {
//lam			var year = activePicker.attr('data-year');
//lam			activePicker.attr('data-year', (year*1-1));
			//disableMonths(activePicker, dateMap);
		});

		$('.datepicker-months .next').on('click', function(e) {
//lam			var year = activePicker.attr('data-year');
//lam			activePicker.attr('data-year', (year*1+1));
			//disableMonths(activePicker, dateMap);
		});

		$('.switch').on('click', function(e) {
			e.stopPropagation();

			// Hide
			$('.input-append.date').datepicker('hide');

			// IF FIRST PERIOD WAS SELECTED, APPLY RESTRICTION TO THE SECOND PICKER
			if ($(activePicker).closest('#sandbox-container-year').length) {
				$('#sandbox-container-month .input-append.date').datepicker('setStartDate', getDateFromPicker( $(this).text() ));
			}

			// Set value of the active Date Picker
			$(activePicker).find('input[type="text"]').val($(this).text());

		});

		// Prevent submit if Niveau6 is invalid
		// On exportCSV reset form action
		$('input[type="submit"]').on('click', function(ev) {
			document.getElementById('form_submit').action = exportAction;
		});

		$("#agasi_expensesbundle_reporttype_annee").val(monthdu+"/"+endDate.getFullYear());
		// Set Start Date
		$('#sandbox-container-month .input-append.date')
			.datepicker('setStartDate', endDate);

});



	function getDateFromPicker(period, separator) {
		if (typeof period == 'undefined') {
			// RETURN TODAY
			return new Date();
		}

		if (typeof separator == 'undefined') {
			separator = '-';
		}
		var date = period.split(separator);

		if (1 == date.length) {
			// YEAR ONLY -- RETURN 01/01/year
			return new Date(parseInt(period), 1, 0);
		}
		else if ( 2 == date.length ) {
			// MONTH/YEAR FORMAT
			return new Date(parseInt(date[1]), parseInt(date[0]), 0);
		}
		else if (2 < date.length) {
			return new Date(parseInt(date[2]), parseInt(date[1]), parseInt(date[0]));
		}
	}

	function generateBirt(){
		$("#errorDateMessage").hide();
		if(checkDateInterval()){
		var reportradiovalue = $("#idradiovalue").val();
		new Ajax().postData("<c:url value='/addparamBirtConsom'/>", createdata(), function(data) {
			    if(reportradiovalue=="Benchgroupe"){
			    	window.open("<c:url value='/reports?ReportName=consom_bench_group_nv5.rptdesign'/>");
				}
			    if(reportradiovalue=="SuiviConso"){
			    	window.open("<c:url value='/reports?ReportName=consom_suivi.rptdesign'/>");

				}
			    if(reportradiovalue=="TopXConso"){
			    	window.open("<c:url value='/reports?ReportName=topdesconso.rptdesign'/>");
				}
			});
		}else{
			$("#errorDateMessage").show();
		}
	}
    function exportcsv(){

    	var niveau5 = $("#idniveau5").val();
		var niveau4 = $("#idniveau4").val();
		var niveau3 = $("#idniveau3").val();
		var niveau2 = $("#idniveau2").val();
		var niveau1 = $("#idniveau1").val();
		var niveau0 = $("#idniveau0").val();
		var operator = $("#idselectoperateur").val();
		var etat = $("#idselectlineStatut").val();
		var type = $("#idselectMaterialType").val();
		var dateStartString = $("#agasi_expensesbundle_reporttype_annee").val();
		var dateEndString = $("#agasi_expensesbundle_reporttype_mois").val();

    	document.location.href = '<c:url value="/consommation/exportcsvdata?niveau5='
			+ niveau5
			+ '&niveau4='
			+ niveau4
			+ '&niveau3='
			+ niveau3
			+ '&niveau2='
			+ niveau2
			+ '&niveau1='
			+ niveau1
			+ '&niveau0='
			+ niveau0
			+ '&operator='
			+ operator
			+ '&etat='
			+ etat
			+ '&type='
			+ type
			+ '&dateStartString='
			+ dateStartString
			+ '&dateEndString=' + dateEndString
		    + '" />';
        }
	function generateBirtPDF(){
		$("#errorDateMessage").hide();
		if(checkDateInterval()){
		var reportradiovalue = $("#idradiovalue").val();
		new Ajax().postData("<c:url value='/addparamBirtConsom'/>", createdata(), function(data) {

			    if(reportradiovalue=="Benchgroupe"){
			    	window.open("<c:url value='/reports?ReportName=consom_bench_group_nv5.rptdesign&ReportFormat=pdf'/>");
				}
			    if(reportradiovalue=="SuiviConso"){
			    	window.open("<c:url value='/reports?ReportName=consom_suivi.rptdesign&ReportFormat=pdf'/>");

				}
			    if(reportradiovalue=="TopXConso"){
			    	window.open("<c:url value='/reports?ReportName=topdesconso.rptdesign&ReportFormat=pdf'/>");
				}
			});

		}
	}
	function checkDateInterval(){
		var result= true;
		var datestart = $("#agasi_expensesbundle_reporttype_annee").val().split('/');
		var dateend = $("#agasi_expensesbundle_reporttype_mois").val().split('/');
		if(dateend.length>1){
			if(dateend[1] != datestart[1]){
				if(dateend[1]-datestart[1] > 1){
					result = false;
				}
				else{
					var diff = datestart[0]-dateend[0];
					if(diff<1){
						result=false;
					}
				}
			}
		}
		return result;
	}

	function createdata() {
		return {
			niveau5 : $("#idniveau5").val(),
			niveau4 : $("#idniveau4").val(),
			niveau3 : $("#idniveau3").val(),
			niveau2 : $("#idniveau2").val(),
			niveau1 : $("#idniveau1").val(),
			niveau0 : $("#idniveau0").val(),
			niveau5sigle : $("#idniveau5sigle").val(),
			niveau4sigle : $("#idniveau4sigle").val(),
			niveau3sigle : $("#idniveau3sigle").val(),
			niveau2sigle : $("#idniveau2sigle").val(),
			niveau1sigle : $("#idniveau1sigle").val(),
			niveau0sigle : $("#idniveau0sigle").val(),
			operator : $("#idselectoperateur").val(),
			etat : $("#idselectlineStatut").val(),
			type : $("#idselectMaterialType").val(),
			dateStartString : $("#agasi_expensesbundle_reporttype_annee").val(),
			dateEndString : $("#agasi_expensesbundle_reporttype_mois").val(),
			ligne_aff : $("#idligneaffiche").val(),
		};
	}

	function checkvalue(radiovalue){
		$("#idradiovalue").val(radiovalue);
		if(radiovalue=="TopXConso"){
			$('.idtrligneaffiche').show();
		}else{
			$('.idtrligneaffiche').hide();
		}
	}


	function FormCleanMonth(){
		$("#agasi_expensesbundle_reporttype_mois").val("");
	}
</script>

