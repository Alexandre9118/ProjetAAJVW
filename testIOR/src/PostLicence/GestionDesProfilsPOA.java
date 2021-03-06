package PostLicence;

/**
 * Interface definition : GestionDesProfils
 * 
 * @author OpenORB Compiler
 */
public abstract class GestionDesProfilsPOA extends org.omg.PortableServer.Servant
        implements GestionDesProfilsOperations, org.omg.CORBA.portable.InvokeHandler
{
    public GestionDesProfils _this()
    {
        return GestionDesProfilsHelper.narrow(_this_object());
    }

    public GestionDesProfils _this(org.omg.CORBA.ORB orb)
    {
        return GestionDesProfilsHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:PostLicence/GestionDesProfils:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("_get_numeroGDP")) {
                return _invoke__get_numeroGDP(_is, handler);
        } else if (opName.equals("connexion")) {
                return _invoke_connexion(_is, handler);
        } else if (opName.equals("consulterProfil")) {
                return _invoke_consulterProfil(_is, handler);
        } else if (opName.equals("inscriptionGestionDesVoeux")) {
                return _invoke_inscriptionGestionDesVoeux(_is, handler);
        } else if (opName.equals("modifierProfil")) {
                return _invoke_modifierProfil(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke__get_numeroGDP(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg = numeroGDP();
        _output = handler.createReply();
        _output.write_short(arg);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_connexion(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        PostLicence.IEtudiant arg0_in = PostLicence.IEtudiantHelper.read(_is);
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        try
        {
            PostLicence.GestionDesVoeux _arg_result = connexion(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();
            PostLicence.GestionDesVoeuxHelper.write(_output,_arg_result);

        }
        catch (PostLicence.DonneesInvalides _exception)
        {
            _output = handler.createExceptionReply();
            PostLicence.DonneesInvalidesHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_consulterProfil(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();

        try
        {
            PostLicence.Etudiant _arg_result = consulterProfil(arg0_in);

            _output = handler.createReply();
            PostLicence.EtudiantHelper.write(_output,_arg_result);

        }
        catch (PostLicence.DonneesInvalides _exception)
        {
            _output = handler.createExceptionReply();
            PostLicence.DonneesInvalidesHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modifierProfil(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();

        try
        {
            modifierProfil(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (PostLicence.DonneesInvalides _exception)
        {
            _output = handler.createExceptionReply();
            PostLicence.DonneesInvalidesHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_inscriptionGestionDesVoeux(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        PostLicence.GestionDesVoeux arg0_in = PostLicence.GestionDesVoeuxHelper.read(_is);

        inscriptionGestionDesVoeux(arg0_in);

        _output = handler.createReply();

        return _output;
    }

}
