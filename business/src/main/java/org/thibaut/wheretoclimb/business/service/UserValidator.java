package org.thibaut.wheretoclimb.business.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.business.contract.UserManager;
import org.thibaut.wheretoclimb.model.beans.User;
import org.thibaut.wheretoclimb.model.beans.form.UserForm;

@Component
public class UserValidator implements Validator {

	// common-validator library.
	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	private UserManager userManager;
	@Autowired
	private ManagerFactory managerFactory;


	/**
	 * Can this {@link Validator} {@link #validate(Object, Errors) validate}
	 * instances of the supplied {@code clazz}?
	 * <p>This method is <i>typically</i> implemented like so:
	 * <pre class="code">return Foo.class.isAssignableFrom(clazz);</pre>
	 * (Where {@code Foo} is the class (or superclass) of the actual
	 * object instance that is to be {@link #validate(Object, Errors) validated}.)
	 *
	 * @param clazz the {@link Class} that this {@link Validator} is
	 *              being asked if it can {@link #validate(Object, Errors) validate}
	 * @return {@code true} if this {@link Validator} can indeed
	 * {@link #validate(Object, Errors) validate} instances of the
	 * supplied {@code clazz}
	 */
	@Override
	public boolean supports( Class< ? > clazz ) {
		return clazz == UserForm.class;
	}


	/**
	 * Validate the supplied {@code target} object, which must be
	 * of a {@link Class} for which the {@link #supports(Class)} method
	 * typically has (or would) return {@code true}.
	 * <p>The supplied {@link Errors errors} instance can be used to report
	 * any resulting validation errors.
	 *
	 * @param target the object that is to be validated (can be {@code null})
	 * @param errors contextual state about the validation process (never {@code null})
	 * @see ValidationUtils
	 */
	@Override
	public void validate( Object target, Errors errors ) {
		UserForm userForm = (UserForm) target;

		// Check the fields of AppUserForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.userForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.userForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.userForm.countryCode");

		if (!this.emailValidator.isValid(userForm.getEmail())) {
			// Invalid email.
			errors.rejectValue("email", "Pattern.userForm.email");
		} else if (userForm.getId() == null) {
			User dbUser = managerFactory.getUserManager().findByEmail( userForm.getEmail() );
			if (dbUser != null) {
				// Email has been used by another account.
				errors.rejectValue("email", "Duplicate.userForm.email");
			}
		}

		if (!errors.hasFieldErrors("userName")) {
			User dbUser = managerFactory.getUserManager().findByUserName( userForm.getUserName() );
			if (dbUser != null) {
				// Username is not available.
				errors.rejectValue("userName", "Duplicate.userForm.userName");
			}
		}

		if (!errors.hasErrors()) {
			if (!userForm.getConfirmedPassword().equals(userForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.userForm.confirmPassword");
			}
		}
	}
	
}
