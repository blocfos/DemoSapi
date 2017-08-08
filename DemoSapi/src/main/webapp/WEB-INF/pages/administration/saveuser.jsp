<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br />
<form id="form1" action="<c:url value="/administration/saveuser" />"
	method="post">
	<input type="hidden"
		value="${ (not empty userDev and (not empty userDev.username))? userDev.username : ''}"
		name="selectedid" id="selectedid" />
	<c:choose>
		<c:when test="${!empty userDev}">
			<h3>Modification de ${ userDev.libUser} - ${ userDev.username}</h3>
			<br />
			<table>
				<input type="hidden" name="username" value="${ userDev.username}" />
				<input type="hidden" name="id" value="${ userDev.id }" />
				</c:when>
				<c:otherwise>
					<h3>Ajout d'un nouvel utilisateur</h3>
					<br />
					<table>
						<tr>
							<td>Identifiant <span style="color: red">*</span></td>
							<td><input type="text" name="username" maxlength="7" /></td>
						</tr>
						<tr class="error" style="display: none" id="usernameerror">
							<td><span style="color: red">Ce champ est
									obligatoire</span></td>
							<td></td>
						</tr>
						<tr class="error" style="display: none" id="usernamelentherror">
							<td><span style="color: red"> L'identifiant doit
									être sur 7 caractères</span></td>
							<td></td>
						</tr>
						</c:otherwise>
						</c:choose>
						<tr>
							<td>Nom de l'utilisateur <span style="color: red">*</span></td>
							<td><input type="text" name="libUser"
								value="${ userDev.libUser}" /></td>
						</tr>
						<tr class="error" style="display: none" id="userliberror">
							<td><span style="color: red">Ce champ est
									obligatoire</span></td>
							<td></td>
						</tr>
						<tr>
							<td>Anonymiser les données <span style="color: red">*</span></td>
							<td><input type="radio" id="anonymiser_1" name="anonymiser"
								required="required" value="true" checked><label
								for="anonymiser_1">Oui</label> <input type="radio"
								id="anonymiser_3" name="anonymiser" required="required"
								value="false"><label for="anonymiser_3">Non</label></td>
						</tr>
						<tr class="error" style="display: none" id="anonymisererror">
							<td><span style="color: red">Ce champ est
									obligatoire</span></td>
							<td></td>
						</tr>
						<tr>
							<td>Périmètre <span style="color: red">*</span>
							</td>
							<td><input type="radio" id="perimeter_0" name="perimeter"
								required="required" value="niv5"> <label
								for="perimeter_0">Niveau 5</label> <input type="radio"
								id="perimeter_1" name="perimeter" required="required"
								value="niv4"> <label for="perimeter_1">Niveau 4</label>
								<input type="radio" id="perimeter_2" name="perimeter"
								required="required" value="niv3"> <label
								for="perimeter_2">Niveau 3</label></td>
						</tr>
						<tr class="error" style="display: none" id="perimetererror">
							<td><span style="color: red">Ce champ est
									obligatoire</span></td>
							<td></td>
						</tr>
						<tr valign="top">
							<td></td>
							<td id="listNiveaux">
								<table>
									<tr>
										<td valign="top"><select id="idniveau5" name="niveau5"
											style="display: none; width: 100px">
												<option selected="selected" value="">Tous</option>
										</select></td>
										<td valign="top"><select id="idniveau4" name="niveau4"
											style="display: none; width: 100px">
												<option selected="selected" value=""></option>
										</select>
										<td><select id="idniveau3" name="niveau3"
											multiple="multiple" style="display: none; width: 200px">
												<option selected="selected" value=""></option>
										</select></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr class="error" style="display: none" id="levelerror">
							<td colspan="2"><span style="color: red"> Merci de
									séléctionner un niveau correspondant au périmètre séléctioné</span></td>
						</tr>
					</table>
					<center>
						<input type="button" value="Enregistrer" id="submitButton" /> <input
							type="button" value="Retour"
							onclick="document.location.href='<c:url value="/administration/userlist" />'" />
					</center>
					<div style="margin-right: 10px; margin-left: 10px; color: red">
						<br>
						<br> <span style="color: red">*</span> Champ obligatoire
					</div>
					</form>


					<script type="text/Javascript">
$(document).ready(function(){
	$("#idniveau5").change(function() {
		getniveau4();
		$("#idniveau3").empty();
		$("#idniveau3").append("<option  value='' selected='selected'>Tous</option>");
	});
	$("#idniveau4").change(function() {
		getniveau3();
	});

	$("#submitButton").click(function(){validForm()});

	$('input[name=perimeter]').click(function(){
		switch($('input[name=perimeter]:checked').val()){
			case "niv5":
				$('#idniveau5').show();
				$('#idniveau4').hide();
				$('#idniveau3').hide();
				break;
			case "niv4":
				$('#idniveau5').show();
				$('#idniveau4').show();
				$('#idniveau3').hide();
				break;
			case "niv3":
				$('#idniveau5').show();
				$('#idniveau4').show();
				$('#idniveau3').show();
				break;
		}
	});

	new Ajax().postData("<c:url value='/getAllniveau5'/>",{}, function(data) {
		refreshlistdata("idniveau5", data);
	//Initilation si c'est un update
	<c:if test="${!empty userDev}">
		//Si l'utilisateur à au moin un perimetre>
		<c:if test="${empty nonlvl || !nolvl}" >
		//initalisation du niveau
		$('#perimeter_0').attr('checked',true);
		$('#idniveau5').show();
		<c:if test="${!empty idn5}">
			$("#idniveau5").val(${idn5});
			new Ajax().postData("<c:url value='/getAllniveau4'/>",{branche : $("#idniveau5").val()}, function(data) {
				refreshlistdata("idniveau4", data);
				<c:if test="${!empty idn4}">
				$('#perimeter_1').attr('checked',true);
				$("#idniveau4").val(${idn4});
				$('#idniveau4').show();
				new Ajax().postData("<c:url value='/getAllniveau3'/>",{domaine : $("#idniveau4").val()}, function(data) {
					refreshlistdata("idniveau3", data);
					<c:forEach items="${listN3}" var="idn3">
					$('#idniveau3').show();
					$('#perimeter_2').attr('checked',true);
					$("#idniveau3 option[value='${idn3}']").attr("selected", true);
					</c:forEach>
				});
			</c:if>
			});
		</c:if>
		</c:if>
		$("input[name='anonymiser'][value='${userDev.anonymiser}']").attr("checked", true);
	</c:if>
	});
});


function getniveau4(){
	new Ajax().postData("<c:url value='/getAllniveau4'/>",{branche : $("#idniveau5").val()}, function(data) {
		refreshlistdata("idniveau4", data);
		});
}

function getniveau3(){
	new Ajax().postData("<c:url value='/getAllniveau3'/>",{domaine : $("#idniveau4").val()}, function(data) {
		refreshlistdata("idniveau3", data);
		});
}

function refreshlistdata(idcomp, data) {
	idcomp = "#" + idcomp;
	$(idcomp).empty();
	if(idcomp=="#idniveau5")
		$(idcomp).append("<option  value='' selected='selected'>Tous</option>");
	if(idcomp=="#idniveau4")
		$(idcomp).append("<option  value='' selected='selected'></option>");
	$.each(data,function(i, item) {
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
	});
}

function validForm(){
	var valid = true;
	$(".error").hide();
	if($("input[name='anonymiser']:checked").val() == undefined){
		valid = false;
		$("#anonymisererror").show();
	}
	if($("input[name='perimeter']:checked").val() == undefined){
		valid = false;
		$("#perimetererror").show();
	}else{
		if($("input[name='perimeter']:checked").val() != "niv5"){
			if($("#idniveau4").val() == ""){
				valid = false;
				$("#levelerror").show();
			}
			if($("input[name='perimeter']:checked").val() == "niv3"){
				if($("#idniveau3").val() == undefined){
					valid = false;
					$("#levelerror").show();
				}
			}
		}
	}
	<c:if test="${empty userDev}">
	if($("input[name='username']").val().trim() == ""){
		valid = false;
		$("#usernameerror").show();
	}
    if($("input[name='username']").val().trim().length<7 && $("input[name='username']").val().trim().length >0){
        valid = false;
        $("#usernamelentherror").show();
    }
	</c:if>
	if($("input[name='libUser']").val().trim() == ""){
		valid = false;
		$("#userliberror").show();
	}

	if(valid){
		if($("#selectedid").val() == ''){
			var datatopost = {};
			jQuery.map($("#form1").serializeArray(),function(x){datatopost[x.name] = x.value;});
			new Ajax().postData("<c:url value='/administration/verifyUser'/>", datatopost, function(data){
				if(data){
					$("#usernamelentherror").html('<td colspan="2" style="color: red;">'+data+'</td>')
					$("#usernamelentherror").show();
				}
				else{
					$("#form1").submit();
				}
			});
		}else{
			$("#form1").submit();
		}
	}
}
</script>