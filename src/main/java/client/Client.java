package client;

import dto.HostInputsDTO;
import dto.InputsDTO;
import dto.Parameters;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public void connect(String host, int port) {
        try {
            client = new Socket(host, port);
            System.out.println("Client successfully connected to server");
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            start();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    @Override
    public void run() {
        try {
            while (!client.isClosed()) {
                out.writeObject(new InputsDTO());
                Object message = in.readObject();
                if (message instanceof HostInputsDTO) {
                    Parameters.setPacwomanPotentialDirection(((HostInputsDTO) message).getPacmanDirection());
                    Parameters.setRedGhostPotentialDirection(((HostInputsDTO) message).getRedGhostDirection());
                    Parameters.setPinkGhostPotentialDirection(((HostInputsDTO) message).getPinkGhostDirection());
                    Parameters.setBlueGhostPotentialDirection(((HostInputsDTO) message).getBlueGhostDirection());
                    Parameters.setOrangeGhostPotentialDirection(((HostInputsDTO) message).getOrangeGhostDirection());
                    Parameters.setPacwomanRotationAngle(((HostInputsDTO) message).getRotationAngle());
                    Parameters.setPacwomanScore(((HostInputsDTO) message).getScore());
                }
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
