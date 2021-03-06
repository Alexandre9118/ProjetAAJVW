package devON;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import PostLicence.DonneesInvalides;
import PostLicence.Etudiant;
import PostLicence.GestionDesVoeux;
import PostLicence.Minist�re;
import PostLicence.Minist�reHelper;
import PostLicence.Rectorat;
import PostLicence.RectoratPOA;
import PostLicence.Universite;
import PostLicence.Voeu;
import PostLicence.dossierEtudiant;

public class RectoratIMPL extends RectoratPOA {

	Hashtable<String,Universite>ListeListUniversite;
	Hashtable<String,ArrayList<String>>ValidationFormation;
	Hashtable<String,GestionDesVoeux>LesGDV;
	String nomRectorat;
	Minist�re MonMinistere;
    

	/*************************Constructeur***********************************/
	public RectoratIMPL(Hashtable<String, Universite> listeListUniversite) {
		super();
		ListeListUniversite = listeListUniversite;
		MonMinistere= Minist�reHelper.narrow(
				NamingServiceTool.getReferenceIntoNS("Ministere"));
		System.out.println("Ref�r�rence ministere recuperee" );


	}

	public RectoratIMPL() {
		super();
		ListeListUniversite = new Hashtable<String,Universite>();
		MonMinistere= Minist�reHelper.narrow(
				NamingServiceTool.getReferenceIntoNS("Ministere"));
		System.out.println("Ref�r�rence ministere recuperee" );
	}



	/**********************Fonction G�n�r�*********************/

	@Override
	public String nomRectorat() {
		// TODO Auto-generated method stub
		return nomRectorat;
	}

	@Override
	public void transfertDossier(dossierEtudiant dossierEtu, Voeu voeu) {

		// TODO Auto-generated method stub

		boolean candiddatureConforme=false;

		//->boolean CandidatureConforme(String NomFormationEtudiant,String Nomformationvoeu)
		if (CandidatureConforme(dossierEtu.etu.formation.NomFormation,voeu.formationVoeu.NomFormation))
		{	

			try {
				Universite universiteAcontacter;	
				universiteAcontacter =ListeListUniversite.get(voeu.formationVoeu.nomUniv);
				universiteAcontacter.envoyerCandidatureD(dossierEtu,dossierEtu.etu.ineEtudiant, voeu);

			} catch (DonneesInvalides e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			//si la candidature n'est pas conforme il faut la renvoyer a l�tudiant...good luck..
		}

	}
	@Override
	public void envoyerListeVoeuxGDV(Voeu[] lv, Etudiant etu)
			throws DonneesInvalides {
		// TODO Auto-generated method stub
		dossierEtudiant dossierEtu;
		Universite univEtudiant =GetUniversiteDansRecorat(etu.nomUniv);
		String NomFormationEtudiant;
		String NomFormationVoeux;


		for(int i=0;i<lv.length;i++)
		{
			/*histoire de vous faciliter la compr�hention*/
			NomFormationEtudiant=etu.formation.NomFormation;
			NomFormationVoeux=lv[i].formationVoeu.NomFormation;




			//La l'universit� auquel il postule est t'elle dans son rectorat
			if(lv[i].formationVoeu.nomRectorat.equals(this.nomRectorat))
			{
				//Avant tout! la candidature est elle valide?
				if(CandidatureConforme(etu.formation.NomFormation,lv[i].formationVoeu.NomFormation))
				{	

					//postule til dans son universit�? si oui pas de transfert de dossier

					if(lv[i].formationVoeu.nomUniv.equals(etu.nomUniv))
					{	

						univEtudiant.envoyerCandidature(etu.ineEtudiant, lv[i]);
					}
					//sinnon bin on r�cupere le dossier dans luniv de letudiant
					//et on lenvoi a lautre univ qui est elle aussi dans le rectorat
					else
					{
						dossierEtu=univEtudiant.madDossier(etu.ineEtudiant);
						Universite univPostuler =GetUniversiteDansRecorat(lv[i].formationVoeu.nomUniv);
						univPostuler.envoyerCandidatureD(dossierEtu, etu.ineEtudiant, lv[i]);
					}
				}
				else
				{
					//il faut la renvoyer a l�tudiant! A toi de jouer ;)
				}
			}
			//sinnon on fait le transfert vers le bon rectorat
			else
			{


				Rectorat recorat_a_Joindre=MonMinistere.recupererRectorat(lv[i].formationVoeu.nomRectorat);
				dossierEtu=univEtudiant.madDossier(etu.ineEtudiant);
				recorat_a_Joindre.transfertDossier(dossierEtu, lv[i]);



			}

		}

	}



	@Override
	public void envoyerDecisionCandidatureRectorat(String ine, Voeu voeu)
			throws DonneesInvalides {
		// TODO Auto-generated method stub

	}

	@Override
	public void envoyerDecisionCandidatureUniv(Etudiant etu, Voeu voeu)
			throws DonneesInvalides {
		// TODO Auto-generated method stub
		
		//si il s'agit de ce recorat qui doit r�cup�r� la r�ponse
		if(etu.formation.nomRectorat.equals(nomRectorat))
		{
			
			//Pour faire simple..le numero de voeu correspond a son GDV comme sa
			//facile de le retrouver
			GestionDesVoeux Gdv;
			Gdv=LesGDV.get(String.valueOf(voeu.numeroVoeu));
			Gdv.transmettreDecisionCandidatureRectorat(etu.ineEtudiant, voeu);
		}
		//sinnon faut le passer au bon recorat
		else
		{
			Rectorat recorat_a_Joindre=MonMinistere.recupererRectorat(etu.formation.nomRectorat);
			
			//on ne s'embete pas.. on appel direct le service sur le bon serveur
			recorat_a_Joindre.envoyerDecisionCandidatureUniv(etu,voeu);
		}
	}
	

	@Override
	public void inscriptionUniv(Universite iorLUniversite) {
		// TODO Auto-generated method stub
		
		ListeListUniversite.put(iorLUniversite.nomUniversite(), iorLUniversite);
	}

	@Override
	public void repondrePropositionVoeux(String ine, Voeu voeu)
			throws DonneesInvalides {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void inscriptionGDV(GestionDesVoeux Gdv) {
		// TODO Auto-generated method stub
		LesGDV.put(String.valueOf(Gdv.numeroGDV()), Gdv);
	}

	@Override
	public void deliberationJury() {
		// TODO Auto-generated method stub
Enumeration ListeUniv=this.ListeListUniversite.elements();
		
		while(ListeUniv.hasMoreElements())
		{
			Universite Un=null;
			
			Un=(Universite) ListeUniv.nextElement();
			Un.deliberationJury();
		}
	}

	/*********************Fonction cr�� par nous**************/

	//V�rifier q'une candidature est conforme

	public boolean CandidatureConforme(String NomFormationEtudiant,String Nomformationvoeu)
	{
		//On r�cupere la liste des formations valide pour la formation auquel il a postuler
		ArrayList<String> ListeFormationValidePrCandidature=ValidationFormation.get(Nomformationvoeu);

		//On cherche si on trouve la formation de l�tudiant dans les formations valide
		for (int i=0;i< ListeFormationValidePrCandidature.size();i++)
		{
			if(ListeFormationValidePrCandidature.get(i).equals(NomFormationEtudiant))
			{
				return true;
			}	
		}
		return false;
	}

	// v�rifier si luniv fait parti du recorat
	public boolean UniversiteDansRecorat(String univ)
	{
		if (ListeListUniversite.get(univ)!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// r�cup�rer luuniv si elle fait parti du recorat
	public Universite GetUniversiteDansRecorat(String univ)
	{
		return ListeListUniversite.get(univ);

	}

	public static void main(String[] args) {

    RectoratIMPL recto = new RectoratIMPL();
	}


	



}
