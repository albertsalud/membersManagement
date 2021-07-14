package com.albertsalud.members.model.services.result;

import com.albertsalud.members.model.entities.Member;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberServicesResultBean extends ResultBean {
	
	private Member member;

}
