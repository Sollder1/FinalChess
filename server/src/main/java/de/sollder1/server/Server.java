package de.sollder1.server;

import de.sollder1.server.connector.Listener;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.FileReader;
import java.io.IOException;

public class Server {
        public static void main(String[] args) throws IOException, XmlPullParserException {

        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));

        System.out.println("Final Chess Server: v." + model.getVersion());
        Listener.startListener(16, 1998);


    }

}
