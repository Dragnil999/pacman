package server;

import dto.HostInputsDTO;
import dto.InputsDTO;
import dto.Parameters;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientDialog;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public ClientHandler(Socket client) {
        clientDialog = client;
        try {
            in = new ObjectInputStream(clientDialog.getInputStream());
            out = new ObjectOutputStream(clientDialog.getOutputStream());
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (!clientDialog.isClosed()) {
                out.writeObject(new HostInputsDTO());
                Object message = in.readObject();
                if (message instanceof InputsDTO) {
                    Parameters.setPacwomanPotentialDirection(((InputsDTO) message).getPotentialDirection());
                    Parameters.setPacwomanRotationAngle(((InputsDTO) message).getRotationAngle());
                    Parameters.setPacwomanScore(((InputsDTO) message).getScore());
                }
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
