package businessLogic;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    /**
     * Abstract method to be implemented by the classes that extend the MenuItem class
     * @return Float
     */
    public abstract float computePrice();
    public abstract String toString();
    public abstract String getName();
}
