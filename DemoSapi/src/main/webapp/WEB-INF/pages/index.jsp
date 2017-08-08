<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div style="text-align: center; margin-top: 20px; margin-bottom: 10px;">
	<span>Bienvenue sur l’application de gestion des consommations
		de téléphonie mobile et des dépenses en notes de frais.</span>
</div>
<div style="margin-bottom: 10px; text-align: center; font-size: 11px;">
	<span> Vous &ecirc;tes connect&eacute; sous l&#8217;identifiant
		<sec:authentication property="principal.username" />
	</span>
</div>

<div
	style="border-radius: 10px; height: 200px; text-align: justify; width: 630px; font-size: 14px; border: 2px solid; background-color: DarkGray; border-color: Gray; margin: 0px auto;">
	<div style="margin-right: 10px; margin-left: 10px;">Vous êtes
		dans l’application AGA’SI. Ce module permet le suivi des consommations
		téléphoniques et des notes de frais à travers des reportings.</div>
</div>