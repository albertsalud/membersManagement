package com.albertsalud.members.model.services.result;

import lombok.Data;

@Data
public abstract class ResultBean {
	
	protected boolean ok = true;
	protected String error;

}
