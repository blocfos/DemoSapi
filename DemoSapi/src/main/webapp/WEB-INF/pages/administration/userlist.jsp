<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Gestion des utilisateurs</h3>
<br />
<input type="button" value="Ajouter un utilisateur"
	onclick="document.location.href='<c:url value="/administration/newuser" />'" />
<br />
<br />
<table id="tableData">
</table>
<div id="gridPager"></div>
<br />
<script type="text/javascript">
var dialog = new Dialog();

new Ajax().postData("<c:url value="/administration/userlist/getUsersList" />",{},function(data){
$("#tableData").jqGrid({
		datatype : 'local',
		data : data,
		colNames : [ 'Identifiant','Nom de l\'utilisateur', 'Périmètre', 'Anonymiser','Action' ],
		colModel : [ {
			name : 'username',
			index : 'username',
			width : 90,
			align : 'center',
		}, {
			name : 'nom',
			index : 'nom',
			width : 120,
		},  {
			name : 'perimètre',
			index : 'perimètre',
			width : 520
		}, {
			name : 'anonymiser',
			index : 'anonymiser',
			width : 70
		}, {
			name : 'action',
			index : 'action',
			sortable : false,
			width : 70,
			formatter : displayButtons
		} ],
		gridview : true,
		rownumbers : false,
		sortname:"nom",
		sortorder:"asc",
		viewrecords : true,
		rowNum : 9999999,
		height : '100%'
	});
});

function displayButtons(cellvalue, options, rowObject) {

	var button = "<div class='button-icon' onclick=\"editUser('"
		+ rowObject.idUser
		+ "');\" title=\"Modifier l'utilisateur\" ><span class='ui-icon ui-icon-pencil' /></div>";

	button += "<div class='button-icon'><span class='ui-icon ui-icon-trash' title=\"Supprimer l'utilisateur\"  onclick=\"deleteUser('"
			+ rowObject.idUser
			+ "',"
			+ options.rowId
			+ ",false);\" /></div>";
	return button;
}

function editUser(idUser) {
	var url = '<c:url value ="/administration/edituser" />';
	var form = $('<form action="' + url + '" method="post">' +
	  '<input type="text" name="idUser" value="' + idUser + '" />' +
	  '</form>');
	$('body').append(form);
	form.submit();
}

function deleteUser(idUser, idrow, isConfirmed) {
	if(!isConfirmed){
		var content=$("<div class='dialog'>Etes-vous sûr de vouloir supprimer cet utilisateur ?</div>");
		dialog.actionDialog(content, "Demande de confirmation",  function(){deleteUser(idUser, idrow,true);});
	}
	else{
		$('#tableData').delRowData(idrow);
		new Ajax().postData("<c:url value="/administration/deleteUser" />", {
			idUser : idUser
		}, function() {
			dialog.closeDialog();
		});
	}
}
</script>