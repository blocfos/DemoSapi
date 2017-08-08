<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#idniveau5").change(function() {
		getniveau4();
		emptySelect("#idniveau3");
		emptySelect("#idniveau2");
		emptySelect("#idniveau0");
	});
	$("#idniveau4").change(function() {
		getniveau3();
		emptySelect("#idniveau2");
		emptySelect("#idniveau0");
	});
	$("#idniveau3").change(function() {
		getniveau2();
		emptySelect("#idniveau0");
	});
	$("#idniveau2").change(function() {
		 getniveau0byniveau2();

	});
	getniveau5();
});




function getniveau5(){
	new Ajax().postData("<c:url value='/getniveau5'/>",{}, function(data) {
		refreshlistdata("idniveau5", data);
		if(data.length == 1){
			getniveau4();
		}
		});
}

function getniveau4(){
	new Ajax().postData("<c:url value='/getniveau4'/>",{branche : $("#idniveau5").val()}, function(data) {
		refreshlistdata("idniveau4", data);
		if(data.length == 1){
			getniveau3();
		}
		});
}

function getniveau3(){
	new Ajax().postData("<c:url value='/getniveau3'/>",{domaine : $("#idniveau4").val()}, function(data) {
		refreshlistdata("idniveau3", data);
		if(data.length == 1){
			getniveau2();
		}
		});
}

function getniveau2(){
	new Ajax().postData("<c:url value='/getniveau2'/>",{niveau3 : $("#idniveau3").val()}, function(data) {
		refreshlistdata("idniveau2", data);
		if(data.length == 1){
			getniveau0byniveau2();
		}
		});
}

function getniveau0byniveau2(){
	new Ajax().postData("<c:url value='/getniveau0byniveau2'/>",{niveau2 : $("#idniveau2").val()}, function(data) {
		refreshlistdata("idniveau0", data);
		});
}

function refreshlistdata(idcomp, data) {
	idcomp = "#" + idcomp;
	$(idcomp).empty();
	if(data.length != 1){
		$(idcomp).append("<option value=''>Tous</option>");
	}
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

function emptySelect(select){
	$(select).empty();
	$(select).html("<option value='' selected='selected'></option>");
}

function setNameField(){
	$("#idniveau5sigle").val($("#idniveau5 option:selected").text());
	$("#idniveau4sigle").val($("#idniveau4 option:selected").text());
	$("#idniveau3sigle").val($("#idniveau3 option:selected").text());
	$("#idniveau2sigle").val($("#idniveau2 option:selected").text());
	$("#idniveau1sigle").val($("#idniveau1 option:selected").text());
	$("#idniveau0sigle").val($("#idniveau0 option:selected").text());

};
</script>