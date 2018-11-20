package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {

	private String currentPassword;
	private String newPassword;
	private String newPasswordConfirm;


}
