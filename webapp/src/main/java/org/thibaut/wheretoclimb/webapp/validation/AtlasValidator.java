package org.thibaut.wheretoclimb.webapp.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;

@Component
public class AtlasValidator implements Validator {

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
		return clazz == AtlasForm.class;
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

		AtlasForm atlasForm = ( AtlasForm ) target;

		ValidationUtils.rejectIfEmpty( errors, "name", "Pattern.atlasForm.field" );
		ValidationUtils.rejectIfEmpty( errors, "country", "NotEmpty.atlasForm.field" );
		ValidationUtils.rejectIfEmpty( errors, "region", "NotEmpty.atlasForm.field" );

		if ( !( atlasForm.getCountry( ).matches( "^[a-zA-Z ]{4,50}$" ) ) ) {
			errors.rejectValue( "country", "Pattern.atlasForm.field" );
		}

	}
}
