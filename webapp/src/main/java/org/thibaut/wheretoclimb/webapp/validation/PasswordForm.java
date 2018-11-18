package org.thibaut.wheretoclimb.webapp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {

	private String currentPassword;
	private String newPassword;
	private String newPasswordConfirm;


}
