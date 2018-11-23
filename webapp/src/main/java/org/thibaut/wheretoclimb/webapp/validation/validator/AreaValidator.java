package org.thibaut.wheretoclimb.webapp.validation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thibaut.wheretoclimb.webapp.validation.pojo.AreaForm;

@Component
public class AreaValidator implements Validator {

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
		return clazz == AreaForm.class;
	}

	/**
	 * Validate the supplied {@code target} object, which must be
	 * of a {@link Class} for which the {@link #supports(Class)} method
	 * typically has (or would) return {@code true}.
	 * <p>The supplied {@link Errors errors} instance can be used to report
	 * any resulting validator errors.
	 *
	 * @param target the object that is to be validated (can be {@code null})
	 * @param errors contextual state about the validator process (never {@code null})
	 * @see ValidationUtils
	 */
	@Override
	public void validate( Object target, Errors errors ) {

		AreaForm areaForm = ( AreaForm ) target;

		ValidationUtils.rejectIfEmpty( errors, "name", "NotEmpty.elementForm.field" );
		ValidationUtils.rejectIfEmpty( errors, "nearestCity", "NotEmpty.elementForm.field" );

		if ( !( areaForm.getName( ).matches( "^[a-zA-Z0-9 ]{4,50}$" ) ) ) {
			errors.rejectValue( "name", "Pattern.elementForm.name" );
		}
		if ( !( areaForm.getNearestCity( ).matches( "^[a-zA-Z ]{3,100}$" ) ) ) {
			errors.rejectValue( "nearestCity", "Pattern.areaForm.nearestCity" );
		}
		if ( ( areaForm.getAccess( ).length() > 1000) ) {
			errors.rejectValue( "access", "Pattern.areaForm.access" );
		}
		if ( ( areaForm.getRockType( ).length() > 50) ) {
			errors.rejectValue( "rockType", "Pattern.areaForm.rockType" );
		}
		if ( ( areaForm.getParkingAccess( ).length() > 1000) ) {
			errors.rejectValue( "parkingAccess", "Pattern.areaForm.parkingAccess" );
		}
	}
}
