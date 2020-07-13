package businessLogicLayer.validators;

public interface Validator<T> {
    /**
     * Method to be implemented by a class that implements the Validator interface. It is used to validate a specific field
     * of the object given as parameter.
     * @param t T
     */
    public void validate(T t);
}
