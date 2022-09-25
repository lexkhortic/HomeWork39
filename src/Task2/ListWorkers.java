package Task2;

import java.io.Serializable;
import java.util.ArrayList;

public class ListWorkers implements Serializable {
    private ArrayList<Worker> workersList;

    public ListWorkers() {
    }

    public ArrayList<Worker> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(ArrayList<Worker> workersList) {
        this.workersList = workersList;
    }
}
