<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- TODO NEW MODULE : Change WEB with module's code and remove this comment-->
<title>Portail iSIS - WEB</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="shortcut icon"
	href="<c:url value="/resources/css/sg/img/favicon.ico"/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sg/css/intranetsg.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sg/css/intranet_menu.css"/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/sg/sg/blue/css/jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/sg/sg/blue/css/sg.css"/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/ui.jqgrid.css"/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/common.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/module.css"/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/dialog.css"/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/sg/sg/blue/css/ie_fixes.css"/>" />

<script type="text/javascript"
	src="<c:url value="/resources/sg/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/sg/js/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.jqGrid.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/grid.locale-fr.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.growl.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/Ajax.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/Grid.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/spin.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/Dialog.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/Bind.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/Inherit.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/DependantPickList.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/FilterForm.js"/>"></script>
<!-- fix IE6 -->
<script type="text/javascript"
	src="<c:url value="/resources/js/json-ie.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/common.js"/>"></script>

<!--[if lt IE 8]>
		<script type="text/javascript" defer src="<c:url value="/resources/js/sg/intranet_menu.js"/>"></script>
	<![endif]-->
<!--[if lt IE 7]>
		<script type="text/javascript" defer="defer" src="<c:url value="/resources/js/sg/pngfix.js"/>"></script>
	<![endif]-->

<decorator:head />

</head>

<body>

	<div id="mainContainer">
		<div id="topContainer">
			<div id="topContent">

				<div id="outils">
					<div id="outilsGroupe">
						<ul>
							<li>Bienvenue <sec:authentication
									property="principal.fullname" /></li>
						</ul>
					</div>
					<div id="outilsSite">
						<ul>
							<li><a href="/sis-portal/">Retour au portail iSIS</a>
								&nbsp;&nbsp;|&nbsp;&nbsp; <a
								href="<c:url value="/modulelogout"/>">Se d&eacute;connecter</a>
							</li>
						</ul>
					</div>
				</div>

				<div id="header">
					<div id="logo">
						<a href="/sis-portal/"><img
							src="<c:url value="/resources/css/sg/img/logo_SIOP.gif"/>"
							border="0" alt="SIOP" height="51px" /></a>
					</div>
					<div id="nomdusite">
						<img src="<c:url value="/resources/css/sg/img/logo_module.gif"/>"
							border="0" alt="XXX" height="51px" />
					</div>
				</div>

				<div id="onglets">
					<ul id="tabs">
						<li class="homeLink"><a href="<c:url value="/index"/>"><div
									id="tabhome"
									class="${pageContext.request.requestURL.toString().contains('index')? 'active' : ''}"></div></a></li>
						<li
							class="${pageContext.request.requestURL.toString().contains('consommation')? 'active' : ''}"><a
							href="<c:url value="/consommation/consulReporting"/>">Consommations
								t&eacute;l&eacute;phoniques</a></li>
						<li
							class="${pageContext.request.requestURL.toString().contains('expense')? 'active' : ''}"><a
							href="<c:url value="/expense/consulReporting"/>">Expense</a></li>
						<sec:authorize access="hasAnyRole('AGA_ADMIN','ADMIN')">

							<li id="tab_admin"
								class="${pageContext.request.requestURL.toString().contains('administration')? 'active' : ''}">
								<a href="<c:url value="/administration/"/>">Administration</a>
							</li>
						</sec:authorize>
						<li
							<c:if
							test="${pageContext.request.requestURL.toString().contains('contact')}">
							class="active"
							</c:if>><a
							href="<c:url value="/contact"/>">Contact</a></li>
					</ul>
				</div>
				<div id="navigationHoriz">
					<ul id="navH">
						<!--  SubMenu consommation  -->
						<c:if
							test="${pageContext.request.requestURL.toString().contains('consommation')}">
							<li id="tabconsommationconsulrepotings"
								class="${pageContext.request.requestURL.toString().contains('consommation/consulReporting')? 'active' : ''}"><a
								href="<c:url value="/consommation/consulReporting"/>">Consultation
									des reportings</a></li>
							<sec:authorize access="hasAnyRole('AGA_CDG','AGA_ADMIN','ADMIN')">
								<li
									class="${pageContext.request.requestURL.toString().contains('/consommation/importation')? 'active' : ''}"><a
									href="<c:url value="/consommation/importation"/>">Importations</a></li>
							</sec:authorize>
						</c:if>

						<!--  SubMenu expense  -->
						<c:if
							test="${pageContext.request.requestURL.toString().contains('expense')}">
							<li
								class="${pageContext.request.requestURL.toString().contains('expense/consulReporting')? 'active' : ''}"><a
								href="<c:url value="/expense/consulReporting"/>">Consultation
									des reportings</a></li>
							<sec:authorize access="hasAnyRole('AGA_CDG','AGA_ADMIN','ADMIN')">
								<li
									class="${pageContext.request.requestURL.toString().contains('expense/expenseImportation')? 'active' : ''}"><a
									href="<c:url value="/expense/expenseImportation"/>">Importations</a></li>
							</sec:authorize>

						</c:if>


						<!--  Admini notice  -->
						<c:if
							test="${pageContext.request.requestURL.toString().contains('administration')}">
							<li
								class="${pageContext.request.requestURL.toString().contains('notice')? 'active' : ''}"><a
								href="<c:url value="/administration/notice/"/>">Notice</a></li>
							<li
								class="${pageContext.request.requestURL.toString().contains('user') ? 'active' : ''}"><a
								href="<c:url value="/administration/userlist"/>">Gestion des
									utilisateurs</a></li>
							<sec:authorize access="hasAnyRole('AGA_ADMIN','ADMIN')">
								<li
									class="${pageContext.request.requestURL.toString().contains('administration/rubrique') or pageContext.request.requestURL.toString().contains('administration/configrubrique')? 'active' : ''}"><a
									href="<c:url value="/administration/rubrique"/>">Configuration
										des rubriques Expense</a></li>
							</sec:authorize>
						</c:if>
					</ul>
					<div style="position: relative; float: right">
						<ul id="navH">
							<c:if test="${noticeExists}">
								<li><a href="<c:url value="/notice"/>">Notice</a></li>
							</c:if>
						</ul>
					</div>
				</div>

			</div>

			<div id="content">
				<div class="blue">
					<decorator:body />
				</div>

				<div class="clearFix"></div>
				<br class="clearFix" />
				<!-- **** IE 6 Fixes **** -->

			</div>

		</div>

		<div id="bottomContainer">
			<div id="footer">&copy; Soci&eacute;t&eacute;
				G&eacute;n&eacute;rale&nbsp;&nbsp;|&nbsp;&nbsp;Version :
				${project.version}</div>
		</div>

	</div>

</body>
</html>
