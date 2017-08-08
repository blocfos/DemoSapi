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
<form id="form" name="form" method="post"
	action="<c:url value='/expense/importfile'/>"
	enctype="multipart/form-data">


	<h3>Importation Expense</h3>

	<table align="center"
		style="width: 550px; margin-top: -2px; margin-left: 20px;">
		<tr>
			<td>
		<tr>
			<td colspan="2"><label id="idimportinfo"
				style="display: none; color: red"></label></td>
		</tr>
		<tr>
			<td><label for="idateimport" class="required">Mois/Année<span
					style="color: red;"> *</span></label></td>
			<td><div id="sandbox-container-date">
					<div class="input-append date">
						<input type="text"
							id="agasi_adminbundle_uploadmobiletype_moisannee"
							name="dateimport" required="required" /> <span class="add-on"><i
							class="icon-calendar"></i></span>
					</div>
				</div></td>
		</tr>
		<tr id="dateimportationempty" style="display: none; color: red;">
			<td align="center" colspan="2">Ce champ doit être renseigné.</td>
		</tr>
		<tr>
			<td><label>Fichier à importer <span style="color: red;">
						*</span>
			</label></td>
			<td><input type="file" id="idfileimportation"
				name="fileimportation" onchange="uploadfile(this);" /></td>
		</tr>
		<tr id="fileimportationempty" style="display: none; color: red;">
			<td align="center" colspan="2">Ce champ doit être renseigné.</td>
		</tr>
		<tr id="idFileDocJointinfo" style="display: none; color: red;">
			<td align="center" colspan="2">Extension invalide.<br>
				(Extensions autorisées : « csv »)
			</td>
		</tr>
		<tr>
			<td><label>Supprimer les données du mois </label></td>
			<td><table id="idradiosupprimerdumois">
					<tr>
						<td><input name="supprimerdumois" type="radio" value="1"
							checked="checked" /></td>
						<td><label>Oui</label></td>
						<td><input name="supprimerdumois" type="radio" value="0" /></td>
						<td><label>Non</label></td>
					</tr>
				</table></td>
		</tr>
	</table>
	<div align="center">
		<br /> <input value="Importer" id="importbutton"
			onclick="importdata();" type="button" />
	</div>
	<input type="hidden" value="" name="extension" id="extension" />
	<div style="margin-right: 10px; margin-left: 10px; color: red">
		<br>
		<br> <span style="color: red">*</span> Champ obligatoire
	</div>
</form>
<script type="text/javascript">
function validationcommit(){
	document.getElementById('fileimportationempty').style.display = 'none';
	document.getElementById('dateimportationempty').style.display = 'none';
	var valide = true;
	var datevalide = $("#agasi_adminbundle_uploadmobiletype_moisannee").val();
	if(datevalide==null||datevalide==""){
        valide = false;
        document.getElementById('dateimportationempty').style.display = '';
		}
	var filevalide = $("#idfileimportation").val();
	if(filevalide=null||filevalide==""){
		valide = false;
		document.getElementById('fileimportationempty').style.display = '';
		}
	var extension = $("#extension").val();
	if(extension=="1"){
		valide = false;
	}
	return valide;
}
$(document).ready(
		function() {
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

			$('#sandbox-container-date .input-append.date').datepicker({
							autoclose: true,
						    format: "mm/yyyy",
						    startView: 1,
						    minViewMode: 1,
						    language: "fr"
						});
		});
var saveMessage = "${importinfo}";

if(saveMessage!=null&&saveMessage.trim().length>0){
	    $("#idimportinfo").html(saveMessage);
		document.getElementById('idimportinfo').style.display = '';
		var isexportcsv = "${exportcsv}";
		if(isexportcsv=="true"){
			document.location.href = '<c:url value="/expense/exportcsv" />';
			}


}

function uploadfile(fileupload){
	document.getElementById('idFileDocJointinfo').style.display = 'none';
	var filename =  fileupload.value;
	var mime = filename.toLowerCase().substring(filename.lastIndexOf("."));
	if ($.inArray(mime, [ '.csv']) == -1) {
		            document.getElementById('idFileDocJointinfo').style.display = '';
		        	$("#extension").val('1');
	 			}

}

function importdata(){
	var valide =  validationcommit();
	var spinvar = new Spinner();
	spinvar.spin(document.getElementById('content'));
	if(valide==true){
		document.getElementById('idimportinfo').style.display = 'none';
		$("#form").submit();
	}else{
		spinvar.stop();
		}

}
</script>
