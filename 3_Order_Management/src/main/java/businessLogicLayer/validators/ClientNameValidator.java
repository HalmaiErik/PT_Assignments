package businessLogicLayer.validators;

import model.Client;

public class ClientNameValidator implements Validator<Client> {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 40;

    /**
     * Checks the length of the parameter client's name string. If it is not between the MIN and MAX lengths, it throws
     * an IllegalArgumentException.
     * @param client Client
     */
    public void validate(Client client) {
        if(client.getName().length() < MIN_LENGTH || client.getName().length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The client's name length needs to be between 2 and 40 characters");
        }
    }
}
