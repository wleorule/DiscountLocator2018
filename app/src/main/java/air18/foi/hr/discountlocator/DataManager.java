package air18.foi.hr.discountlocator;

public class DataManager {
    //singleton
    private static DataManager instance;

    private DataManager()
    {

    }

    public static DataManager getInstance()
    {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }
}