/**
 * Created by clint on 27-05-2017.
 */
public class BL {

        public BL()
        {

        }

        public int getUser(String name, String password){
            DatabaseUser db = new DatabaseUser();
            int level = db.checkIfUser(name, password);
            return level;
        }

}
