package businessLogicLayer.validators;

import model.Client;

public class ClientAddressValidator implements Validator<Client> {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 40;

    /**
     * Checks the length of the parameter client's address string. If it is not between the MIN and MAX lengths, it throws
     * an IllegalArgumentException.
     * @param client Client
     */
    public void validate(Client client) {
        if(client.getAddress().length() < MIN_LENGTH || client.getAddress().length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The client's address needs to be between 3 and 40 characters");
        }
    }
}
