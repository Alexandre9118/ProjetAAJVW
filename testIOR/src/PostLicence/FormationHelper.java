package PostLicence;

/** 
 * Helper class for : Formation
 *  
 * @author OpenORB Compiler
 */ 
public class FormationHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert Formation into an any
     * @param a an any
     * @param t Formation value
     */
    public static void insert(org.omg.CORBA.Any a, PostLicence.Formation t)
    {
        a.insert_Streamable(new PostLicence.FormationHolder(t));
    }

    /**
     * Extract Formation from an any
     * @param a an any
     * @return the extracted Formation value
     */
    public static PostLicence.Formation extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof PostLicence.FormationHolder)
                    return ((PostLicence.FormationHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            PostLicence.FormationHolder h = new PostLicence.FormationHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the Formation TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[4];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "nomUniv";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "NomFormation";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "TypeFormation";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "nomRectorat";
                _members[3].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _tc = orb.create_struct_tc(id(),"Formation",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the Formation IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:PostLicence/Formation:1.0";

    /**
     * Read Formation from a marshalled stream
     * @param istream the input stream
     * @return the readed Formation value
     */
    public static PostLicence.Formation read(org.omg.CORBA.portable.InputStream istream)
    {
        PostLicence.Formation new_one = new PostLicence.Formation();

        new_one.nomUniv = istream.read_string();
        new_one.NomFormation = istream.read_string();
        new_one.TypeFormation = istream.read_string();
        new_one.nomRectorat = istream.read_string();

        return new_one;
    }

    /**
     * Write Formation into a marshalled stream
     * @param ostream the output stream
     * @param value Formation value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, PostLicence.Formation value)
    {
        ostream.write_string(value.nomUniv);
        ostream.write_string(value.NomFormation);
        ostream.write_string(value.TypeFormation);
        ostream.write_string(value.nomRectorat);
    }

}
