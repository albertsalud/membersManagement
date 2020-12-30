package com.albertsalud.members.model.services.result;

import lombok.Data;

@Data
abstract class ResultBean {
	
	protected boolean ok = true;
	protected String error;

}
