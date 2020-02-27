package de.sollder1.chess;

import de.sollder1.chess.game.gui.TimeStamp;
import de.sollder1.chess.starter.gui.StarterView;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.FileReader;
import java.io.IOException;

public class App {

	private static String version = "0.1";

	public static void main(String[] args) throws IOException, XmlPullParserException {

		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader("pom.xml"));
		version = model.getVersion();

		StarterView.externalLaunch();
		
	}

	public static String getVersion() {
		return version;
	}
}
