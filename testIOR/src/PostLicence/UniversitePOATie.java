package PostLicence;

/**
 * Interface definition : Universite
 * 
 * @author OpenORB Compiler
 */
public class UniversitePOATie extends UniversitePOA
{

    //
    // Private reference to implementation object
    //
    private UniversiteOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public UniversitePOATie(UniversiteOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public UniversitePOATie(UniversiteOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public UniversiteOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(UniversiteOperations delegate_)
    {
        _tie = delegate_;
    }

    /**
     * _default_POA method
     */
    public org.omg.PortableServer.POA _default_POA()
    {
        if (_poa != null)
            return _poa;
        else
            return super._default_POA();
    }

    /**
     * Read accessor for nomUniversite attribute
     */
    public String nomUniversite()
    {
        return _tie.nomUniversite();
    }

    /**
     * Read accessor for villeUniversite attribute
     */
    public String villeUniversite()
    {
        return _tie.villeUniversite();
    }

    /**
     * Read accessor for academieUniversite attribute
     */
    public String academieUniversite()
    {
        return _tie.academieUniversite();
    }

    /**
     * Operation envoyerCandidature
     */
    public void envoyerCandidature(String ine, PostLicence.Voeu voeu)
        throws PostLicence.DonneesInvalides
    {
        _tie.envoyerCandidature( ine,  voeu);
    }

    /**
     * Operation envoyerCandidatureD
     */
    public void envoyerCandidatureD(PostLicence.dossierEtudiant dossierEtu, String ine, PostLicence.Voeu voeu)
        throws PostLicence.DonneesInvalides
    {
        _tie.envoyerCandidatureD( dossierEtu,  ine,  voeu);
    }

    /**
     * Operation madDossier
     */
    public PostLicence.dossierEtudiant madDossier(String ine)
    {
        return _tie.madDossier( ine);
    }

    /**
     * Operation repondrePropositionvoeux
     */
    public void repondrePropositionvoeux(String ine, PostLicence.Voeu voeu)
        throws PostLicence.DonneesInvalides
    {
        _tie.repondrePropositionvoeux( ine,  voeu);
    }

    /**
     * Operation deliberationJury
     */
    public void deliberationJury()
    {
        _tie.deliberationJury();
    }

}
