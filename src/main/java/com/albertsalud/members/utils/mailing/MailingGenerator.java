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

}
