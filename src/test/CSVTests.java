import main.MVPApplication;
import main.exceptions.FileWrappedException;
import main.exceptions.IncorrectFileDataException;
import main.exceptions.NoDirectoryFoundException;
import main.exceptions.NoFilesWithExtensionFoundException;
import main.model.Player;
import main.model.SingleGameStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CSVTests {

    MVPApplication application = new MVPApplication();
    String fileExtension = ".csv";
    String delimiter = ";";

    String[] failingDirectories = {
    "invalidGameType",
    "blankFields",
    "fieldsMissing",
    "notUniqueNicknameOneGame",
    "notUniqueNicknameDifferentGames",
    "notUniqueNumbers",
    "negativeNumbers",
    "notEqualTeamSizes",
    "negativePointFields",
    "noWinnerInGame",
    "notEqualReceivedGoalsAndSumOfMadeGoalsHandBall",
    "incorrectNumberOfTeams",
    "incorrectValueForInteger"
    };

    private Stream<String> provideInvalidDirectoryPaths() {
        return Arrays.stream(failingDirectories).map(path -> "csv/" + path);
    }
    private Player calculateMVP(String directoryPath) throws NoFilesWithExtensionFoundException, FileWrappedException, IncorrectFileDataException, NoDirectoryFoundException {
        List<SingleGameStats> games = application.processFilesFromDirectory(directoryPath, fileExtension, delimiter);
        return application.calculateMVP(games);
    }

    @Test
    void processValidFiles_getMVP() {
        try {
            Player mvpPlayer = calculateMVP("csv/valid");
            Assertions.assertEquals(mvpPlayer.getRating(), 102);
        } catch (FileWrappedException | NoFilesWithExtensionFoundException | IncorrectFileDataException |
                 NoDirectoryFoundException e) {
            Assertions.fail();
        }
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDirectoryPaths")
    void processInvalidFiles_GetException(String directoryPath) throws NoFilesWithExtensionFoundException, NoDirectoryFoundException {
        try {
            calculateMVP(directoryPath);
            Assertions.fail();
        }
        catch (FileWrappedException | IncorrectFileDataException e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(true);
        }
    }


}
