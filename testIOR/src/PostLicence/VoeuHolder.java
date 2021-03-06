package PostLicence;

/**
 * Holder class for : Voeu
 * 
 * @author OpenORB Compiler
 */
final public class VoeuHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Voeu value
     */
    public PostLicence.Voeu value;

    /**
     * Default constructor
     */
    public VoeuHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public VoeuHolder(PostLicence.Voeu initial)
    {
        value = initial;
    }

    /**
     * Read Voeu from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = VoeuHelper.read(istream);
    }

    /**
     * Write Voeu into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        VoeuHelper.write(ostream,value);
    }

    /**
     * Return the Voeu TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return VoeuHelper.type();
    }

}
