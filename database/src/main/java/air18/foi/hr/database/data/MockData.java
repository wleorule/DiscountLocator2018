package air18.foi.hr.database.data;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;

public class MockData {
    public static void writeAll(){
        Store acmeStore = new Store();
        acmeStore.setName("ACME Store");
        acmeStore.save(); // <-- available from BaseModel super class

        Discount bananas = new Discount();
        bananas.setName("Bananas off!");
        bananas.setDiscount(5);
        bananas.setStore(acmeStore);
        bananas.save();

        Discount tuna = new Discount();
        tuna.setName("Three for two!");
        tuna.setDiscount(33);
        tuna.setStore(acmeStore);
        tuna.save();
    }
}
