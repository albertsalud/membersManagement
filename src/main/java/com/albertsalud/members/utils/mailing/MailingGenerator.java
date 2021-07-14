package com.albertsalud.members.utils.mailing;

import org.springframework.stereotype.Component;

import com.albertsalud.members.model.entities.Member;

@Component
public class MailingGenerator {

	public String generateRecoveryEmailBody(Member member) {
		String body = 
				"<h1>Recuperació de paraula de pas</h1>"+
				"<p>Hola, " + member.getName() + "</p>"+ 
				"<p>Hem rebut una sol.licitut de recuperació de paraula de pas. Segueix aquest "+
				"<a href=\"http://daudecinc.tk/members/changePassword?p=" + member.getPassword() + "&e=" + member.getEmail() + "\">enllaç</a> per tal "+
				"d'establir una de nova.</p>"+
			"<p>En cas que no hagis sigut tu qui ha fet aquesta sol.licitut, ignora aquest correu.</p>"+
			"<p>L'equip de Dau de cinc.</p>";
		
		return generateFullHTML(body);
	}
	
	private String generateFullHTML(String body) {
		return "<!DOCTYPE html>"+
		"<html>"+
		"<head>"+
		"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
		"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">"+
		"	<link rel=\"stylesheet\" type=\"text/css\" href=\"http://daudecinc.tk/css/dd5.css\">"+
		"</head>"+
		"<body>"+
		"	<div id=\"header-email\">"+
		"		<div id=\"logo-image\">"+
		"			<a href=\"http://daudecinc.tk\" target=\"_blank\"><img src=\"http://daudecinc.tk/images/logo.png\"></a>"+
		"		</div>"+
		"	</div>"+
		"	<div id=\"content-wrapper\">"+
		"		<div id=\"content-email\" class=\"no-news\">"+
		body +
		"		</div>"+
		"	</div>"+
		"</body>"+
		"</html>";
	}

	public String generateActivateUserBody(Member member) {
		String body = "<h1>Activació d'usuari</h1>"+
				"<p>Hola, " + member.getName() + "</p>"+ 
				"<p>Gràcies per registrar-te com a soci a Dau de cinc. Ara només falta una última passa, que confirmis la teva suscripció. " +
				"Pots fer-ho seguint aquest "+
					"<a href=\"http://daudecinc.tk/members/activeUser?p=" + member.getPassword() + "&e=" + member.getEmail() + "\">enllaç</a>.</p>"+
				"<p>En cas que no hagis sigut tu qui ha fet aquesta sol.licitut, ignora aquest correu.</p>"+
				"<p>L'equip de Dau de cinc.</p>";
		
		return generateFullHTML(body);
	}

}
