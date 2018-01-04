package tutorial.lovecode.ui;

import java.util.HashMap;

public class PresenterManager {

    private static PresenterManager instance;
    private HashMap<String,BasePresenter> presenters;


    private PresenterManager(){

        presenters = new HashMap<>();

    }

    public static synchronized PresenterManager getInstance(){
        if (instance == null){
            instance = new PresenterManager();
        }
        return instance;
    }


    public void put(String key, BasePresenter presenter){
        presenters.put(key, presenter);
    }

    public BasePresenter get(String key){
        return presenters.get(key);
    }

    public synchronized void removePresenter(String key){
        if(presenters != null){
            presenters.remove(key);
        }
    }

}
