package server;

import java.io.IOException;

/**
 * Created by evgenyp on 1/23/2017.
 */
public interface IServer {
    public void start() throws IOException;
    public void stop() throws IOException;
    public void restart() throws IOException;
}
