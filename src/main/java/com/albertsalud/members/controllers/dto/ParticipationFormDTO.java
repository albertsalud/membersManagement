package com.albertsalud.members.controllers.dto;

import com.albertsalud.members.model.entities.Activity;
import com.albertsalud.members.model.entities.Member;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipationFormDTO {

	public ParticipationFormDTO(Activity activity) {
		this.activity = activity;
	}

	@NotNull
	private Activity activity;
	
	@NotNull
	private Member member;
}
