<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="form" method="post"
	action="<c:url value="/administration/saverubrique" />"
	enctype="multipart/form-data" accept-charset="UTF-8">
	<input type="hidden" name="selectedid" id="selectedid"
		value="${selectedid}" /> <input type="hidden" name="page" id="page"
		value="${page}" /> <input type="hidden" name="sidx" id="sidx"
		value="${sidx}" /> <input type="hidden" name="sord" id="sord"
		value="${sord}" /> <input type="hidden" name="add" id="add"
		value="true" />
	<h3>${empty rubriqueDev ? 'Ajout' : 'Modification'}d'une rubrique</h3>
	<table>
		<tr>
			<td>Rubrique :<span style="color: red">*</span></td>
		</tr>
		<tr>
			<td><input id="rubriquetxt" name="rubriquetxt"
				required="required" type="text" size="100"
				value="${empty rubriqueDev ? '' : rubriqueDev.getRubrique()}"></td>
		</tr>
		<tr id="msgrub" style="display: none; color: red;">
			<td>Ce champ est obligatoire</td>
		</tr>
		<tr>
			<td>Sous-rubrique:<span style="color: red">*</span></td>
		</tr>
		<tr>
			<td><input id="sousrubriquetxt" name="sousrubriquetxt"
				${empty rubriqueDev ? "" : "disabled"} required="required"
				type="text" size="100"
				value="${empty rubriqueDev ? '' : rubriqueDev.getDescr()}"></td>
		</tr>
		<tr id="msgdescr" style="display: none; color: red;">
			<td>Ce champ est obligatoire</td>
		</tr>
		<tr>
			<td><button type="button" onclick="submitdata(false)">Retour</button>
				<button type="button" onclick="submitdata(true)">Enregistrer</button></td>
		</tr>
	</table>
	<div style="margin-right: 10px; margin-left: 10px; color: red">
		<br>
		<br> <span style="color: red">*</span> Champ obligatoire
	</div>
</form>

<script type="text/javascript">
	function submitdata(isadd) {

		if (isadd) {
			$("#msgrub").hide();
			$("#msgdescr").hide();
			var haserror = false;
			if ($("#rubriquetxt").val().trim().length <= 0) {
				$("#msgrub").show();
				haserror = true;
			}
			if ($("#sousrubriquetxt").val().trim().length <= 0) {
				$("#msgdescr").show();
				haserror = true;
			}
			if (haserror) {
				return;
			}
		}
		$("#add").val(isadd);
		$("#form").submit();

	}
</script>
