package com.albertsalud.members.utils.mailing;

import org.springframework.stereotype.Component;

import com.albertsalud.members.model.entities.Member;

@Component
public class MailingGenerator {

	public String generateRecoveryEmailBody(Member member) {
		return "<html>"+
		"<body>"+
		"<p>Hola, " + member.getName() + "</p>"+ 
		"<p>Hem rebut una sol.licitut de recuperació de paraula de pas. Segueix aquest "+
			"<a href=\"http://daudecinc.tk/members/changePassword?p=" + member.getPassword() + "&e=" + member.getEmail() + "\">enllaç</a> per tal "+
			"d'establir una de nova.</p>"+
		"<p>En cas que no hagis sigut tu qui ha fet aquesta sol.licitut, ignora aquest correu.</p>"+
		"<p>L'equip de Dau de cinc.</p>"+
		"</body>"+
		"</html>";
	}

	public String generateActivateUserBody(Member member) {
		return "<html>"+
		"<body>"+
		"<p>Hola, " + member.getName() + "</p>"+ 
		"<p>Gràcies per registrar-te com a soci a Dau de cinc. Ara només falta una última passa, que confirmis la teva suscripció. " +
		"Pots fer-ho seguint aquest "+
			"<a href=\"http://daudecinc.tk/members/activeUser?p=" + member.getPassword() + "&e=" + member.getEmail() + "\">enllaç</a>.</p>"+
		"<p>En cas que no hagis sigut tu qui ha fet aquesta sol.licitut, ignora aquest correu.</p>"+
		"<p>L'equip de Dau de cinc.</p>"+
		"</body>"+
		"</html>";
	}

}
