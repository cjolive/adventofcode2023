import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class Day {

    public abstract long task1();

    public abstract long task2();

    protected Stream<String> puzzleInputStream() {
        String filename = this.getClass().getSimpleName().toLowerCase() + ".txt";
        return fileAsStringStream(filename);
    }

    private Stream<String> fileAsStringStream(String filename) {
        try {
            Path path = Paths.get(
                    this.getClass().getClassLoader().getResource(filename).toURI());
            return Files.lines(path);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Could not read file", e);
        }
    }
}
