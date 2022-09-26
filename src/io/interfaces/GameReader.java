package io.interfaces;

import exceptions.GameTypeNotFound;
import model.SingleGameStats;

import java.io.IOException;
import java.util.List;

public interface GameReader {

    SingleGameStats readGame(String filePath) throws IOException, GameTypeNotFound;

}
