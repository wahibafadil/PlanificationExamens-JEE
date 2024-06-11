package com.teste.monteste.Controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.teste.monteste.bo.Compte;
import com.teste.monteste.bo.UserPrincipal;
import com.teste.monteste.bo.Personnel;
import com.teste.monteste.web.models.UserndAccountInfos;

@Controller
public class LognController {

	@Autowired
	private HttpSession httpSession;

	/**
	 * Récupère les données de l'utilisateur connecté du contexte de securité et le
	 * stocke dans un objet personnalisé à enregistrer dans la session http
	 * 
	 * @return
	 */
	private UserndAccountInfos getUserAccount() {
		// On vérifie si les infors de l'utilisateur sont déjà dans la session
		UserndAccountInfos userInfo = (UserndAccountInfos) httpSession.getAttribute("userInfo");

		if (userInfo == null) {

			// On obtient l'objet representant le compte connecté (Objet UserPrincipal
			// implémentant UserDetails)
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			// On cast vers notre objet UserPrincipal
			Compte userAccount = ((UserPrincipal) principal).getUser();

			Personnel u = userAccount.getProprietaire();
			
			String roleName = userAccount.getRole().getNomRole();

			userInfo = new UserndAccountInfos(u.getId(), userAccount.getIdCompte(), userAccount.getLogin(),
					u.getNom(), u.getPrenom(), u.getEmail(), roleName);

			// On le stocke dans la session
			httpSession.setAttribute("userInfo", userInfo);
		}

		return userInfo;

	}

	@GetMapping("/showMyLoginPage")
	public String showLoginForm() {

		return "loginForm";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

	

	//@GetMapping("/cadreadmin/showHome")
	//public String showCadreAdminHomePage(Model m) {

		//UserndAccountInfos infoUser = getUserAccount();
		//m.addAttribute("userInfos", infoUser);
		//return "cadreadmin/userHomePage";

	//}


	@GetMapping("/admin/showAdminHome")
	public String showAdminHome(Model m) {

		UserndAccountInfos infoUser = getUserAccount();
		m.addAttribute("userInfos", infoUser);
		return "admin/adminHome";

	}

}

