<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="form" method="post"
	action="<c:url value="/administration/configrubrique" />">
	<input type="hidden" name="selectedid" id="selectedid"
		value="${selectedid}" /> <input type="hidden" name="page" id="page"
		value="${page}" /> <input type="hidden" name="sidx" id="sidx"
		value="${sidx}" /> <input type="hidden" name="sord" id="sord"
		value="${sord}" />
	<h3>Configuration des rubriques Expense</h3>
	<br />
	<div id="containertabledata" style="width: 90%; margin: 0 auto;">
		<input type="button" name="newdossier" value="Ajouter une rubrique"
			onclick="javascript:addrubrique();"> <br /> <br />
		<table id="tableData">
		</table>
	</div>
	<div id="gridPager"></div>
</form>
<script type="text/javascript">
		$("#tableData")
				.jqGrid(
						{
							url : '<c:url value="/administration/rubrique/list" />',
							postData : {},
							mtype : 'POST',
							datatype : 'json',
							ajaxGridOptions : {
								contentType : 'application/json; charset=utf-8'
							},
							serializeGridData : function(data) {
								return JSON.stringify(data);
							},
							jsonReader : {
								repeatitems : false,
								root : function(obj) {
									return obj.content;
								},
								page : function(obj) {
									return obj.number + 1;
								},
								total : function(obj) {
									return obj.totalPages;
								},
								records : function(obj) {
									return obj.totalElements;
								},
							},
							colNames : [ 'Rubrique', 'Sous-rubrique', 'Action' ],
							colModel : [
									{
										name : 'rubrique',
										index : 'rubrique',
										align : 'left',
									},
									{
										name : 'descr',
										index : 'descr',
										align : 'left',
									},
									{
										name : 'rubriqueId',
										index : 'rubriqueId',
										sortable : false,
										align : 'center',
										width: '20px',
										formatter : function(cellvalue,
												options, rowObject) {
											return "<div class='button-icon' onclick=\"editrubrique('"
													+ cellvalue
													+ "');\" title='Editer' ><span class='ui-icon ui-icon-pencil' /></div>";
										},
									} ],
							gridview : true,
							rownumbers : false,
							rowNum : 25,
							rowList : [ 25, 50, 75 ],
							pager : '#gridPager',
							viewrecords : true,
							height : '100%',
							<c:if test="${not empty page}">page:${page},</c:if>
							autowidth: true,
						});

		function editrubrique(id) {
			$("#selectedid").val(id);
			$("#page").val($('#tableData').getGridParam('page'));
			$("#sidx").val($('#tableData').getGridParam('sidx'));
			$("#sord").val($('#tableData').getGridParam('sord'));
			$("#form").submit();
		}
		function addrubrique(){
			$("#selectedid").val("");
			$("#page").val($('#tableData').getGridParam('page'));
			$("#sidx").val($('#tableData').getGridParam('sidx'));
			$("#sord").val($('#tableData').getGridParam('sord'));
			$("#form").submit();
		}
	</script>