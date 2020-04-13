package businessLogicLayer;

import businessLogicLayer.validators.ClientAddressValidator;
import businessLogicLayer.validators.ClientNameValidator;
import businessLogicLayer.validators.Validator;
import databaseAccessLayer.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructor method. It initializes the clientDAO and the validator list by adding all the validators for the Client objects.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientNameValidator());
        validators.add(new ClientAddressValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Truncates the client table from the database.
     */
    public void truncateTable() {
        clientDAO.truncateTable();
    }

    /**
     * Validates and inserts the client given as parameter into the database.
     * @param client Client
     */
    public void insertClient(Client client) {
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }

        clientDAO.insert(client);
    }

    /**
     * Removes the client having the id given as parameter from the database.
     * @param id Integer
     */
    public void removeClientById(int id) {
        clientDAO.delete(id, "id");
    }

    /**
     * Lists all the clients in the client database table.
     * @return List<Client>
     */
    public List<Client> listClients() {
        return clientDAO.findAll();
    }

    /**
     * Finds the client with the name given as parameter. Throws a NoSuchElementException if none found.
     * @param name String
     * @return Client
     */
    public Client findClientByName(String name) {
        Client client = clientDAO.find(name, "name");

        if(client == null) {
            throw new NoSuchElementException("Client with name = " + name + " was not found");
        }
        return client;
    }

    /**
     * Finds the client with the name and address given as parameters. Throws a NoSuchElementException if none found.
     * @param name String
     * @param address String
     * @return Client
     */
    public Client findClientByNameAndAddress(String name, String address) {
        Client client = clientDAO.findClientByNameAndAddress(name, address);

        if(client == null) {
            throw new NoSuchElementException("Client with name = " + name + " and address = " + address + " was not found");
        }
        return client;
    }
}
