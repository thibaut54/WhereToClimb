package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;
import org.thibaut.wheretoclimb.model.entity.Role;
import org.thibaut.wheretoclimb.model.entity.User;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.pojo.PasswordForm;
import org.thibaut.wheretoclimb.webapp.validation.pojo.UserForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.PasswordValidator;
import org.thibaut.wheretoclimb.webapp.validation.validator.UserValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class RegisterController extends AbstractController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private PasswordValidator passwordValidator;

	@Autowired
	private PasswordManager passwordManager;

	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == UserForm.class ) {
			dataBinder.setValidator( userValidator );
		}
	}

	// Set a form validator
	@InitBinder
	protected void initBinderPassword( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == PasswordForm.class ) {
			dataBinder.setValidator( passwordValidator );
		}
	}


	// Show Register page.
	@GetMapping( "/public/register" )
	public String initRegister( Model model ) {

		model.addAttribute( "userForm", new UserForm( ) );

		getGradesAndVerticalities( model );
		return "view/register";
	}

	// This method is called to save the registration information.
	// @Validated: To ensure that this Form
	// has been Validated before this method is invoked.
	@PostMapping( "/public/register" )
	public String saveRegister( Model model,
	                            @ModelAttribute( "userForm" ) @Validated UserForm userForm,
	                            BindingResult result,
	                            final RedirectAttributes redirectAttributes ) {

		// Validate result
		if ( result.hasErrors( ) ) {
			getGradesAndVerticalities(model);
			return "view/register";
		}

		User newUser = null;


		//If a user wants to register
		if ( userForm.getId( ) == null ) {
			User userToCreate = GenericBuilder.of( User::new )
					                    .with( User::setEmail, userForm.getEmail( ) )
					                    .with( User::setRoles, getManagerFactory().getRoleManager().findRoleByRoleName( "USER" ) )
					                    .with( User::setPassword, passwordManager.crypt( userForm.getPassword( ) ) )
					                    .with( User::setFirstName, userForm.getFirstName( ) )
					                    .with( User::setLastName, userForm.getLastName( ) )
					                    .with( User::setUserName, userForm.getUserName( ) )
					                    .with( User::setCreateAccountDate, LocalDateTime.now( ) )
					                    .with( User::setGender, userForm.getGender( ) )
					                    .with( User::setEmailVisible, userForm.isEmailVisible( ) )
					                    .with( User::setDateOfBirth, userForm.getDateOfBirth( ) )
					                    .with( User::setGradeAverage, userForm.getGradeAverage( ) )
					                    .build( );
			try {
				newUser = getManagerFactory( ).getUserManager( ).createUser( userToCreate );
			}
			// Other error!!
			catch ( Exception e ) {
				log.error( "error occuring create/update user account: " + userForm.getUserName( ), e );
				model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
				getGradesAndVerticalities(model);
				return "view/register";
			}
			redirectAttributes.addFlashAttribute( "flashUser", newUser );

		}
		//If a member wants to edit his account datas
		else if ( userForm.getId( ) != null ) {

			User updatedUser = getManagerFactory( ).getUserManager( ).findById( userForm.getId( ) );
			updatedUser.setEmail( userForm.getEmail( ) );
			updatedUser.setUserName( userForm.getUserName( ) );
			updatedUser.setDateOfBirth( userForm.getDateOfBirth( ) );
			updatedUser.setFirstName( userForm.getFirstName( ) );
			updatedUser.setLastName( userForm.getLastName( ) );
			updatedUser.setGender( userForm.getGender( ) );
			updatedUser.setGradeAverage( userForm.getGradeAverage( ) );

			try {
				newUser = getManagerFactory( ).getUserManager( ).createUser( updatedUser );
			}
			// Other error!!
			catch ( Exception e ) {
				log.error( "error occuring update/update user account: " + userForm.getUserName( ), e );
				model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
				getGradesAndVerticalities(model);
				return "view/register";
			}
			redirectAttributes.addFlashAttribute( "flashUser", newUser );

		}
		return "redirect:/public/registerSuccessful";

	}


	@GetMapping( "/public/registerSuccessful" )
	public String viewRegisterSuccessful( Model model ) {
		return "view/registerSuccessful";
	}


	@GetMapping( "/user/changePassword" )
	public String changePassword( Model model ) {
		model.addAttribute( "passwordForm", new PasswordForm( ) );
		return "view/editPassword";
	}


	@PostMapping( "/user/changePassword/{id}" )
	public String changePassword( Model model,
	                              @PathVariable( name = "id" ) Integer id,
	                              @ModelAttribute( "passwordForm" ) @Validated PasswordForm passwordForm,
	                              BindingResult result ) {
		// Validate result
		if ( result.hasErrors( ) ) {
			return "view/editPassword";
		}

		User newUser = null;

		User updatedUser = getManagerFactory( ).getUserManager( ).findById( id );

		if ( passwordForm.getNewPassword( ).equals( passwordForm.getNewPasswordConfirm( ) ) ) {

			updatedUser.setPassword( passwordManager.crypt( passwordForm.getNewPassword( ) ) );

			try {
				newUser = getManagerFactory( ).getUserManager( ).createUser( updatedUser );
			}
			// Other error!!
			catch ( Exception e ) {
				log.error( "error occuring update user passwordt: " + updatedUser.getUserName( ), e );
				model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
				return "view/editPassword";
			}
		}

		return "view/editPasswordConfirm";
	}


	@GetMapping( "/user/editAccount" )
	public String editAccount( Model model ) {

		UserForm userForm = new UserForm( Optional.ofNullable( getManagerFactory( ).getUserManager( ).findByUserName( SecurityContextHolder.getContext( ).getAuthentication( ).getName( ) ) ).get( ) );

		model.addAttribute( "userForm", userForm );

		getGradesAndVerticalities( model );

		return "view/register";
	}
}
