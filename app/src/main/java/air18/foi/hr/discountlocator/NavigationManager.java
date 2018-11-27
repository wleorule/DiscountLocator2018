package air18.foi.hr.discountlocator;

public class NavigationManager {
    //TODO - manage modules
    //TODO - manage drawer
    //TODO - manage data

    //singleton
    private static NavigationManager instance;

    private NavigationManager()
    {

    }

    public static NavigationManager getInstance()
    {
        if (instance == null)
            instance = new NavigationManager();

        return instance;
    }
}