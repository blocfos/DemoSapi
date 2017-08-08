<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(document).ready(function(){
	getniveau5();
	$("#idniveau5").change(function() {
		getniveau4();
		$("#idniveau3").empty();
		$("#idniveau2").empty();
		$("#idniveau1").empty();
		$("#idniveau0").empty();
	});
	$("#idniveau4").change(function() {
		getniveau3();
		$("#idniveau2").empty();
		$("#idniveau1").empty();
		$("#idniveau0").empty();
	});
	$("#idniveau3").change(function() {
		getniveau2();
		$("#idniveau1").empty();
		$("#idniveau0").empty();
	});
	$("#idniveau2").change(function() {
		getniveau1();
		$("#idniveau0").empty();
	});
	$("#idniveau1").change(function() {
		getniveau0();
	});
});




function getniveau5(){
	new Ajax().postData("<c:url value='/getniveau5'/>",{}, function(data) {
		refreshlistdata("idniveau5", data);
		});
}

function getniveau4(){
	new Ajax().postData("<c:url value='/getniveau4'/>",{branche : $("#idniveau5").val()}, function(data) {
		refreshlistdata("idniveau4", data);
		});
}

function getniveau3(){
	new Ajax().postData("<c:url value='/getniveau3'/>",{domaine : $("#idniveau4").val()}, function(data) {
		refreshlistdata("idniveau3", data);
		});
}

function getniveau2(){
	new Ajax().postData("<c:url value='/getniveau2'/>",{niveau3 : $("#idniveau3").val()}, function(data) {
		refreshlistdata("idniveau2", data);
		});
}

function getniveau1(){
	new Ajax().postData("<c:url value='/getniveau1'/>",{niveau2 : $("#idniveau2").val()}, function(data) {
		refreshlistdata("idniveau1", data);
		});
}

function getniveau0(){
	new Ajax().postData("<c:url value='/getniveau0'/>",{centreBudgetaire : $("#idniveau1").val()}, function(data) {
		refreshlistdata("idniveau0", data);
		});
}

function refreshlistdata(idcomp, data) {
	idcomp = "#" + idcomp;
	$(idcomp).empty();
	$(idcomp).append("<option value=''></option>");
	$.each(data,
			function(i, item) {
		        if(idcomp=="#idniveau5"){
		        	$(idcomp).append(
							"<option value='" + item.branche + "'>" + item.sigle
									+ "</option>");
			        }
		        if(idcomp=="#idniveau4"){
		        	$(idcomp).append(
							"<option value='" + item.domaine + "'>" + item.sigle
									+ "</option>");
			        }
		        if(idcomp=="#idniveau3"){
		        	$(idcomp).append(
							"<option value='" + item.niveau3 + "'>" + item.sigle
									+ "</option>");
			        }
		        if(idcomp=="#idniveau2"){
		        	$(idcomp).append(
							"<option value='" + item.niveau2 + "'>" + item.sigle
									+ "</option>");
			        }
		        if(idcomp=="#idniveau1"){
		        	$(idcomp).append(
							"<option value='" + item.centreBudgetaire + "'>" + item.sigle
									+ "</option>");
			        }
		        if(idcomp=="#idniveau0"){
		        	$(idcomp).append(
							"<option value='" + item.centreActivite + "'>" + item.sigle
									+ "</option>");
			        }

			});

}
</script>
<form id="form" method="post">
	<table style="border: 1px solid black; width: 415px;">


		<tr>
			<td><label for="idniveau5">Niveau5 : </label></td>
			<td><select id="idniveau5" style="width: 250px;" name="niveau5">
					<option value="" selected="selected"></option>
			</select></td>
		</tr>
		<tr>
			<td><label for="idniveau4">Niveau4 : </label></td>
			<td><select id="idniveau4" style="width: 250px;" name="niveau4">
					<option value="" selected="selected"></option>
			</select></td>
		</tr>
		<tr>
			<td><label for="idniveau3">Niveau3 : </label></td>
			<td><select id="idniveau3" style="width: 250px;" name="niveau3">
					<option value="" selected="selected"></option>
			</select></td>
		</tr>
		<tr>
			<td><label for="idniveau2">Niveau2 : </label></td>
			<td><select id="idniveau2" style="width: 250px;" name="niveau2">
					<option value="" selected="selected"></option>
			</select></td>
		</tr>
		<tr>
			<td><label for="idniveau1">Niveau1 : </label></td>
			<td><select id="idniveau1" style="width: 250px;" name="niveau1">
					<option value="" selected="selected"></option>
			</select></td>
		</tr>
		<tr>
			<td><label for="idniveau0">Niveau0 : </label></td>
			<td><select id="idniveau0" style="width: 250px;" name="niveau0">
					<option value="" selected="selected"></option>
			</select></td>
		</tr>
	</table>
</form>
