public class currentLoggedUser
{
    private currentLoggedUser(){}
    private static String Email = null;
    public static String getEmail(){return Email;}
    public static void setEmail(String newEmail){Email = newEmail;}
}
