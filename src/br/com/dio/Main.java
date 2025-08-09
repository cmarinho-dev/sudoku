package br.com.dio;

import br.com.dio.ui.custom.screen.MainScreen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {
        Path filePath = Paths.get(Main.class.getClassLoader().getResource("positions.txt").getPath());
        final var gameConfigs = loadPositions(filePath);
        var mainsScreen = new MainScreen(gameConfigs);
        mainsScreen.buildMainScreen();
    }

    private static Map<String, String> loadPositions(Path path) {
        try {
            return Files.lines(path)
                    .map(String::trim)
                    .filter(line -> line.contains(";"))
                    .collect(toMap(
                            line -> line.split(";")[0],
                            line -> line.split(";")[1]
                    ));
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo " + e.getMessage());
            System.exit(404);
            return null;
        }
    }

}
